package w2f;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class Main {
	public static Book[] catalogo;
	public static File file1 = new File("file.txt");

	public static void main(String[] args) {
		Book b1 = new Book("fdsa", "dioo", 2010, 100, "ulio", "thriller", period.MENSILE);
		Book b2 = new Book("fdsad", "letskere", 2022, 100, "hon", "thriller", period.SEMESTRALE);
		Book b3 = new Book("123", "letskere", 2022, 100, "ulio", "thriller", period.SEMESTRALE);
		Book b34 = new Book("1233", "letskere", 2022, 100, "ulio", "thriller", period.SEMESTRALE);
		catalogo = new Book[100];
		addToArr(b1);
		addToArr(b2);

		getInfoCode("fdsa");
		getInfoAutore("ulio");
		getInfoAnno(2022);
		removeBook("fdsa");
		addToArr(b3);
		addToArr(b34);
		getInfoAutore("ulio");
		getInfoCode("123");
		getInfoAutore("hon");
		getInfoAnno(2022);
		try {
			addToFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		leggi();

	}

	// aggiungi oggetto al catalogo
	public static void addToArr(Book object) {
		int x = 0;
		try {
			for (int i = 0; i < catalogo.length; i++) {
				String myCode = object.getIsbn();
				String arrayCodes = catalogo[i].getIsbn();
				if (!(myCode == catalogo[i].getIsbn())) {
					if (!(arrayCodes == null)) {
						x++;
					} else {
					}
				} else {
					System.out.println("errore scegliere un altro codice ISBN");
					System.exit(0);
				}
			}
		} catch (Exception e) {
			System.out.println("inserimento in corso...");
		}
		catalogo[x] = object;
	}

	// ricerca info per codice
	public static void getInfoCode(String cod) {
		try {
			Stream<Book> rightCode = Arrays.stream(catalogo);
			Predicate<Book> book = items -> items.getIsbn() == (cod);
			rightCode.filter(book)
					.forEach(items -> System.out.println("-codice: " + items.getIsbn() + " -titolo: "
							+ items.getTitolo() + " -anno: " + items.getAnnoPubblicazione() + " -pagine: "
							+ items.getNumPagine() + " -autore: " + items.getAutore() + " -genere: " + items.getGenere()
							+ " -periodicita: " + items.getPeriodicita()));
		} catch (Exception e) {
			System.out.println();
		}
	}

	// ricerca per autore
	public static void getInfoAutore(String cod) {
		try {
			Stream<Book> rightCode = Arrays.stream(catalogo);
			Predicate<Book> book = items -> items.getAutore() == (cod);
			rightCode.filter(book)
					.forEach(items -> System.out.println("-codice: " + items.getIsbn() + " -titolo: "
							+ items.getTitolo() + " -anno: " + items.getAnnoPubblicazione() + " -pagine: "
							+ items.getNumPagine() + " -autore: " + items.getAutore() + " -genere: " + items.getGenere()
							+ " -periodicita: " + items.getPeriodicita()));
		} catch (Exception e) {
			System.out.println("");
		}
	}

	// ricerca per anno
	public static void getInfoAnno(int cod) {
		try {
			Stream<Book> rightCode = Arrays.stream(catalogo);
			Predicate<Book> book = items -> items.getAnnoPubblicazione() == (cod);
			rightCode.filter(book)
					.forEach(items -> System.out.println("-codice: " + items.getIsbn() + " -titolo: "
							+ items.getTitolo() + " -anno: " + items.getAnnoPubblicazione() + " -pagine: "
							+ items.getNumPagine() + " -autore: " + items.getAutore() + " -genere: " + items.getGenere()
							+ " -periodicita: " + items.getPeriodicita()));
		} catch (Exception e) {
			System.out.println("");
		}
	}

	// rimozione elemento per codice
	public static void removeBook(String cod) {
		try {
			Stream<Book> rightCode = Arrays.stream(catalogo);
			Predicate<Book> book = items -> items.getIsbn() == cod;
			rightCode.filter(book).forEach(items -> items.remove());

		} catch (Exception e) {
			System.out.println("");
		}
	}

	// metti array nel file.txt
	public static void addToFile() throws IOException {
		String textt = "";

		try {
			for (int i = 0; i < catalogo.length; i++) {
				String text = ("-" + catalogo[i].getIsbn() + "-" + catalogo[i].getTitolo() + "-"
						+ catalogo[i].getAnnoPubblicazione() + "-" + catalogo[i].getNumPagine() + "-"
						+ catalogo[i].getAutore() + "-" + catalogo[i].getGenere() + "-" + catalogo[i].getPeriodicita()
						+ "\n");
				textt = textt + text;
				print(textt);

			}
		} catch (Exception e) {
			System.out.println("");
		}

	}

	public static void print(String txt) throws IOException {

		FileWriter fw = new FileWriter(file1);
		try (PrintWriter pw = new PrintWriter(fw)) {
			pw.write(txt);

		} catch (Exception e) {

		}

	}

	// crea array con file txt
	public static void leggi() {

		String databaseString = null;
		String[] arrOfItems = null;
		// Book[] newArr=new Book[100];
		if (file1.exists()) {
			try {
				Scanner s = new Scanner(new File("file.txt"));
				// String databaseString =FileUtils.re(file1, encoding );
				databaseString = s.useDelimiter("\\A").next();
				;

				arrOfItems = databaseString.split("/\n");
			} catch (Exception e) {
				System.out.println("Qualcosa è andato storto nel recupero delle risorse");
			}

			System.out.println(arrOfItems[0]);
			/*
			 * try { for( String item : arrOfItems ) { String[] stringaSplit = item.split(
			 * "-" );
			 * 
			 * for( int i = 0 ; i < stringaSplit.length ; i++ ) {
			 * 
			 * 
			 * Book obj = new Book(stringaSplit[ 0 ],stringaSplit[ 1
			 * ],Integer.parseInt(stringaSplit[ 2 ] ),Integer.parseInt( stringaSplit[ 3 ]
			 * ),stringaSplit[ 4 ], stringaSplit[ 5 ],period.valueOf(stringaSplit[6]));
			 * System.out.println(obj); newArr[i]=obj;
			 * 
			 * 
			 * } } }catch(Exception e) {
			 * 
			 * } System.out.println(newArr[0].getAutore());
			 */
		}
	}
}
