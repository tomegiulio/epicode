package com.riccardodegni.W5L4.W5L4.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.riccardodegni.W5L4.W5L4.entities.Role;
import com.riccardodegni.W5L4.W5L4.entities.RoleType;
import com.riccardodegni.W5L4.W5L4.entities.User;

@Configuration
public class Beans {

	@Bean
	@Scope("prototype")
	public User user(String fullname, String username, String password) {
		return User.builder()
				.fullname(fullname)
				.username(username)
				.password(password)
				.active(true)
				.build();
	}
	
	@Bean
	@Scope("prototype")
	public Role role(RoleType rt) {
		return Role.builder()
				.type(rt)
				.build();
	}
	
}
