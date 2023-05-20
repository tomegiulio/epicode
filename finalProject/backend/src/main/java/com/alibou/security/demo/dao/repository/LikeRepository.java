package com.alibou.security.demo.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alibou.security.demo.model.Likee;
@Repository
public interface LikeRepository extends JpaRepository<Likee, Integer>{
	List<Likee> findByPostId(int postId);
    List<Likee> findByUserId(int userId);
    List<Likee> findByCragId(int cragId);
    
    Optional<Likee> findByPostIdAndUserId(int postId, int userId);
    Optional<Likee> findByCragIdAndUserId(int cragId, int userId);
}
