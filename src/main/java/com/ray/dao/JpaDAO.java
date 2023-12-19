package com.ray.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.ray.config.HibernateSessionFactoryConfig;

public abstract class JpaDAO<T> {
	private SessionFactory sessionFactory;
	private Class<T> _genericClass; // _genericClass = User.Class || Product.Class

	public JpaDAO(Class<T> genericClass) {
		this.sessionFactory = HibernateSessionFactoryConfig.getSessionFactory();
		this._genericClass = genericClass;
	}
	
	// save to DB
	public T insert(T obj) {
		Transaction transaction = null;
		
		try (Session session = sessionFactory.openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			
			// write data to DB
			session.save(obj);
			
			// force to write obj to DB
			session.flush();
			
			// get saved obj data after writing to DB
			session.refresh(obj);
			
			// end transaction
			transaction.commit();
			
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback(); // do not save to db
			}
			e.printStackTrace();
		}
		return obj;
	}
	
	// update
	public T update(T obj) {
		Transaction transaction = null;
		
		try (Session session = sessionFactory.openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			
			// update data
			session.update(obj);
			
			// end transaction
			transaction.commit();
			
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback(); // do not save to db
			}
			e.printStackTrace();
		}
		return obj;
	}
	
	// find one
	public T findOne(Object objId) {
		Transaction transaction = null;
		Object queryObj = null;
		T result = null;
		
		try (Session session = sessionFactory.openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			
			// Class<T> queryObj
			queryObj = session.get(_genericClass, (Serializable) objId);
			
			// Class<T> result --> T
			result = _genericClass.cast(queryObj);
			
			// end transaction
			transaction.commit();
			
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback(); // do not save to db
			}
			e.printStackTrace();
		}
		return result;
	}
	
	// find all
	public List<T> finAll() {
		return null;
	}
	
	// delete
	public void delete(T obj) {}
	
}
