package com.w5d5.w5d5.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.w5d5.w5d5.entities.Postazione;
import com.w5d5.w5d5.entities.Prenotazioni;

@Repository
public interface PrenotazioneRepository extends JpaRepository<Prenotazioni, Integer>{
	 
}

