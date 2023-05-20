package com.sss.demo;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor


@Component("shirt")
@Scope("prototype")
public class shirt {
	private double price;
	private String name;
	public shirt(double price,String name){
		
	
		
	}
}
