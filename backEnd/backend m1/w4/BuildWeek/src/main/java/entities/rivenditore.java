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
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="venditore")
@Getter
@Setter
public class rivenditore {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int SellerCode;
	 @OneToMany(mappedBy = "rivenditore")
	 private Set<biglietto> bigliettiEmessi;
	 @OneToMany(mappedBy = "rivenditore")
	 private Set<abbonamento> abbonamentiEmessi;
	 @Enumerated(EnumType.STRING)
	    private lineaRoma zona;
}
