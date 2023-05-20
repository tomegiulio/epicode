package com.alibou.security.demo.dao.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibou.security.demo.dao.repository.FollowRepository;
import com.alibou.security.demo.model.Follow;
import com.alibou.security.user.User;
import com.alibou.security.user.UserRepository;

@Service
public class FollowService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FollowRepository followRepository;

    public void follow(int followerId, int followingId) {
        User follower = userRepository.findById(followerId).orElseThrow(() -> new IllegalArgumentException("Follower not found"));
        User following = userRepository.findById(followingId).orElseThrow(() -> new IllegalArgumentException("Following not found"));
        Follow follow = new Follow();
        follow.setFollower(follower);
        follow.setFollowing(following);
        followRepository.save(follow);
    }

    public void unfollow(int followerId, int followingId) {
        Follow follow = (Follow) followRepository.findByFollowerIdAndFollowingId(followerId, followingId);
        followRepository.delete(follow);
    }

    public List<User> getFollowers(int userId) {
        List<Follow> follows = followRepository.findByFollowingId(userId);
        return follows.stream().map(Follow::getFollower).collect(Collectors.toList());
    }

    public List<User> getFollowing(int userId) {
        List<Follow> follows = followRepository.findByFollowerId(userId);
        return follows.stream().map(Follow::getFollowing).collect(Collectors.toList());
    }
}
