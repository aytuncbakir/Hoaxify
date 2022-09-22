package com.hoaxify.ws.hoax;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.hoaxify.ws.user.User;

public interface HoaxRepository extends JpaRepository<Hoax, Long>, JpaSpecificationExecutor<Hoax>{
	
	public Page<Hoax> findByUser(User user, Pageable page); 
	
//	public Page<Hoax> findByIdLessThan(long id, Pageable page); 
	
//	public Page<Hoax> findByIdLessThanAndUser(long id, User user, Pageable page); 
	
//	public long countByIdGreaterThan(long id);
//	
//	public long countByIdGreaterThanAndUser(long id, User user);
	
	public List<Hoax> findByIdGreaterThan(long id, Sort sort);
	
	public List<Hoax> findByIdGreaterThanAndUser(long id, User user, Sort sort);

}
