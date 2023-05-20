package entities;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import javax.persistence.Table;

import enumm.stato;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="partecipazione")
@Getter
@Setter
public class partecipazione {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	private persona persona;
	@ManyToOne
	private evento evento;
	
	@Enumerated(EnumType.STRING)
	private stato stato;

}
