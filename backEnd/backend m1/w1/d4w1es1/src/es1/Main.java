package es1;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Dipendente d1=new Dipendente("1231","dasd");
		Dipendente d2=new Dipendente("GIU",indice.IMPIEGATO,"dasdsa");
		Dipendente d3=new Dipendente("MAX",indice.QUADRO,"dasdsa");
		Dipendente d4=new Dipendente("JHONNY",indice.DIRIGENTE,"dasdsa");
		//stampa dei dati dipendenti
		d1.stampaDipendente();
		d2.stampaDipendente();
		d3.stampaDipendente();
		d4.stampaDipendente();
	
		//array paghe
		System.out.println("");
		System.out.println("");
		Dipendente[] dipendenti ={d1,d2,d3,d4};
		for(Dipendente d: dipendenti) {
		d.calcolaPaga(5);
		}
		//promozioni
		System.out.println("");
		System.out.println("");
		d1.stampaDipendente();
		d2.stampaDipendente();
		System.out.println("promozioni eseguite");
		System.out.println("");
		d1.promote();
		d2.promote();
		d1.stampaDipendente();
		d2.stampaDipendente();
			
	}
		
}
