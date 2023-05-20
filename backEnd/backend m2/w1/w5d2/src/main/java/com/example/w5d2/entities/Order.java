package com.example.w5d2.entities;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.springframework.stereotype.Component;

import com.example.w5d2.enumm.state;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Component
public class Order {

@ManyToMany
Set<table> tables;
@OneToMany
Set<pizza>pizzas;
@OneToMany
Set<drink>drink;
state status=state.LOADING;
LocalDate time=LocalDate.now();
int people;
}
