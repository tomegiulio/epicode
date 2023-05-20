package entities;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import enumm.lineaRoma;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="tratte")
@Getter
@Setter
public class tratta {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int roadCode;
	@Enumerated(EnumType.STRING)
	private lineaRoma partenza;
	@Enumerated(EnumType.STRING)
	private lineaRoma arrivo;
	private double minutiStimati;
	private double minutiReal;
	private double kilometri;
}
