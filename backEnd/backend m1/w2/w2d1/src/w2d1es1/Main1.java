package w2d1es1;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main1 {

	public static void main(String[] args) {
		randomGenerator();
		
	}
	
	
	private static void randomGenerator() {
		Random rand = new Random();
		int i1=rand.nextInt(10);
		int i2=rand.nextInt(10);
		int i3=rand.nextInt(10);
		int i4=rand.nextInt(10);
		int i5=rand.nextInt(10);
		int i6=1;
		int posizione=0;
		int[] arr= {i1,i2,i3,i4,i5}; 
		System.out.println("I 5 numeri generati sono: "+Arrays.toString(arr));
		Scanner s=new Scanner(System.in);
		do {
		System.out.println("Inserieci un numero da 1 a 10"+ "\n" +"o il numero 0 per uscire");
		i6=Integer.parseInt(s.nextLine());
		if(!(i6==0)){
			if(i6<=10) {
		System.out.println("Inserieci la posizione del tua numero: "+i6+" per inserirlo nell' array digitando un numero intero da 1 a 6");
		posizione=Integer.parseInt(s.nextLine());
		switch(posizione) {
			case 1:
				int[] arr2= {i6,i1,i2,i3,i4,i5};
				System.out.println("Inserimento compiuto "+Arrays.toString(arr2));
				break;
			case 2:
				int[] arr3= {i1,i6,i2,i3,i4,i5};
				System.out.println("Inserimento compiuto "+Arrays.toString(arr3));
				break;
			case 3:
				int[] arr4= {i1,i2,i6,i3,i4,i5};
				System.out.println("Inserimento compiuto "+Arrays.toString(arr4));
				break;
			case 4:
				int[] arr5= {i1,i2,i3,i6,i4,i5};
				System.out.println("Inserimento compiuto "+Arrays.toString(arr5));
				break;
			case 5:
				int[] arr6= {i1,i2,i3,i4,i6,i5};
				System.out.println("Inserimento compiuto "+Arrays.toString(arr6));
				break;
			case 6:
				int[] arr7= {i1,i2,i3,i4,i5,i6};
				System.out.println("Inserimento compiuto "+Arrays.toString(arr7));
				break;
			default:
				System.out.println("errore inserire un posione valida");
				break;
		}}else {
			System.out.println("inserire un valore valido");
		}
			}}while(!(i6==0));
		System.out.println("uscito dall loop");
		s.close();
		}
			
		}
		
		
	


