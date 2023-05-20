package com.sss.demo;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component("topping")
@Scope("prototype")
public class topping extends product {
	
	
	public topping(String name,double price,int calories) {
		super(name,price,calories);
		
	}
}
