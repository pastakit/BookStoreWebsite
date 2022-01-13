package com.bookstore.dao;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class JpaDAO <T>{
	static EntityManagerFactory emf;
	protected EntityManager em;

	static {
		emf = Persistence.createEntityManagerFactory("BookStoreWebsite");
	}
	
	public JpaDAO() {
		//super();
	}	
	public T create(T t) {
		em = emf.createEntityManager();
		
		em.getTransaction().begin();
		
		em.persist(t);
		em.flush();
		em.refresh(t);
		
		em.getTransaction().commit();
		
		em.close();
		return t;
	}
	public T update(T entity) {
		em = emf.createEntityManager();

		em.getTransaction().begin();
		em.merge(entity);
		em.getTransaction().commit();
		
		em.close();
		return entity;
	}
	
	public T find(Class<T> type, Object id) {
		em = emf.createEntityManager();

		T entity  = em.find(type, id);
		if (entity != null) {
			em.refresh(entity);			
		}
		
		em.close();
		return entity;
	}
	public void delete(Class<T> type, Object id) {
		em = emf.createEntityManager();

		em.getTransaction().begin();
		
		Object ref = em.getReference(type, id);
		em.remove(ref);
		
		em.getTransaction().commit();
		
		em.close();
	}
	public List<T> findWithNamedQuery(String namedQuery){
		em = emf.createEntityManager();
		Query query = em.createNamedQuery(namedQuery);
		List<T> result = query.getResultList();

		em.close();
		return result;
	}
	
	// overload 
	public List<T> findWithNamedQuery(String namedQuery, int first, int max){
		em = emf.createEntityManager();
		Query query = em.createNamedQuery(namedQuery);
		
		query.setFirstResult(first);
		query.setMaxResults(max);
		List<T> result = query.getResultList();
		em.close();
		return result;
	}
	
	public List<T> findWithNamedQuery(String namedQuery, String paramName, Object paramValue){
		em = emf.createEntityManager();

		Query query = em.createNamedQuery(namedQuery);
		query.setParameter(paramName, paramValue);
		List<T> result = query.getResultList();

		em.close();
		return result;
	}
	
	// overload multiple params
	public List<T> findWithNamedQuery(String namedQuery, Map<String, Object> params){
		em = emf.createEntityManager();

		Query query = em.createNamedQuery(namedQuery);
		Set<Entry<String, Object>> paramSet = params.entrySet();
		for(Entry<String, Object> entry:paramSet) {
			query.setParameter(entry.getKey(), entry.getValue());
		}
		
		List<T> result = query.getResultList();
		em.close();
		return result;
	}
	

	
	
	public long countWithNamedQuery(String namedQuery) {
		em = emf.createEntityManager();

		
		Query query = em.createNamedQuery(namedQuery);
		long result = (long) query.getSingleResult();
		
		em.close();
		return result;
	}
	
	public void close() {
		if (emf!=null) {
			emf.close();
		}
	}
	
}
