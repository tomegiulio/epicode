package app;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import entities.evento;
import entities.location;
import entities.partecipazione;
import entities.persona;
import enumm.sex;
import enumm.stato;
import enumm.tipo;


public class Main {
	
	private static final String persistenceUnit="w3d3";
	private static final EntityManagerFactory emf=Persistence.createEntityManagerFactory(persistenceUnit);

	
	public static void main(String[] args) {
		//insertLocation("agordo","agordo bl");
		insertEvento("techno", LocalDate.of(2000, 10, 10), "techno after party papale", 200, tipo.PUBBLICO,1,4);
		//insertPartecipazione(stato.CONDERMATO,1,2);
		///insertPersona("ulio","tome","mrtome@mfsam",sex.M,LocalDate.of(2000, 11, 16),1);
	}
	
	
	
	//--------------METODI EVENTO-------------------------------------------------
	///------------add----------------
	public static void insertEvento(String titolo,LocalDate dataEvento,String descrizione,int numeroMassimoPartecipante,tipo tipoEvento,int locationid,int listaId) {
		final EntityManager em = emf.createEntityManager();
		final EntityTransaction t = em.getTransaction();
		location l=em.find(location.class, locationid);
		try {
			partecipazione list1=em.find(partecipazione.class, listaId);
			Set<partecipazione> listt= new HashSet<partecipazione> ();
			listt.add(list1);
			evento e=new evento();
			e.setTitolo(titolo);
			e.setDataEvento(dataEvento);
			e.setDescrizione(descrizione);
			e.setNumeroMassimoPartecipanti(numeroMassimoPartecipante);
			e.setTipoEvento(tipoEvento);
			e.setLocation(l);
			e.setSetPartecipazione(listt);
		
			
			t.begin();
			em.persist(e);
			t.commit();
			
		}catch(Exception e) {
			System.out.println("errore");
		}finally {
			em.close();
		}
	}
	
	////----------delete----------
	public static void deleteEvento(int id) {
		final EntityManager em = emf.createEntityManager();
		final EntityTransaction t = em.getTransaction();
		try {
			evento e=em.find(evento.class, id);
			t.begin();
			em.remove(e);
			t.commit();
		}catch(Exception e) {
			System.out.println("errore eliminazione");
		}finally {
			em.close();
		}
	}
	///-----------getbyid
	public static void getById(int id) {
		final EntityManager em = emf.createEntityManager();
		try {
			evento e=em.find(evento.class, id);
			System.out.println(e.getTitolo()+" "+e.getDescrizione()+" "+e.getDataEvento()+" "+e.getNumeroMassimoPartecipanti());
		}catch(Exception e) {
			System.out.println("errore ricerca");
		}finally {
			em.close();
		}
		
	}
	///-----------refresh
	public void refresh(evento object) {
		final EntityManager em = emf.createEntityManager();
		final EntityTransaction t = em.getTransaction();
		try {
			t.begin();
			em.refresh(object);
			t.commit();

		} finally {
			em.close();
		}

	}
	///----insertlocation
	public static void insertLocation(String name,String citta) {
		final EntityManager em = emf.createEntityManager();
		final EntityTransaction t = em.getTransaction();
		
		try {
			location lo=new location();
			lo.setName(name);
			lo.setCittà(citta);
			
		
			
			t.begin();
			em.persist(lo);
			t.commit();
			
		}catch(Exception e) {
			System.out.println("errore");
		}finally {
			em.close();
		}
	}
	public static void insertPersona(String name,String surname,String email,sex sesso,LocalDate data,int listaId) {
		final EntityManager em = emf.createEntityManager();
		final EntityTransaction t = em.getTransaction();
		
		try {
			partecipazione list=em.find(partecipazione.class, listaId);
			List<partecipazione> listt =  new ArrayList<>();
			listt.add(list);
			persona pe=new persona();
			pe.setName(name);
			pe.setSurname(surname);
			pe.setEmail(email);
			pe.setSesso(sesso);
			pe.setDataNascita(data);
			pe.setListaPartecipazioni(listt);
		
			
		
			
			t.begin();
			em.persist(pe);
			t.commit();
			
		}catch(Exception e) {
			System.out.println("errore");
		}finally {
			em.close();
		}
	}
	public static void insertPartecipazione( stato stato,int eventoId,int personaId) {
		final EntityManager em = emf.createEntityManager();
		final EntityTransaction t = em.getTransaction();
		evento ev=em.find(evento.class, eventoId);
		persona pers=em.find(persona.class, personaId);
		try {
			partecipazione par=new partecipazione();
			par.setEvento(ev);
			par.setPersona(pers);
			par.setStato(stato);
			
		
			
			t.begin();
			em.persist(par);
			t.commit();
			
		}catch(Exception e) {
			System.out.println("errore");
		}finally {
			em.close();
		}
	}
	
	
		
}
