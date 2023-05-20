package com.sss.demo;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Component("drink")
@Scope("prototype")
public class drink extends product{
	public drink(String name,double price,double calories) {
		super(name,price,calories);
	}
}
