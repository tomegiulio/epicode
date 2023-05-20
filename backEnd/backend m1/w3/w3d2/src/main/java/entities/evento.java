package entities;

import java.time.LocalDate;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="eventi")
@Getter
@Setter
public class evento {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String titolo;
	private LocalDate dataEvento;
	private String descrizione;
	private int numeroMassimoPartecipanti;
	@Enumerated(EnumType.STRING)
	private tipo tipoEvento;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitolo() {
		return titolo;
	}
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	public LocalDate getDataEvento() {
		return dataEvento;
	}
	public void setDataEvento(LocalDate dataEvento) {
		this.dataEvento = dataEvento;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public int getNumeroMassimoPartecipanti() {
		return numeroMassimoPartecipanti;
	}
	public void setNumeroMassimoPartecipanti(int numeroMassimoPartecipanti) {
		this.numeroMassimoPartecipanti = numeroMassimoPartecipanti;
	}
	public tipo getTipoEvento() {
		return tipoEvento;
	}
	public void setTipoEvento(tipo tipoEvento) {
		this.tipoEvento = tipoEvento;
	}
	
	
	
}
