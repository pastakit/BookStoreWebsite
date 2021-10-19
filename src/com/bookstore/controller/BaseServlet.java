package com.bookstore.controller;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//@WebServlet("/")
public abstract class BaseServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	protected EntityManagerFactory emf;
	protected EntityManager em;
	
	
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		//super.init();
		emf = Persistence.createEntityManagerFactory("BookStoreWebsite");
		em = emf.createEntityManager();
	}
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		em.close();
		emf.close();
		//super.destroy();

	}
	
}
