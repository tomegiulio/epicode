package com.w5d5.w5d5.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.w5d5.w5d5.entities.Postazione;

@Service
public class PostazioneService {
	@Autowired
	PostazioneRepository pr;
	
	public void insert(Postazione p){
		pr.save(p);
	}
	
	public Optional<Postazione> getFind(Integer id) {
		return pr.findById(id);
	}
}
