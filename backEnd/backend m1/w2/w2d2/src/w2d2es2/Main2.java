package w2d2es2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main2 {

	public static void main(String[] args) {
		scann();
	}
	
	
	public static void scann(){
		System.out.println("inserire un numero di numeri casuali da 0 a 100 che si vogliono generare");
		Scanner s =new Scanner(System.in);
		int n=0;
		///numero di numeri da generare scann
		do{try{
			n=Integer.parseInt(s.nextLine());
			}catch(NumberFormatException e) {
			System.out.println("inserire un numero intero sopra lo zero");}}while(!(n>0));
		//generazioen numeri
		List<String>lista1=new ArrayList<String>();
		for(int i=0;i<n;i++) {
			int i1=	(int)Math.floor(Math.random()*100);
			String str=String.valueOf(i1);
			lista1.add(str);
			}
		System.out.println("lista numero 1: ");
		System.out.println(lista1);
		System.out.println("lista al contrario: ");
		Collections.reverse(lista1);
		System.out.println(lista1);
		s.close();
		for(int i=0;i<lista1.size();i++) {
			if(i%2==0) {
				System.out.println("indice pari");
				System.out.println(lista1.get(i));
			}else {
				System.out.println("indice dispariiii");
				System.out.println(lista1.get(i));
		}
	}}}
