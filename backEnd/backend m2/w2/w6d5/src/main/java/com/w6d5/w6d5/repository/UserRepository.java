package com.w6d5.w6d5.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.w6d5.w6d5.model.User;
public interface UserRepository extends JpaRepository<User, Integer>{

	 Optional<User> findByUsername(String username);

}
