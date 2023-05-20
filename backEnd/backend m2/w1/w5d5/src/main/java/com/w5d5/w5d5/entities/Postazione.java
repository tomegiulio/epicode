package com.w5d5.w5d5.entities;

import java.util.List;

import com.w5d5.w5d5.enumeration.postazion;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="postazioni")
public class Postazione {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String citta;
	private int maxPersone;
	@Enumerated(EnumType.STRING)
	private postazion post;
	
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(
		      name = "post_pren", 
		      joinColumns = @JoinColumn(name = "post_id"), 
		      inverseJoinColumns = @JoinColumn(name = "pren_id"))
	private List<Prenotazioni> pren;
	
}
