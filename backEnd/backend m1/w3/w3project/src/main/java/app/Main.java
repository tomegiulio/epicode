package app;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import entities.Book;
import entities.Prestito;
import entities.Utente;
import enumm.per;

public class Main {
	private static final String persistenceUnit = "w3project";
	private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory(persistenceUnit);

	public static void main(String[] args) {
		// insertUser();
		 //insertBook();
		 //insertLoan(1,1);
		//finishLoan(1);
		///removeBook(1);
		//getByAutore("JK Rowling");
		//getByCodeId(1);
		//getByTitle("HarryPotter1");
		getByScaduti();

	}

////----------------------------------book----------------------------------------------------------------------
	public static void insertBook() {
		final EntityManager em = emf.createEntityManager();
		final EntityTransaction t = em.getTransaction();

		try {

			Book book = new Book("HarryPotter1", 2000, 260, "JK Rowling", "Fantasy", per.SEMESTRALE);

			t.begin();
			em.persist(book);
			t.commit();

		} catch (Exception e) {
			System.out.println("errore");
		} finally {
			em.close();
		}
	}

	public static void removeBook(int bookCode) {
		final EntityManager em = emf.createEntityManager();
		final EntityTransaction t = em.getTransaction();
		t.begin();
		try {
			Query q = em.createQuery("DELETE Book b WHERE b.id=:d ");
			q.setParameter("d", bookCode);
			q.executeUpdate();

		} catch (Exception e) {
			System.out.println("il libro e' al momento in prestito");
		}
		t.commit();
	}

	public static void getByAutore(String autore) {
		final EntityManager em = emf.createEntityManager();
		try {
			Query q = em.createQuery("SELECT b.titolo FROM Book b WHERE b.autore=:d");
			 q.setParameter("d",autore);
			 @SuppressWarnings("unchecked")
			List<Book> resultList=q.getResultList();
			 System.out.println("LIBRO: "+resultList);

		} catch (Exception e) {
			System.out.println("errore query autore search book");
		}
	}
	public static void getByCodeId(int idd) {
		final EntityManager em = emf.createEntityManager();
		try {
			Query q = em.createQuery("SELECT b.titolo FROM Book b WHERE b.id=:d");
			 q.setParameter("d",idd);
			 @SuppressWarnings("unchecked")
			List<Book> resultList=q.getResultList();
			 System.out.println("LIBRO: "+resultList);

		} catch (Exception e) {
			System.out.println("errore query autore search book");
		}
	}
	public static void getByYear(int year) {
		final EntityManager em = emf.createEntityManager();
		try {
			Query q = em.createQuery("SELECT b.titolo FROM Book b WHERE b.annopubb=:d");
			 q.setParameter("d",year);
			 @SuppressWarnings("unchecked")
			List<Book> resultList=q.getResultList();
			 System.out.println("LIBRO: "+resultList);

		} catch (Exception e) {
			System.out.println("errore query autore search book");
		}
	}
	public static void getByTitle(String title) {
		final EntityManager em = emf.createEntityManager();
		try {
			Query q = em.createQuery("SELECT b.id FROM Book b WHERE b.titolo=:d");
			 q.setParameter("d",title);
			 @SuppressWarnings("unchecked")
			List<Book> resultList=q.getResultList();
			 System.out.println("LIBRO: "+resultList);

		} catch (Exception e) {
			System.out.println("errore query autore search book");
		}
	}
	public static void getByScaduti() {
		final EntityManager em = emf.createEntityManager();
		try {
			Query q = em.createQuery("SELECT p FROM Prestito p");
			 @SuppressWarnings("unchecked")
			List<Prestito> resultList=q.getResultList();
			 List<Prestito> finale= new ArrayList<>();
			 boolean d;
			 LocalDate ld=LocalDate.now();
			 for( Prestito p : resultList ) {
				 LocalDate ld2=p.getLoanMaxTime();
				  d=ld2.isBefore(ld);
				 if( d=true )  {
					 finale.add(p);
	                }
				 for(Prestito p2:finale) {
				 System.out.println("prestiti scaduti:"+p2.getId());
				 }
	                	
	                	
	                	
	                
	            }

		} catch (Exception e) {
			System.out.println("errore query scaduti search book");
		}
	}
//-----------------------------------------user----------------------------------------------------------------
	public static void insertUser() {
		final EntityManager em = emf.createEntityManager();
		final EntityTransaction t = em.getTransaction();

		try {
			Utente user = new Utente("Giulio", "Tome", LocalDate.of(2000, 11, 16));

			t.begin();
			em.persist(user);
			t.commit();

		} catch (Exception e) {
			System.out.println("errore");
		} finally {
			em.close();
		}
	}

////----------------loan----------------------------------------------------------------------------------------
	public static void insertLoan(int userId, int bookId) {
		final EntityManager em = emf.createEntityManager();
		final EntityTransaction t = em.getTransaction();

		try {
			LocalDate today = LocalDate.now();
			Prestito loan = new Prestito(today);
			Book b = em.find(Book.class, bookId);
			Utente u = em.find(Utente.class, userId);
			loan.setLoanMaxTime(today.plusDays(30));
			loan.setUtente(u);
			loan.setLibro(b);
			b.setDisponibile(false);

			t.begin();
			em.persist(loan);
			t.commit();

		} catch (Exception e) {
			System.out.println("errore");
		} finally {
			em.close();
		}
	}

	public static void finishLoan(int loanId) {
		final EntityManager em = emf.createEntityManager();
		final EntityTransaction t = em.getTransaction();

		try {
			LocalDate today = LocalDate.now();

			Prestito pre = em.find(Prestito.class, loanId);

			pre.setLoanFinish(today);
			try {
				Book bookId = pre.getLibro();
				bookId.setDisponibile(true);
			} catch (Exception e) {
				System.out.print("libro rimosso dal catalogo");
			}

			t.begin();
			em.persist(pre);
			t.commit();

		} catch (Exception e) {
			System.out.println("errore");
		} finally {
			em.close();
		}
	}

}
/*

 
 * di tessera utente - Ricerca di tutti i prestiti scaduti e non ancora
 * restituiti
 */