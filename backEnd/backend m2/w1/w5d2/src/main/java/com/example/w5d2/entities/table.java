package com.example.w5d2.entities;

import java.util.Set;

import javax.persistence.ManyToMany;

import org.springframework.stereotype.Component;

import com.example.w5d2.enumm.occupated;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Component
public class table {

@ManyToMany
Set<Order>orders;
int maxP;
int p;
double priceTot;
occupated state=occupated.FREE;
}
