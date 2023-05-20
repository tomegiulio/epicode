package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="user")
@Getter
@Setter
@NoArgsConstructor
public class user {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int uteneteId;
	private String nome;
	private String cognome;
	public user(String nome, String cognome) {
		
		this.nome = nome;
		this.cognome = cognome;
	}
	@OneToOne
	private tessera tessera;
}
