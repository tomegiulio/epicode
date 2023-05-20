package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.SmokeSystem;
import com.example.demo.repository.SmokeSystemRepository;

@Service
public class SmokeSystemService {
@Autowired
private SmokeSystemRepository sr;

public void save(SmokeSystem ss) {
	sr.save(ss);
}

public SmokeSystem smokeTes(int id,int smoke) throws Exception {
	Optional<SmokeSystem> systResult = sr.findById(id);
		if (systResult.isPresent()) {
			SmokeSystem ss = systResult.get();
			ss.setSmoke(smoke);
			
			sr.save(ss);
			return ss;
		}else {
			throw new Exception("Elemento non aggiornato");
		}
		}

public Optional<SmokeSystem> getById(int id) {
	return sr.findById(id);
	
}

public List<SmokeSystem> getall() {
	return sr.findAll();
}

public void delete(int id) {
	sr.deleteById(id);
}

}
