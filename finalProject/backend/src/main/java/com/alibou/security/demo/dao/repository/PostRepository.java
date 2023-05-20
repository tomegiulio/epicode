package com.alibou.security.demo.dao.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alibou.security.demo.model.Post;
@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
	List<Post> findByCragId(int cragId);
	List<Post> findByRoutesId(int RoutesId);
    List<Post> findByUserId(int userId);
    List<Post> findAll( Sort sort);
}

