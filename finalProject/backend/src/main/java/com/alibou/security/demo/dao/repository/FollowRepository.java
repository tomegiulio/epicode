package com.alibou.security.demo.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alibou.security.demo.model.Follow;
@Repository
public interface FollowRepository extends JpaRepository<Follow, Integer> {
	List<Follow> findByFollowerId(int followerId);
	Follow findByFollowerIdAndFollowingId(int followerId, int followingId);
	Optional<Follow> findByFollowingIdAndFollowerId(int followerId, int followingId);
	List<Follow> findByFollowingId(int followingId);
}
