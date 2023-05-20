package entities;


import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import enumm.status;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name="tram")
public class tram  {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int tramCode;
	@Column(name = "numero_posti")
    private int numeroPosti = 50;
    private int kilometriOrari=70;
    
    private String[] Manutenzione=new String[1000];
    @OneToMany
    @Column(name = "tratte")
    private Set<tratta> viaggi;
    
    @OneToMany
    private Set<tratteripetute> viaggiripetuti;
    

    @ManyToMany
    private Set<biglietto> biglietti;
    
    @Enumerated(EnumType.STRING)
    private status statuss = status.SERVIZIO;
    
    
    
	
    
}