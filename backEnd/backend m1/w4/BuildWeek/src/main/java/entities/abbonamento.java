package entities;


import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import enumm.emissione;
import enumm.scadenza;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="abbonamento")
@Getter
@Setter
@NoArgsConstructor

public class abbonamento {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	
	private int subCode;
	@Enumerated(EnumType.STRING)
	private emissione type;
	private LocalDate dataEmm=LocalDate.now();
	private LocalDate dataScad;
	@Enumerated(EnumType.STRING)
	private scadenza val=scadenza.VALIDO;
	@OneToOne
	private tessera user;
	@ManyToOne
	@JoinColumn(name="idDistributore")
	private distributore distributore;
	@ManyToOne
	@JoinColumn(name="idRivenditore")
	private rivenditore rivenditore;
	public abbonamento(emissione type) {
	
		this.type = type;
	}
	
	
	
	
	
}
