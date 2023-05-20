package com.w5d5.w5d5.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.w5d5.w5d5.entities.Postazione;
@Repository
public interface PostazioneRepository extends JpaRepository<Postazione, Integer>{
	
}
