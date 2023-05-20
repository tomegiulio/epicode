package com.riccardodegni.W5L4.W5L4;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.PropertySource;

import com.riccardodegni.W5L4.W5L4.config.Beans;
import com.riccardodegni.W5L4.W5L4.dao.DaoService;
import com.riccardodegni.W5L4.W5L4.entities.Role;
import com.riccardodegni.W5L4.W5L4.entities.RoleType;
import com.riccardodegni.W5L4.W5L4.entities.User;

@SpringBootApplication
@PropertySource("classpath:application.properties")
public class W5L4Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(W5L4Application.class, args);
	}
	
	@Autowired
	private DaoService dao;
	
	@Value("${populate_db}")
	private int populateDbFlag;

	@Override
	public void run(String... args) throws Exception {
		if( populateDbFlag == 1 ) {
			System.out.println("DB generation");
			populateDb();
		}
		
		getRolesFromUserById(2);
	}
	
	private void populateDb() {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(Beans.class);
		
		User u1 = (User)ctx.getBean("user", "Mario Rossi", "mrossi", "test");
		User u2 = (User)ctx.getBean("user", "Luigi Verdi", "lverdi", "test");
		Role r1 = (Role)ctx.getBean("role", RoleType.ROLE_ADMIN);
		Role r2 = (Role)ctx.getBean("role", RoleType.ROLE_USER);
				
		dao.saveRole(r1);
		dao.saveRole(r2);
		dao.saveUser(u1);
		dao.saveUser(u2);
		
		u1.setRoles(new HashSet<>() {{
			add(r1);
		}});

		u2.setRoles(new HashSet<>() {{
			add(r2);
		}});
		
		dao.saveUser(u1);
		dao.saveUser(u2);
		
		((AnnotationConfigApplicationContext)ctx).close();
	}
	
	private void getRolesFromUserById(int id) {
		Optional<User> authUserObj = dao.getUserById(id);
		User authUser = authUserObj.get();
		String role = "USER";
		Set<Role> roles = authUser.getRoles();
		
		for( Role r : roles ) {
			if( r.getType().toString().contains("ADMIN") ) {
				role = "ADMIN";
				break;
			}
		}
		
		System.out.println(role);
	}

}
