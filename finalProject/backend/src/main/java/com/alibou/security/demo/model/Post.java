package com.alibou.security.demo.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.alibou.security.user.User;

import io.micrometer.common.lang.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
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
public class Post {
	@Id
	@GeneratedValue
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	@Lob     
	private String content;
	private String media;
	private boolean hidden=false;
	private boolean deleted=false;
	
	
	private String style;	
	 private int tryNumber=1;

	 private int valutation;
	 
	 
	 @Nullable
	 @ManyToOne
		@JoinColumn(name = "crag_id")
		private Crag crag;
	 
	
	 @ManyToOne(optional = true)
		@JoinColumn(name = "ruotes_id")
		private Routes routes;
	
	private LocalDateTime createdAt=LocalDateTime.now();
}
