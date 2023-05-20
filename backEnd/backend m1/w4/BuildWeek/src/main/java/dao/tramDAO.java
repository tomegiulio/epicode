package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import entities.tram;
import utils.JpaUtil;

public class tramDAO {
private static final Logger logger = LoggerFactory.getLogger(tramDAO.class);
	
	
	
	///save//////////////////////////////////////////////////////////////
	public void save(tram object) {
    EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
    try {

        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        em.persist( object );

        transaction.commit();
    } catch( Exception ex ) {
        em.getTransaction().rollback();

        logger.error( "Error saving object: " + object.getClass().getSimpleName(), ex );
        throw ex;

    } finally {
        em.close();
    }
    }
}

