package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import enumm.per;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="book")
@Getter
@Setter
@NoArgsConstructor
public class Book {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String titolo;
	private int annoPubb;
	private int numPag;
	private String autore;
	private String genere;
	private per period;
	private boolean disponibile;
	public Book(String titolo, int annoPubb, int numPag, String autore, String genere,per period) {
		this.titolo = titolo;
		this.annoPubb = annoPubb;
		this.numPag = numPag;
		this.autore = autore;
		this.genere = genere;
		this.period=period;
		this.disponibile=true;
	}
	

}
