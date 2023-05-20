package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.model.Control;
import com.example.demo.model.SmokeSystem;

@Configuration
public class Beans {
	
@Bean
public Control control1() {
	return Control.builder()
			.controlName("milano")
			.sonde(null)
			.build();
}
@Bean
public SmokeSystem sys1() {
	return SmokeSystem.builder()
			.countryName("milano1")
			.latudine(1200)
			.logitudine(3000)
			.smoke(0)
			.build();
}
@Bean
public SmokeSystem sys2() {
	return SmokeSystem.builder()
			.countryName("milano2")
			.latudine(1260)
			.logitudine(3100)
			.smoke(0)
			.build();
}
@Bean
public SmokeSystem sys3() {
	return SmokeSystem.builder()
			.countryName("milano3")
			.latudine(1250)
			.logitudine(3020)
			.smoke(0)
			.build();
}
@Bean
public SmokeSystem sys4() {
	return SmokeSystem.builder()
			.countryName("milano4")
			.latudine(1400)
			.logitudine(2900)
			.smoke(0)
			.build();
}
}
