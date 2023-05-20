package com.w5d5.w5d5.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.w5d5.w5d5.entities.User;
@Repository
public interface PersonRepository extends JpaRepository<User,Integer>{

}

