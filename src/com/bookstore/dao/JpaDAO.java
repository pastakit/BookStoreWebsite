package com.bookstore.dao;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class JpaDAO <T>{
	protected EntityManager entityManager;

	public JpaDAO(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}	
	public T create(T t) {
		entityManager.getTransaction().begin();
		
		entityManager.persist(t);
		entityManager.flush();
		entityManager.refresh(t);
		
		entityManager.getTransaction().commit();
		
		return t;
	}
	public T update(T entity) {
		entityManager.getTransaction().begin();
		entityManager.merge(entity);
		entityManager.getTransaction().commit();
		
		return entity;
	}
	
	public T find(Class<T> type, Object id) {
		T entity  = entityManager.find(type, id);
		if (entity != null) {
			entityManager.refresh(entity);			
		}

		return entity;
	}
	public void delete(Class<T> type, Object id) {
		entityManager.getTransaction().begin();
		
		Object ref = entityManager.getReference(type, id);
		entityManager.remove(ref);
		
		entityManager.getTransaction().commit();
	}
	public List<T> findWithNamedQuery(String namedQuery){
		Query query = entityManager.createNamedQuery(namedQuery);
		return query.getResultList();
	}
	
	public List<T> findWithNamedQuery(String namedQuery, String paramName, Object paramValue){
		Query query = entityManager.createNamedQuery(namedQuery);
		query.setParameter(paramName, paramValue);
		return query.getResultList();
	}
	
	// overload multiple params
	public List<T> findWithNamedQuery(String namedQuery, Map<String, Object> params){
		Query query = entityManager.createNamedQuery(namedQuery);
		Set<Entry<String, Object>> paramSet = params.entrySet();
		for(Entry<String, Object> entry:paramSet) {
			query.setParameter(entry.getKey(), entry.getValue());
		}
		return query.getResultList();
	}
	
	
	public long countWithNamedQuery(String namedQuery) {
		Query query = entityManager.createNamedQuery(namedQuery);
		long result = (long) query.getSingleResult();
		return result;
	}
	
}
