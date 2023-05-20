package com.sss.demo;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component("hawaii")
@Scope("prototype")
public class hawaii extends product{
	public hawaii(String name,double price,double calories,topping topp,size s) {
		super(name,price,calories,topp,s);
		setName("hawaii(tomato,cheese,pineapple,ham)");
		setPrice(price);
		setCalories(calories);
		setTopp(topp);
		setS(s);
	
	}
}
