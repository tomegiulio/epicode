package com.alibou.security.demo.model;

import java.time.LocalDate;

import com.alibou.security.user.User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
public class CommentAscend {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
 	
	@ManyToOne
	@JoinColumn(name = "user_id")
    private User user;
    
 	@Lob
    private String text;

    private LocalDate createdAt;
	
}
