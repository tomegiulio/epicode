package com.alibou.security.demo.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alibou.security.demo.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
	List<Comment> findByPostId(int postId);
	//List<Comment> findByAscendId(int ascendId);
    List<Comment> findByUserId(int userId);
}
