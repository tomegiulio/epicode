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
import javax.persistence.Table;

import enumm.timbro;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="biglietto")
@Getter
@Setter


public class biglietto {


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int TicketCode;
	private LocalDate dataEmm=LocalDate.now();
	private LocalDate dataTim;
	@Enumerated(EnumType.STRING)
	private timbro usato=timbro.FALSE;
	@ManyToOne
	@JoinColumn(name="id")
	private rivenditore rivenditore;
	@ManyToOne
	@JoinColumn(name="idDistributore")
	private distributore distributore;
	

	public biglietto() {
		
	}
	
}
