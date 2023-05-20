package com.w6d5.w6d5.model;

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
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class User {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;
private String fullName;
private String username;
private String email;
private String password;
private String role;
@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
private Set<Device> devices = new HashSet<>();;
}
