package com.alibou.security.demo.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.alibou.security.demo.model.Routes;
@Repository
public interface RoutesRepository extends JpaRepository<Routes, Integer> {
    List<Routes> findByCragId(int cragId);
    List<Routes> findByName(String name);
    
	@Query("SELECT r FROM Routes r WHERE lower(r.name) LIKE lower(:name)")
    List<Routes> findByNameContainingIgnoreCase(@Param("name") String name);

}

