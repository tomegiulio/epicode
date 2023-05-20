package w2d1es2;

import java.util.Scanner;

public class Main2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		calcolcaKmPerLitro();
	}

	private static void calcolcaKmPerLitro() {
		int kmPercorsi = 0;
		int litriUsati = 0;
		Scanner s = new Scanner(System.in);

		try {
			System.out.println("inserire i kilometri efettuati");
			kmPercorsi = Integer.parseInt(s.nextLine());
		} catch (NumberFormatException e) {
			System.out.println("inserire un numero valido");
			System.exit(0);
		}
		if(kmPercorsi>0) {
		try {
			System.out.println("inserire i litri usati");
			litriUsati = Integer.parseInt(s.nextLine());
		} catch (NumberFormatException e) {
			System.out.println("inserire un numero valido");
			System.exit(0);
		}
		
		try {
			int kmPerLitro = kmPercorsi / litriUsati;
			System.out.println("hai fatto " + kmPerLitro + " chilometri per litro");
		} catch (ArithmeticException e) {
			System.out.println(kmPercorsi+" diviso 0= infinito");
			System.exit(0);
		}}else {
			System.out.print("inserisci un numero di kilometri superiore allo 0");
		}
		s.close();
	}

}
