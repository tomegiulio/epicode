package com.sss.demo;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Component("lemonade")
@Scope("prototype")
public class lemonade extends drink {


	public lemonade(String name, double price, double calories) {
		super(name, price, calories);
		setName("lemonade(0.33l");
		setCalories(120);
		setPrice(1.29);
	}
	

}