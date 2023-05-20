package com.w6d5.w6d5;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.w6d5.w6d5.config.Beans;
import com.w6d5.w6d5.enumerated.State;
import com.w6d5.w6d5.model.Device;
import com.w6d5.w6d5.model.SecurityUser;
import com.w6d5.w6d5.model.User;
import com.w6d5.w6d5.service.DeviceService;
import com.w6d5.w6d5.service.JpaUserDetailsService;
import com.w6d5.w6d5.service.UserService;

@SpringBootApplication
public class W6d5Application implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(W6d5Application.class, args);
	}
	@Autowired
	private UserService us;
	@Autowired
	private DeviceService ds;
	@Autowired
	JpaUserDetailsService js;
	@Override
	public void run(String... args) throws Exception {
		int y=0;
		if(y==2) {
			insertUserss();
			//insertUser();
		}
		
	}
	public void insertUserss(){
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(Beans.class);
		User u1 = (User)ctx.getBean("user");
		User u2 = (User)ctx.getBean("user2");
		us.save(u1);
		us.save(u2);
		((AnnotationConfigApplicationContext)ctx).close();
	}
	public void insertUsers() {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(Beans.class);
		User u1 = (User)ctx.getBean("user");
		User u2 = (User)ctx.getBean("user2");

		Device d1 = (Device)ctx.getBean("pc");
		Device d2 = (Device)ctx.getBean("smarthphone");
		Device d3 = (Device)ctx.getBean("tablet");
		d1.setState(State.ASSIGNED);
		d3.setState(State.ASSIGNED);
		d2.setState(State.ASSIGNED);
		
		u1.setDevices(new HashSet<>() {private static final long serialVersionUID = 1L;
		{
			add(d1);
			add(d2);
			add(d3);
		}});
		us.save(u1);
		us.save(u2);
		ds.save(d3);
		ds.save(d2);
		ds.save(d1);
		((AnnotationConfigApplicationContext)ctx).close();
	}

}
