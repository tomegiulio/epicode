package com.sss.demo;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

@Component("mug")
@Scope("prototype")
public class mug {
	private double price;
	private String name;
	public mug(double price,String name){
		this.price=price;
		this.name=name;
	}
}
