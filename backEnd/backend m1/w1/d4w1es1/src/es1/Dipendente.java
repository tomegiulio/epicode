package es1;

public class Dipendente {
	private double stipendioBase;
	private double stipendio;
	private String matricola;
	private double importoOrarioStraordianario=30;
	private indice livello;
	private String dipartimento;
	public Dipendente(String matricola,String dipartimento) {
		this.stipendioBase=1000;
		this.stipendio=stipendioBase;
		setImportoOrarioStraordianario(importoOrarioStraordianario);
		setDipartimento(dipartimento);
		this.livello=indice.OPERAIO;
		this.matricola=matricola;
		this.dipartimento=dipartimento;
	}
	public Dipendente(String matricola,indice livello,String dipartimento) {
		this.stipendioBase=1000;
		this.stipendio=getStipendio();
		setImportoOrarioStraordianario(importoOrarioStraordianario);
		setDipartimento(dipartimento);
		this.livello=livello;
		this.matricola=matricola;
		this.dipartimento=dipartimento;
	} 
	public void stampaDipendente() {
		System.out.println("il dipendente matricola: '"+getMatricola()+"' e' un "+getLivello()+" addetto alla "+getDipartimento()+" e ha uno stipendio di: "+getStipendio()+"$, e un importo per gli straordinari di: "+getImportoOrarioStraordianario()+
				"$ all'ora...");
	}
	
	public void calcolaPaga(double oreExtra) {
		double straordinari=oreExtra*(getImportoOrarioStraordianario());
		double cashh=straordinari +getStipendio();
		System.out.println(cashh+"$ di cui: "+straordinari+"$ per le "+oreExtra+" ore di straordinari");
	}
	///get and set
	public void setImportoOrarioStraordianario(double importoOrarioStraordianario) {
		this.importoOrarioStraordianario = importoOrarioStraordianario;
	}

	public void setDipartimento(String dipartimento) {
		this.dipartimento = dipartimento;
	}

	public double getStipendio() {
		if(livello==indice.OPERAIO) {
			stipendio=stipendioBase*1;
			
		}else if(livello==indice.IMPIEGATO) {
			stipendio=stipendioBase*1.2;
		}else if(livello==indice.QUADRO) {
			stipendio=stipendioBase*1.5;
		}else if(livello==indice.DIRIGENTE) {
			stipendio=stipendioBase*2;
		}else {
			stipendio=0;
		}
		return stipendio;
	}
	public void promote() {
		if(livello==indice.OPERAIO) {
			livello=indice.IMPIEGATO;
			
		}else if(livello==indice.IMPIEGATO) {
			livello=indice.QUADRO;
		}else if(livello==indice.QUADRO) {
			livello=indice.DIRIGENTE;
		}else {
			System.out.println("errore");
		}
	}

	public String getMatricola() {
		return matricola;
	}

	public double getImportoOrarioStraordianario() {
		return importoOrarioStraordianario;
	}

	public indice getLivello() {
		return livello;
	}

	public String getDipartimento() {
		return dipartimento;
	}
	public double getStipendioBase() {
		return stipendioBase;
	}
}

