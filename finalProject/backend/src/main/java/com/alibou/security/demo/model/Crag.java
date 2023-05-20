package com.alibou.security.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
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
public class Crag {
	@Id
	@GeneratedValue
	private Integer id;
	private String name;
	//descrizione della falesia
	@Lob
	private String text;
	//posizione
	private String lng;
	private String lat;
	//immagine profilo
	private String pic;
	private String city;
	private String country;
	
	
}
