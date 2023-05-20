package com.w5d5.w5d5.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.w5d5.w5d5.entities.User;

@Service
public class PersonService {
	@Autowired
	private PersonRepository pr;
	
	public void insert(User p) {
			pr.save(p);
	}
	public List<User> getAll() {
		return pr.findAll();
	}
	public Optional<User> getFind(Integer id) {
		return pr.findById(id);
	}
	
}

