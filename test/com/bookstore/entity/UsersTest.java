package com.bookstore.entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.bookstore.entity.Users;

public class UsersTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Users user1 = new Users();
		user1.setEmail("email1@gmail.com");
		user1.setFullName("fullname1");
		user1.setPassword("password1");
		 
		EntityManagerFactory  emf = Persistence.createEntityManagerFactory("BookStoreWebsite");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		em.persist(user1);
		em.getTransaction().commit();
		em.close();
		emf.close();
		System.out.println("A Users object was persisted");
	}

}
