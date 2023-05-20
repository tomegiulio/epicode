package com.sss.demo;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component("pizza")
@Scope("prototype")
public class pizza extends product {
	
	public pizza(String name,double price,double calories,topping topp,size s) {
		super(name,price,calories,topp,s);
		setName("Margerita(tomato,cheese)");
		setPrice(price);
		setCalories(calories);
		setTopp(topp);
		setS(s);
	
	}
	
	
}
