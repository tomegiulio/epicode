package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.SmokeSystem;
@Repository
public interface SmokeSystemRepository extends JpaRepository<SmokeSystem, Integer>{

}
