package d3w1es1;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/// stringa dipari\pari
		pariDispari("s");
		// anno bisestile o no
		annobisestile(2024);
		
	}
	static void pariDispari(String a) {
		if (a.length() == 0) {
			System.out.println("il numero di caratteri che compongono la stringa e' zero");
		} else {
			if (a.length() % 2 == 0) {
				System.out.println("il numero di caratteri che compongono la stringa e' pari");
			} else {
				System.out.println("il numero di caratteri che compongono la stringa e' dispari");
			}
		}
	}
	
	static void annobisestile(int year) {
		if (year % 4 == 0) {
			if (year % 100 == 0) {
				if (year % 400 == 0) {
					System.out.println("il " + year + " e' un anno bisestile");
				} else {
					System.out.println("il " + year + " non e' un anno bisestile");
				}
			} else {
				System.out.println("il " + year + " e' un anno bisestile");
			}
		}else {
			System.out.println("il " + year + " non e' un anno bisestile");
		}
	}
}
