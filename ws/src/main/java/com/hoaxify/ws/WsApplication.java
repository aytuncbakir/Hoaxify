package com.hoaxify.ws;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WsApplication {

	
	private static final Logger log = LoggerFactory.getLogger(WsApplication.class);

	// 191
	public static void main(String[] args) {
		
		SpringApplication.run(WsApplication.class, args);
		/*
		ApplicationContext applicationContext = SpringApplication.run(WsApplication.class, args);
		String[] allBeanNames = applicationContext.getBeanDefinitionNames();
		for(String beanName : allBeanNames) {
			log.info(beanName);
		}
		*/
	}
	
//	@Bean
//	@Profile("dev")
//	CommandLineRunner createInitialUsers(UserService userService, HoaxService hoaxService ) {
		/*
		return new CommandLineRunner() {

			@Override
			public void run(String... args) throws Exception {
				User user = new User();
				user.setUsername("user1");
				user.setDisplayName("display1");
				user.setPassword("Password1");
				userService.save(user);
			}
			
		};
		*/
		
		/*
				return (args) -> {
					
					try {
						userService.getByUsername("user1");
					} catch (Exception e) {
						for(int i = 1; i<=25; i++) {
							User user = new User();
							user.setUsername("user"+i);
							user.setDisplayName("display"+i);
							user.setPassword("Password"+i);
							userService.save(user);
							for(int j = 1; j<=20; j++) {
								HoaxSubmitVM hoax = new HoaxSubmitVM();
								hoax.setContent("hoax"+j+" from user "+ i);
								hoaxService.save(hoax, user);
							}
						}
					}
					
					
				};
		
		 	*/
			
	//}

}
