package com.sss.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		System.out.println("hello world!");
		makePizza();
	}
	
	public static void makePizza(){
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		
		ctx.scan("com.sss.demo");
        ctx.refresh();
        topping cond=(topping)ctx.getBean("topping","fries",2,300);
        pizza p=(pizza)ctx.getBean("pizza","ggg",8,1200,cond,size.FAMILY);
        hawaii p2=(hawaii)ctx.getBean("hawaii","ggg",10,1200,cond,size.MEDIUM);
        salami p3=(salami)ctx.getBean("salami","ggg",9,1200,cond,size.MEDIUM);
        water w1=(water)ctx.getBean("water","ggg",9,9);
        lemonade l1=(lemonade)ctx.getBean("lemonade","ggg",9,9);
        wine wi1=(wine)ctx.getBean("wine","ggg",9,9);
        System.out.println("pizza:");
        System.out.println(p.getName()+" + "+p.getTopp().getName()+", price: "+p.getPrice()+"$, calories: "+p.getCalories());
        System.out.println(p2.getName()+" + "+p2.getTopp().getName()+", price: "+p2.getPrice()+"$, calories: "+p2.getCalories());
        System.out.println(p3.getName()+" + "+p3.getTopp().getName()+", price: "+p3.getPrice()+"$, calories: "+p3.getCalories());
        System.out.println("drink:");
        System.out.println(w1.getName()+", price: "+w1.getPrice()+"$, calories: "+w1.getCalories());
        System.out.println(l1.getName()+", price: "+l1.getPrice()+"$, calories: "+l1.getCalories());
        System.out.println(wi1.getName()+", price: "+wi1.getPrice()+"$, calories: "+wi1.getCalories());
        System.out.println("gadject");
        mug m1=(mug)ctx.getBean("mug");
        m1.setName("mug");
        m1.setPrice(4.99);
        shirt s1=(shirt)ctx.getBean("shirt");
    	s1.setPrice(4.99);
		s1.setName("mug(godfather)");
        System.out.println(s1.getName()+", price: "+s1.getPrice());
        System.out.println(m1.getName()+", price: "+m1.getPrice());
		ctx.close();
		
	}

}
