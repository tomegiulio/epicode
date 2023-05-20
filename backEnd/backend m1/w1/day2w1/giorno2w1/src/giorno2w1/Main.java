package giorno2w1;

public class Main {
	public static void main(String[] args){
		//creazione dei rettangoli
		rettangoli rettangolo1=new rettangoli(2.5,1.5);
		rettangoli rettangolo2=new rettangoli(10,20);
		//area rettangolo 1 e 2
		arearettangolo(rettangolo1.altezza,rettangolo1.larghezza ); 
		arearettangolo(rettangolo2.altezza,rettangolo2.larghezza );
		//perimetro rettangolo 1 e 2
		perimetrorettangolo(rettangolo1.altezza,rettangolo1.larghezza ); 
		perimetrorettangolo(rettangolo2.altezza,rettangolo2.larghezza );
		//dati rettangolo 1 e 2
		datirettangolo(rettangolo1.altezza,rettangolo1.larghezza );
		datirettangolo(rettangolo2.altezza,rettangolo2.larghezza ); 
		///dati rettangoli sommati
		datirettangoli(rettangolo1.altezza,rettangolo1.larghezza,rettangolo2.altezza,rettangolo2.larghezza);
	 }
	static void arearettangolo(double altezza,double larghezza) {
		 System.out.println((altezza*larghezza)+" cm2");
	 }
	static void perimetrorettangolo(double altezza,double larghezza) {
		 System.out.println((altezza+larghezza)*2+" cm");
	 }
	static void datirettangolo(double altezza,double larghezza) {
		double area=(altezza*larghezza);
		double perimetro=((altezza+larghezza)*2);
		System.out.println("un rettangolo con lati di: "+altezza+"cm e di "+larghezza+"cm ha un area di: "+ area+"cm2 e un perimetro di "+
		perimetro+"cm");
	}
	static void datirettangoli(double altezza,double larghezza,double altezza2,double larghezza2) {
		double area=(altezza*larghezza);
		double perimetro=((altezza+larghezza)*2);
		
		double area2=(altezza2*larghezza2);
		double perimetro2=((altezza2+larghezza2)*2);
		
		double perimetrototale=perimetro+perimetro2;
		double areatotale=area+area2;
		
		System.out.print("un rettangolo con lati di: "+altezza+"cm e di "+larghezza+"cm ha un area di: "+ area+"cm2 e un perimetro di "+
				perimetro+"cm invece un rettangolo con lati di: "+altezza2+"cm e di "+larghezza2+"cm ha un area di: "+ area2+"cm2 e un perimetro di "+
				perimetro2+"cm... la somma totale dell area dei due quadrati e' di: "+areatotale+"cm2 invece la somma dei perimetri equivale a "+perimetrototale+"cm");
	}
	
}
