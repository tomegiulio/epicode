package entities;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import enumm.sex;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Entity
@Table(name="persone")

public class persona {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;


private String name;
private String surname;
private String email;
@Enumerated(EnumType.STRING)
private sex sesso;
private LocalDate dataNascita;
@OneToMany(mappedBy="persona",cascade=CascadeType.REMOVE)
private List<partecipazione> listaPartecipazioni;


}