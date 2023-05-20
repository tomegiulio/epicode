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
@Table(name="bus")
public class bus  {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int busCode;
	@Column(name = "numero_posti")
    private int numeroPosti = 30;
    private int kilometriOrari=90;
    
    private String[] Manutenzione=new String[1000];
    @OneToMany
    private Set<tratta> viaggi;
    @OneToMany
    private Set<tratteripetute> viaggiripetuti;

    @ManyToMany
    private Set<biglietto> biglietti;
    
    @Enumerated(EnumType.STRING)
    private status statuss = status.SERVIZIO;
    
   
}