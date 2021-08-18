package com.jwt.security.learn.securityjwt;

import com.jwt.security.learn.securityjwt.models.Role;
import com.jwt.security.learn.securityjwt.models.User;
import com.jwt.security.learn.securityjwt.service.UserService;
import javafx.scene.layout.BackgroundRepeat;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class SecurityjwtApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurityjwtApplication.class, args);
	}


	@Bean
	BCryptPasswordEncoder bCryptPasswordEncoder(){
		return new BCryptPasswordEncoder();
	}


	/*@Bean
	CommandLineRunner run(UserService userService){
		return args -> {
			userService.saveRole(new Role(null,"ADMIN"));
			userService.saveRole(new Role(null,"USER"));
			userService.saveRole(new Role(null,"MANAGER"));
			userService.saveRole(new Role(null,"SUPER_ADMIN"));

			userService.saveUser(new User(null,"John","John@gmail.com","1234",new ArrayList<>()));
			userService.saveUser(new User(null,"Waqar","waqar@gmail.com","abc",new ArrayList<>()));
			userService.saveUser(new User(null,"Raza","raza@gmail.com","222",new ArrayList<>()));
			userService.saveUser(new User(null,"Suleman","suleman@gmail.com","444",new ArrayList<>()));

			userService.addRoleToUser("John@gmail.com","ADMIN");

			userService.addRoleToUser("waqar@gmail.com","USER");
			userService.addRoleToUser("waqar@gmail.com","MANAGER");

			userService.addRoleToUser("raza@gmail.com","MANAGER");

			userService.addRoleToUser("suleman@gmail.com","SUPER_ADMIN");
			userService.addRoleToUser("suleman@gmail.com","MANAGER");




		};
	}*/


}
