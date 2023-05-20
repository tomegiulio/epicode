package com.sss.demo;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component("salami")
@Scope("prototype")
public class salami extends product {
	public salami(String name, double price, double calories, topping topp, size s) {
		super(name, price, calories, topp, s);
		setName("pepperoni(tomato,cheese,salami)");
		setPrice(price);
		setCalories(calories);
		setTopp(topp);
		setS(s);

	}
}
