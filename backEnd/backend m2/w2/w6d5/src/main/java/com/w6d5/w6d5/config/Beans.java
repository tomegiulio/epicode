package com.w6d5.w6d5.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.w6d5.w6d5.enumerated.State;
import com.w6d5.w6d5.enumerated.Type;
import com.w6d5.w6d5.model.Device;

import com.w6d5.w6d5.model.User;

@Configuration
public class Beans {
	 
	@Bean
	@Scope("prototype")
	public User user() {
		return User.builder()
				.fullName("giulio tome")
				.username("giulio100")
				.role("ADMIN")
				.password("123")
				.email("mrtomegiulio@gg.com")
				.devices(null)
				.build();
	}
	@Bean
	@Scope("prototype")
	public User user2() {
		return User.builder()
				.fullName("giulio tome")
				.username("giulio21")
				.role("USER")
				.password("123")
				.email("mrtomegiulio@gg.com")
				.devices(null)
				.build();
	}
	@Bean
	@Scope("prototype")
	public Device pc() {
		return Device.builder()
				.type(Type.PC)
				.state(State.AVAILABLE)
				.build();
	}
	@Bean
	@Scope("prototype")
	public Device smarthphone() {
		return Device.builder()
				.type(Type.SMARTPHONE)
				.state(State.AVAILABLE)
				.build();
	}
	@Bean
	@Scope("prototype")
	public Device tablet() {
		return Device.builder()
				.type(Type.TABLET)
				.state(State.AVAILABLE)
				.build();
	}

}
