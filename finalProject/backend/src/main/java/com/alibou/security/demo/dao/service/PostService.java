package com.alibou.security.demo.dao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.alibou.security.demo.dao.repository.PostRepository;
import com.alibou.security.demo.model.Post;

@Service
public class PostService {
    
    @Autowired
    private PostRepository postRepository;
    
    public List<Post> findAll() {
        Sort sort = Sort.by(Sort.Direction.DESC, "createdAt");
        return postRepository.findAll(sort);
    }
    
    
}






