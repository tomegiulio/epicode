package entities;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import enumm.scadenza;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="tessere")
@Getter
@Setter
@NoArgsConstructor
public class tessera {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int tessId;
	@OneToOne(mappedBy="tessera")
	private user userr;
	
	private LocalDate dataEmm=LocalDate.now();
	private LocalDate dataScad=dataEmm.plusYears(1);
	@Enumerated(EnumType.STRING)
	private scadenza val;
	@OneToOne
	private abbonamento abbonamentiCard;
	
	
	
}
