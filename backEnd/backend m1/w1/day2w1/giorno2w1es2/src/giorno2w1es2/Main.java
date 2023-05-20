package giorno2w1es2;

public class Main {
	public static void main(String[] args){
		//creazione della sim
		timclass uliosium=new timclass(3383283283l,0,"");
		//informazioni sim
		datiSim(uliosium.numero,uliosium.credito,uliosium.chiamate);
		//ricarica
		uliosium.credito=ricarica(uliosium.credito,10);
		datiSim(uliosium.numero,uliosium.credito,uliosium.chiamate);
		//chiamata 
		uliosium.credito=uliosium.credito-chiamata(2112313144,12);
		uliosium.chiamate=uliosium.chiamate+("\n numero chimato: 2112313144 durata della chiamata: 12 minuiti");
		uliosium.credito=uliosium.credito-chiamata(2112313144,12);
		uliosium.chiamate=uliosium.chiamate+("\n numero chimato: 2112313144 durata della chiamata: 12 minuiti");
		;
		
		
		datiSim(uliosium.numero,uliosium.credito,uliosium.chiamate);
		
	}
	
	static void datiSim(long numero,double credito,String chiamate) {
	
	if(chiamate=="") {
		System.out.println("il tuo numero e' : "+numero+" e il credito residuo e' di: "+credito+"$ e il registro delle chiamate e' vuoto");
	}else {
		System.out.println("il tuo numero e' : "+numero+" e il credito residuo e' di: "+credito+"$ e il registro delle chiamate e': "+chiamate);
	}}
	
	static double ricarica(double credito,double importo){
		credito=credito+importo;
		
		return credito;
	}
	static double chiamata(long numeroChiamata,double minuti) {
		double costoPerMinuto=0.20;
		double costo=minuti*costoPerMinuto;
		return costo;
		
	}
	}
	

