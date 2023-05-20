package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import entities.user;
import utils.JpaUtil;

public class userDAO {
	private static final Logger logger = LoggerFactory.getLogger(userDAO.class);
	
	
	
	///save//////////////////////////////////////////////////////////////
	public void save(user object) {
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
	//refresh////////////////////////////////////////////////////////
	public void refresh(user object) {
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		try {
			em.refresh(object);
		} finally {
			em.close();
		}
	}
	///getbyid////////////////////////////////////////////////////////////////
	public user getById(int id) {
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		try {

			return em.find(user.class, id);

		} finally {
			em.close();
		}

	}
	//////delete/////////////////////////////////////////////////////////////////
	public void delete(user object) {
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		try {

			EntityTransaction transaction = em.getTransaction();
			transaction.begin();

			em.remove(object);

			transaction.commit();
		} catch (Exception ex) {
			em.getTransaction().rollback();
			logger.error("Error deleting object: " + object.getClass().getSimpleName(), ex);
			throw ex;

		} finally {
			em.close();
		}

	}
}
