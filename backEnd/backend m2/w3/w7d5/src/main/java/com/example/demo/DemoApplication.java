package com.example.demo;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.example.demo.config.Beans;
import com.example.demo.model.Control;
import com.example.demo.model.SmokeSystem;
import com.example.demo.service.ControlService;
import com.example.demo.service.SmokeSystemService;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	
	@Autowired 
	private SmokeSystemService ss;
	@Autowired
	private ControlService cs;
	
	AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(Beans.class);
	
	@Override
	public void run(String... args) throws Exception {
		int x=0;
		if(x==1) {
			populateDb();
		}
		cs.Allarm(1);
		((AnnotationConfigApplicationContext)ctx).close();
		
	}
	public void populateDb() {
		Control c1=(Control) ctx.getBean("control1");
		SmokeSystem ss1=(SmokeSystem)ctx.getBean("sys1");
		SmokeSystem ss2=(SmokeSystem)ctx.getBean("sys2");
		SmokeSystem ss3=(SmokeSystem)ctx.getBean("sys3");
		SmokeSystem ss4=(SmokeSystem)ctx.getBean("sys4");
		c1.setSonde(new HashSet<>() {private static final long serialVersionUID = 1L;
		{
			add(ss1);
			add(ss2);
			add(ss3);
			add(ss4);
		}});
		cs.save(c1);
		ss.save(ss1);
		ss.save(ss2);
		ss.save(ss3);
		ss.save(ss4);
	}
}
