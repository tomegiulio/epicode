package com.w5d5.w5d5.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.w5d5.w5d5.entities.Postazione;
import com.w5d5.w5d5.entities.Prenotazioni;
import com.w5d5.w5d5.entities.User;
@Service
public class PrenotazioneService {
	@Autowired
	private PrenotazioneRepository pr;
	
	 public void insert(User u, Postazione p, LocalDate d){
	        Prenotazioni prenotazione = Prenotazioni.builder().date(d).user(u).postaz(p).build();
	        
	        
	        
	            pr.save(prenotazione);
	        
	}
	public Optional<Prenotazioni> getFind(Integer id) {
		return pr.findById(id);
	}
	public List<Prenotazioni> getAll() {
		return pr.findAll();
	}
	
	
}
