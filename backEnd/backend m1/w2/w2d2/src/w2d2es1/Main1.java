package w2d2es1;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;


public class Main1 {

	public static void main(String[] args) {
		scanner();
	}
	public static void scanner() {
		Scanner s= new Scanner(System.in);
		int n = 0;
		String p;
		do{System.out.println("inserisci il numnero di elementi che vuoi creare");
		try{n=Integer.parseInt(s.nextLine());
		if(!(n>0)){
		System.out.println("inserire un numero maggiore di 0");}
		}catch(NumberFormatException e) {
			System.out.println("inserire un numero");}
		}while(!(n>0));
		
		Set<String>setLista=new HashSet<String>();
		Set<String>setDoppi=new HashSet<String>();
		for(int i=0;i<n;) {
			System.out.println("digita gli elementi da inserire");
			p=s.nextLine();
			
			if(setLista.contains(p)) {
				System.out.println("non sono consentiti duplicati");
				setDoppi.add(p);
			}else {
			setLista.add(p);
			i++;
			}
		}
		s.close();
		//stampa parole distinte
		System.out.println(setLista.toString());
		//stampa numero parole distinte
		System.out.println(setLista.size());
		//stampa parole provate a essere duplicate
		System.out.println(setDoppi.toString());
		
	}

}
