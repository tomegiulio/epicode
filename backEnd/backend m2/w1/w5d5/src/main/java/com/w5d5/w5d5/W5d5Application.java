package com.w5d5.w5d5;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.w5d5.w5d5.config.PersonConfig;
import com.w5d5.w5d5.dao.PersonService;
import com.w5d5.w5d5.dao.PostazioneService;
import com.w5d5.w5d5.dao.PrenotazioneService;
import com.w5d5.w5d5.entities.Postazione;
import com.w5d5.w5d5.entities.Prenotazioni;
import com.w5d5.w5d5.entities.User;




@SpringBootApplication
public class W5d5Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(W5d5Application.class, args);
	}
	@Autowired
	PersonService ps;
	@Autowired
	PostazioneService pos;
	@Autowired
	PrenotazioneService pss;
	ApplicationContext ctx=new AnnotationConfigApplicationContext(PersonConfig.class);
	
	
	@Override
	public void run(String... args) throws Exception {
		boolean x=true;
				if(x==true) {
					//insertPerson();
					//insertPostazione();
					
					inserApputtanamento(1,1,LocalDate.now());
				}
				
				

		((AnnotationConfigApplicationContext)ctx).close();
	}
	///roba da persona
	public void insertPerson(){
		User per=(User) ctx.getBean("p11");
		ps.insert(per);
	}
	
	
	public void getPerson() {
		List<User> person=ps.getAll();
		if(person.isEmpty()) {
			System.out.println("nessun utente nel db");
		}else{
			for(User x:person) {
			
			System.out.println(x);}
		}
		}
	
	public void getPersonById(Integer id) {
		Optional<User> person=ps.getFind(id);
		if(person.isPresent()) {
			User u=person.get();
			System.out.println(u);
		}else{
			System.out.println("nessun utente con id "+id);
		}
		}
	
	///robe da postazione
	public void insertPostazione(){
		Postazione per=(Postazione) ctx.getBean("po1");
		pos.insert(per);
	}
	public void getPostById(Integer id){
		Optional<Postazione> post=pos.getFind(id);
		if(post.isPresent()) {
			Postazione p=post.get();
			System.out.println(p);
		}else {
			System.out.println("nessun utente con id "+id);
		}
	}
	///robe da appuntamento
	@SuppressWarnings("null")
	public void inserApputtanamento(int userId,int postazId,LocalDate data) {
		Optional<User> person=ps.getFind(userId);
		User u=person.get();
		Optional<Postazione> post=pos.getFind(postazId);
		Postazione p=post.get();
		Set<Prenotazioni> unoDi2 = null;
		Set<Prenotazioni> dueDi2 = null;
		List<Prenotazioni> prenotazioni=pss.getAll();
			if(prenotazioni==null) {
				pss.insert(u, p, data);
			}else {
				
			for(Prenotazioni x:prenotazioni) {
				
					if(x.getId()==postazId) {
						unoDi2.add(x);
					}
				}
			}if(unoDi2==null) {
				pss.insert(u, p, data);
			}else {
			
			for(Prenotazioni x:unoDi2) {
				if(x.getDate()==data) {
					dueDi2.add(x);
				}
			}
			if(dueDi2==null) {
				pss.insert(u, p, data);
			}else {
				System.out.println("Impossibile creare l'appuntamento");
			}
		
		}}}
		
			
		
			
			
	
	
	


