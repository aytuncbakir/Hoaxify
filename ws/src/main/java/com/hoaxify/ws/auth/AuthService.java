package com.hoaxify.ws.auth;


import java.util.Optional;
import java.util.UUID;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hoaxify.ws.user.User;
import com.hoaxify.ws.user.UserRepository;
import com.hoaxify.ws.user.vm.UserVM;



@Service
public class AuthService {
	
	UserRepository userRepository;
	
	PasswordEncoder passwordEncoder;
	
	TokenRepository tokenRepository;

	public AuthService(UserRepository userRepositor, PasswordEncoder passwordEncoder, TokenRepository tokenRepository) {
		this.userRepository = userRepositor;
		this.passwordEncoder = passwordEncoder;
		this.tokenRepository = tokenRepository;
	}

	public AuthResponse authenticate(Credentials credentials) {
		User inDB = userRepository.findByUsername(credentials.getUsername());
		if(inDB == null) {
			throw new AuthException();
		}
		
		boolean matches = passwordEncoder.matches(credentials.getPassword(), inDB.getPassword());
		if(!matches) {
			throw new AuthException();
		}
		
		//Date expiresAt = new Date(System.currentTimeMillis() + 10 * 1000 );
		UserVM userVM = new UserVM(inDB);
		String token = generateRandomToken();
		
		Token tokenEntity = new Token();
		tokenEntity.setToken(token);
		tokenEntity.setUser(inDB);
		tokenRepository.save(tokenEntity);
		AuthResponse response = new AuthResponse();			
		response.setToken(token);
		response.setUser(userVM);
		return response;
		
	}

	public UserDetails getUserDetails(String token) {
		Optional<Token> optionalToken =  tokenRepository.findById(token);
		if(!optionalToken.isPresent()) {
			return null;
		}
		return optionalToken.get().getUser();
	}
	
	public String generateRandomToken() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	public void clearToken(String token) {
		tokenRepository.deleteById(token);
	}


}
