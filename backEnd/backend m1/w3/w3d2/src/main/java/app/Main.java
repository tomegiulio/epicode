package app;

import java.time.LocalDate;

import javax.persistence.*;

import entities.evento;
import entities.tipo;


public class Main {

	private static final String persistenceUnit="w3d2";
	private static final EntityManagerFactory emf=Persistence.createEntityManagerFactory(persistenceUnit);
	private static final EntityManager em = emf.createEntityManager();
	private static final EntityTransaction t = em.getTransaction();
	
	
	
	
	
	public static void main(String[] args) {
		getById(2);
		editNumeroMassimoById(2,20000);

	}
	public static void insertEvento(String titolo,LocalDate dataEvento,String descrizione,int numeroMassimoPartecipante,tipo tipoEvento ) {
		try {
			evento e=new evento();
			e.setTitolo(titolo);
			e.setDataEvento(dataEvento);
			e.setDescrizione(descrizione);
			e.setNumeroMassimoPartecipanti(numeroMassimoPartecipante);
			e.setTipoEvento(tipoEvento);
			
			t.begin();
			em.persist(e);
			t.commit();
		}catch(Exception e) {
			System.out.println("errore");
		}
	}
	public static void deleteEvento(int id) {
		try {
			evento e=em.find(evento.class, id);
			EntityTransaction transaction=em.getTransaction();
			transaction.begin();
			em.remove(e);
			transaction.commit();
		}catch(Exception e) {
			System.out.println("errore eliminazione");
		}
	}
	public static void getById(int id) {
		try {
			evento e=em.find(evento.class, id);
			System.out.println(e.getTitolo()+" "+e.getDescrizione()+" "+e.getDataEvento()+" "+e.getNumeroMassimoPartecipanti());
		}catch(Exception e) {
			System.out.println("errore ricerca");
		}
	}
	public static void editNumeroMassimoById(int id,int n) {
		try {
			evento e=em.find(evento.class, id);
			System.out.println(e.getNumeroMassimoPartecipanti());
			e.setNumeroMassimoPartecipanti(n);
			em.refresh(e);
			System.out.println(e.getNumeroMassimoPartecipanti());
		}catch(Exception e) {
			System.out.println("Errore edit");
		}

}
	}
