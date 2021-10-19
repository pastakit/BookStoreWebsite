package com.bookstore.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class BaseDAOTest {
	protected static EntityManagerFactory emf;
	protected static EntityManager em;
	
	public static void setUpClass() {
		emf = Persistence.createEntityManagerFactory("BookStoreWebsite");
		em = emf.createEntityManager();
	}
	
	public static void tearDownClass() {
			em.close();
			emf.close();
	}
}
