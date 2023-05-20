package com.sss.demo;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Component("wine")
@Scope("prototype")
public class wine extends drink {

	public wine(String name, double price, double calories) {
		super(name, price, calories);
		setName("wine(0.7l)13°");
		setCalories(120);
		setPrice(7.49);
	}

}
