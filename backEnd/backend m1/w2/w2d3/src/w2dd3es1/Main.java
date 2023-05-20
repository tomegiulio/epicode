package w2dd3es1;


import java.util.*;
public class Main{
	protected static int[] lista=new int[3000];
	public static void main(String[] args) {
		//creazione numeri
		System.out.println("generezione dei 3000 numeri");
		createNumber();
		System.out.println(lista.length);
		//somma
		System.out.println("somma dei 3000 numeri");
		sommaArray();
		//somma del threat1
		Thread tmain=Thread.currentThread();
		System.out.println("Current thread: "+ tmain.getName());
		tmain.setPriority(10);
		System.out.println("Main thread priority: "+tmain.getPriority());
		////thread
		threads t1=new threads(0,1000,lista);
		t1.run();
		threads t2=new threads(1000,2000,lista);
		t2.run();
		threads t3=new threads(2000,3000,lista);
		t3.run();
	}
	
	
	public static void createNumber() {
		for(int i=0;i<3000;i++) {
			int i1=	(int)Math.floor(Math.random()*11);
			lista[i]=i1;}
			System.out.println(Arrays.toString(lista));
	}
	public static void sommaArray() {
		 int sum = Arrays.stream(lista).sum();
		 System.out.println(sum);
	}
}
