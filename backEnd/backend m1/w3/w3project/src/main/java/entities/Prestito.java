
package entities;

import java.time.LocalDate;

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
@Table(name="loan")
@Getter
@Setter
@NoArgsConstructor
public class Prestito {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@OneToOne
	private Utente utente;
	@OneToOne
	private Book libro;
	private LocalDate loanStart;
	private LocalDate loanMaxTime;
	private LocalDate loanFinish;
	public Prestito(LocalDate loanStart) {
		this.loanStart = loanStart;
	}
	
}
