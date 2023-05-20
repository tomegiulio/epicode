package entities;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import enumm.lineaRoma;
import enumm.statoServizio;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="ditributore")
@Getter
@Setter

public class distributore{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int SellerCode;
	@Enumerated(EnumType.STRING)
    private statoServizio attivo=statoServizio.IN_SERVIZIO;
	@Enumerated(EnumType.STRING)
    private lineaRoma zona;

	@OneToMany(mappedBy = "distributore")
	 private Set<biglietto> bigliettiEmessi;
	 @OneToMany(mappedBy = "distributore")
	 private Set<abbonamento> abbonamentiEmessi;
	 }
