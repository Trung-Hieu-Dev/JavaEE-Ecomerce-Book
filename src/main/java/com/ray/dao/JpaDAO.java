package com.ray.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

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
				transaction.rollback(); // do not save to db if not success
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
				transaction.rollback(); // do not save to db if not success
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
				transaction.rollback(); // do not save to db if not success
			}
			e.printStackTrace();
		}
		return result;
	}
	
	// find all
	public List<T> finAll() {
		Transaction transaction = null;
		List<T> objectList = null;
		
		try (Session session = sessionFactory.openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			
			// get list
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<T> criteria = builder.createQuery(_genericClass); ///_genericClass = User.class
			Root<T> root = criteria.from(_genericClass); ///  FROM User
			criteria.select(root); /// select * 
			
			Query<T> query = session.createQuery(criteria);
			objectList = query.getResultList();
			
			// end transaction
			transaction.commit();
			
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback(); // do not save to db if not success
			}
			e.printStackTrace();
		}
		return objectList;
	}
	
	// delete
	public void delete(T objId) {
		Transaction transaction = null;
		Object queryObj = null;
		
		try (Session session = sessionFactory.openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			
			// get obj to delete
			queryObj = session.get(_genericClass, (Serializable) objId);
			
			// delete
			if (queryObj != null) {
				session.delete(queryObj);
				System.out.println("Delete succcessfully!");
			}
			
			// end transaction
			transaction.commit();
			
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback(); // do not save to db if not success
			}
			e.printStackTrace();
		}
	}
	
	public long getTotalRecord() {
		Transaction transaction = null;
		long totalRecord = 0;
		
		try (Session session = sessionFactory.openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			
			// get total record
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
			Root<T> root = criteria.from(_genericClass); ///  FROM User
			criteria.select(builder.count(root)); /// select count(*)
			
			Query<Long> query = session.createQuery(criteria);
			totalRecord = query.getSingleResult();
			
			// end transaction
			transaction.commit();
			
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback(); // do not save to db if not success
			}
			e.printStackTrace();
		}
		return totalRecord;
	}
	
	
	
}
