package w2f;

public class Book {
	private String isbn;
	private String titolo;
	private int annoPubblicazione;
	private int numPagine;
	private String autore;
	private String genere;
	private period periodicita;
	
	public Book(String isbn,String titolo,int annoPubblicazione,
	int numPagine,String autore,String genere,period periodicita) {
		setIsbn(isbn);
		setTitolo(titolo);
		setAnnoPubblicazione(annoPubblicazione);
		setNumPagine(numPagine);
		setAutore(autore);
		setGenere(genere);
		setPeriodicita(periodicita);
		}
	
	
	
	public void remove() {
		setIsbn("");
		setTitolo(null);
		setAnnoPubblicazione(0);
		setNumPagine(0);
		setAutore(null);
		setGenere(null);
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//getter and setters
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getTitolo() {
		return titolo;
	}
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	public int getAnnoPubblicazione() {
		return annoPubblicazione;
	}
	public void setAnnoPubblicazione(int annoPubblicazione) {
		this.annoPubblicazione = annoPubblicazione;
	}
	public int getNumPagine() {
		return numPagine;
	}
	public void setNumPagine(int numPagine) {
		this.numPagine = numPagine;
	}
	public String getAutore() {
		return autore;
	}
	public void setAutore(String autore) {
		this.autore = autore;
	}
	public String getGenere() {
		return genere;
	}
	public void setGenere(String genere) {
		this.genere = genere;
	}
	public period getPeriodicita() {
		return periodicita;
	}
	public void setPeriodicita(period periodicita) {
		this.periodicita = periodicita;
	}
	


}
