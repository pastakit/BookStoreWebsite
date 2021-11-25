package com.bookstore.dao;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.entity.Book;
import com.bookstore.entity.Category;
import com.bookstore.entity.Customer;

public class CustomerDAOTest{
	private static CustomerDAO customerDAO;
	
	@BeforeClass
	public static void setUpClass() {
		customerDAO = new CustomerDAO();
	}
	
	@AfterClass
	public static void tearDownClass() {
		customerDAO.close();
	}
	
	@Test
	public void testGet() {
		Integer id  = 5;
		Customer search  = customerDAO.get(id);
		System.out.println(search.getEmail());;
		assertNotNull(search);
	}
	
	@Test
	public void testCreate() {
		Customer c = new Customer();
		String dummyString = "ZZZZZZZZZ";
		c.setEmail(dummyString);
		c.setFullName(dummyString);
		c.setAddress(dummyString);
		c.setCity(dummyString);
		c.setCountry(dummyString);
		c.setPhoneNumber(dummyString);
		c.setZipCode(dummyString);
		c.setPassword(dummyString);
		//c.setRegisterDay(new Date());	
		
		Customer created = customerDAO.create(c);
		
		assertNotNull(created);
		
	}
	
	@Test
	public void testUpdate() {
		Integer idToUpdate = 1;
		Customer search = customerDAO.get(idToUpdate);
		search.setEmail("1111_updated");
		
		Customer updated = customerDAO.update(search);
		
		String actual = updated.getEmail();
		String expected = "1111_updated";
		assertEquals(expected, actual);
	}
	
	@Test
	public void testDelete() {
		Integer id = 2;
		customerDAO.delete(id);
		Customer search = customerDAO.get(id);
		
		assertNull(search);
	}
	
	@Test 
	public void testListAll() {
		List<Customer> list = customerDAO.listAll();
		
		assertTrue(list.size()>0);
	}
	
	@Test
	public void testCountAll() {
		long actual = customerDAO.count();
		long expected = 5;
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testFindByEmail() {
		String email = "1111_updated";
		Customer search  = customerDAO.findByEmail(email);
		
		String actual = search.getFullName();
		
		System.out.println("actual:"+actual);
		String expected = "111111";
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testCheckLogin() {
		String email = "9999";
		String password = "999";
		
		Customer search = customerDAO.checkLogin(email, password);
		
		assertNotNull(search);
	}
	
}
