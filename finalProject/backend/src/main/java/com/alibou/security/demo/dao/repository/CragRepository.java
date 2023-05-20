package com.alibou.security.demo.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.alibou.security.demo.model.Crag;
@Repository
public interface CragRepository extends JpaRepository<Crag, Integer> {
	List<Crag> findByName(String name);
	
	 
		@Query("SELECT r FROM Crag r WHERE lower(r.name) LIKE lower(:name)")
	    List<Crag> findByNameContainingIgnoreCase(@Param("name") String name);
}
