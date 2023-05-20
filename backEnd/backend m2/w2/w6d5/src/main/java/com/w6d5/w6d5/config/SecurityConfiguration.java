package com.w6d5.w6d5.config;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import com.w6d5.w6d5.service.JpaUserDetailsService;
@EnableWebSecurity
@EnableMethodSecurity
@Configuration
public class SecurityConfiguration {
	    @Autowired
	    private final JpaUserDetailsService myUserDetailsService;
	   
	    public SecurityConfiguration(JpaUserDetailsService myUserDetailsService) {
	        this.myUserDetailsService = myUserDetailsService;
	    }


	    	
	        @Bean
	        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	            http
	                    .csrf().disable()
	                    .cors().and()
	                    .authorizeHttpRequests((authz) -> authz
	                    		 .requestMatchers("/api/admin/**").hasAuthority("ADMIN")
	                    		 
	                    		 
	                    		 
	                    		 
	                            .anyRequest().authenticated())
	                    .userDetailsService(myUserDetailsService)
	                    .headers(headers -> headers.frameOptions().sameOrigin())
	                    .httpBasic(withDefaults())
	            .formLogin()
				.successForwardUrl("/api/login_success")
			.and()
			.logout();
	            return http.build();
	    }	

	    	
	    	
	       
	}



	