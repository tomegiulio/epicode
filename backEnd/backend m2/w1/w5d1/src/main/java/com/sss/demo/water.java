package com.sss.demo;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Component("water")
@Scope("prototype")
public class water extends drink {

	public water(String name, double price, double calories) {
		super(name, price, calories);
		setName("water(0.5l)");
		setCalories(0);
		setPrice(1.29);
	}
	

}
