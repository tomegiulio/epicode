package it.epicode.be;

import java.util.Scanner;

public class Helloworld {

public static void main (String[] args)
		{
		///default start
		System.out.println("This is my first Epicode Java Project!");
		///moltiplicazione
		int x = 10;
		int y= 10;
		System.out.println(x*y);	
		
		//concatena stringa e intero
		int t=34;
		int g=3;
		int r=2;
		System.out.println(t+" gatti in fila per "+g+" con resto di "+r); 
		
		//array
		String numeri[]=new String[5];
		numeri[0]="1";
		numeri[1]="2";
		numeri[2]="3";
		numeri[3]="4";
		numeri[4]="5";
		String nome[]=new String[1];
		nome[0]="giulio";
		System.out.println(numeri[0]+" "+ numeri[1]+" "+ numeri[2]+" "+ nome[0]+" "+ numeri[3]+" "+ numeri[4]);
		
		//scanner nome
		System.out.println("inserire in ordine: il tuo nome, cognome e email confermando con invio");
		try (Scanner scanner = new Scanner(System.in)) {
			String name = scanner.nextLine();
			scanner.close();
			System.out.println("i dati inseriti sono: "+name);
			
		}
		
		
		
		///perimetro rettangolo
		double a=  4.5;
		double b =  2.5;
		System.out.println("il perimetro di un rettangolo con lati di "+a+" cm e di "+b+ " cm e' di "+ (a+b)*2 +" cm" );
		
		//pari e dispari
		int n=10;
		
		if((n % 2)==0) {
			System.out.println(n+ " e' un numero pari");
		}else {
			System.out.println(n+ " e' un numero dispari");
		}
		
		//area triangolo equilatero
		
		System.out.println("l'area di un triangolo quadrilatero con lati di "+a+" cm e di "+b+ " cm e' di "+ (a*b)/2 +" cm2" );
		

		

		}
}

	


