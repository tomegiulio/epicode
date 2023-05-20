package com.sss.demo;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component("product")
@Scope("prototype")
public abstract class product {
private String name;
private double price;
private double calories;
private topping topp;
private size s;

public product(String name,double price,double calories) {
	this.name=name;
	setPrice(price);
	setCalories(calories);
}


public void setPrice(double price){
	if(getTopp()!=null) {
		this.price+=getTopp().getPrice();
	}if(getS()==size.FAMILY){
		this.price+=4.15;
	}else {
		this.price=price;
	}
}

public void setCalories(double calories) {
	if(getTopp()!=null) {
		this.calories+=getTopp().getCalories();
	}if(getS()==size.FAMILY){
		this.calories=calories*1.95;
	}else {
		this.calories=calories;
	}
}
}
