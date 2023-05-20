package com.w5d5.w5d5.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.w5d5.w5d5.entities.Postazione;
import com.w5d5.w5d5.entities.Prenotazioni;
import com.w5d5.w5d5.entities.User;
import com.w5d5.w5d5.enumeration.postazion;


@Configuration
public class PersonConfig {
	@Bean
	public User p11() {
		return User.builder()
				.name("ulio")
				.email("dasad")
				.pren(null)
				.build();
	}
	@Bean
	public User p112() {
		User p= User.builder()
				.name("jhonny")
				.email("jhonny@gmail.com")
				.pren(null)
				.build();
		
		 return p;
	}
	
	///////////////
	@Bean 
	Postazione po1() {
		return Postazione.builder()
				.citta("Padova")
				.maxPersone(30)
				.pren(null)
				.post(postazion.PRIVATE)
				.build();			
	}
	
}