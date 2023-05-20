package com.example.demo.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Control {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String controlName;
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<SmokeSystem> sonde= new HashSet<>();
	
}
