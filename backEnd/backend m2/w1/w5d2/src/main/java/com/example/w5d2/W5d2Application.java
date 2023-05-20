package com.example.w5d2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import com.example.w5d2.entities.Order;
import com.example.w5d2.entities.drink;
import com.example.w5d2.entities.pizza;
import com.example.w5d2.entities.table;

@SpringBootApplication
public class W5d2Application {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	public static void main(String[] args) {
		SpringApplication.run(W5d2Application.class, args);
		Order o1=new Order();
		pizza p1=new pizza( "margherita",9);
		drink d2=new drink("beer",5);
		orderDrink(o1,d2);
		orderPizza(o1,p1);
		order(o1,1);
	}
	
	
	public static void orderPizza(Order o,pizza p) {
		o.getPizzas().add(p);
		
	}
	public static void orderDrink(Order o,drink d) {
		o.getDrink().add(d);
	}
	
	public static void order(Order o1,int p) {
		
		table t1=new table();
		if(p<t1.getMaxP()) {
		double sum=0;
		double sum2=0;
		
		o1.getTables().add(t1);
		o1.setPeople(p);
		t1.setP(p);
		for(pizza pizz:o1.getPizzas()) {
			sum=sum+pizz.getPrice();
		}
		for(drink drin:o1.getDrink()) {
			sum=sum+drin.getPrice();
		}
		t1.setPriceTot(sum+sum2);
		System.out.println(" conto totale"+t1.getPriceTot());
		}else {
			System.out.println("troppo gente");
		}
		
	
	}
}
