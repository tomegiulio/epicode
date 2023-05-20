package com.riccardodegni.W5L4.W5L4.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.riccardodegni.W5L4.W5L4.config.Beans;
import com.riccardodegni.W5L4.W5L4.dao.DaoService;
import com.riccardodegni.W5L4.W5L4.entities.Role;
import com.riccardodegni.W5L4.W5L4.entities.User;


@RestController

@Controller
public class AppController {

	@GetMapping("/")
	@ResponseBody
	public String index() {
		return "home page";
	}
	 @GetMapping("/getpage")
	    public String m1() {

	        RestTemplate rt = new RestTemplate();
	        String rtUrl = "http://localhost:8082/page1";
	        ResponseEntity<String> response = rt.getForEntity(rtUrl, String.class);
	        return "response: " + response.getBody() + "";

	    }
	
	@GetMapping("/page1")
	@ResponseBody
	public String page1() {
		return "page 1";
	}
	
	@GetMapping("/page2")
	@ResponseBody
	public String page2() {
		return "page 2";
	}
	
	@PostMapping("/login_success")
	@ResponseBody
	public String login_success() {
		return "login success";
	}
	
	
	@Autowired
	PasswordEncoder pwEncoder;
	
	@Autowired
	private DaoService dao;
	
	@GetMapping("/auth_add_user")
	@ResponseBody
	public String auth_add_user() {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(Beans.class);
		User u = (User)ctx.getBean("user", 
				"Peach Pink", "ppink", pwEncoder.encode("ppink"));
		
		Set<Role> roles = new HashSet<>();
		roles.add( dao.getRoleById(2).get() );
		u.setRoles(roles);
		
		User uAdded = dao.saveUser(u);
		
		((AnnotationConfigApplicationContext)ctx).close();
		
		return (uAdded != null) ? "utente aggiunto!" : "errore!";
	}
	
	@GetMapping("/auth_update_user_pw")
	@ResponseBody
	public String auth_update_user_pw() {
		int id = 2;
		
		User u = dao.getUserById(id).get();
		String pw = u.getPassword();
		u.setPassword( pwEncoder.encode(pw) );
		dao.saveUser(u);
		
		return "utente aggiornato";
	}
	
	@GetMapping("/auth_update_user_cryptovalue1")
	@ResponseBody
	public String auth_update_user_cryptovalue1() {
		int id = 2;
		
		User u = dao.getUserById(id).get();
		u.setCryptoValue1("ananas");
		dao.saveUser(u);
		
		return "utente aggiornato";
	}
	
	@GetMapping("/getuser")
	@ResponseBody
	@CrossOrigin(origins = "http://127.0.0.1:5500/")
	public List<User> getUser() {
		return (List<User>) dao.getAll();
	}
	
}