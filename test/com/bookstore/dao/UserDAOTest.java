package com.bookstore.dao;

import static org.junit.Assert.*;

import java.util.List;
import javax.persistence.EntityNotFoundException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.entity.Users;


public class UserDAOTest{
	
	private static UserDAO userDAO;
	
	@BeforeClass
	public static void setUpClass() {
		System.out.println("@BeforeClass");
		
		userDAO = new UserDAO();
	}
	
	@AfterClass
	public static void tearDownClass() {
		userDAO.close();
	}
	
	@Test
	public void testCreateUser() {
		//fail("Not yet implemented");
		Users user1 = new Users();
		user1.setEmail("zzzzzz");
		user1.setFullName("zzzzzzz");
		user1.setPassword("zzzzzzzz");

		user1 = userDAO.create(user1);
		
		//System.out.println("A Users object was persisted");
		
		assertTrue(user1.getUserId()>0);
	}
	@Test
	public void testUpdateUser() {
		Users user = new Users();
		user.setUserId(1);
		user.setEmail("chip_updated@gmai.com");
		user.setFullName("Nguyen Duy Khai");
		user.setPassword("password_updated");
		
		user = userDAO.update(user);
		
		String expected = user.getPassword();
		String actual = "password_updated";
		assertEquals(expected, actual);
	}
	
	@Test(expected = javax.persistence.PersistenceException.class )
	public void testCreateFieldsNotSet() {
		Users user1 = new Users();

		user1 = userDAO.create(user1);

		
		//System.out.println("A Users object was persisted");
		
		//assertTrue(user1.getUserId()>0);
	}
		
	
	@Test
	public void testGetUsersFound() {
		//Users user = null;
		Integer id_to_find = 10;
		System.out.println("hellooooooo");

		Users user  = userDAO.get(id_to_find);
		System.out.println("hellooooooo");
		
		assertNotNull(user);
	}
	
	@Test
	public void testGetUsersNotFound() {
		Integer id_to_find  = 999;
		
		Users user = userDAO.get(id_to_find);
		
		assertNull(user);
	}
	
	@Test
	public void testDeleteUsers() {
		Integer id_to_delete = 9;
		userDAO.delete(id_to_delete);
		
		Users user = userDAO.get(id_to_delete);
		
		assertNull(user);
	}
	
	@Test(expected  = EntityNotFoundException.class)
	public void testDeleteNonExistUsers() {
		Integer id_to_delete = 999;
		userDAO.delete(id_to_delete);
	}
	@Test
	public void testListAll() {
		List<Users> list = userDAO.listAll();
		
//		Integer expected = 8;
//		Integer actual = list.size();
//		assertEquals(expected, actual);
		
		assertTrue(list.size()>0);
	}
	
	@Test
	public void testCountAll() {
		long actual  = userDAO.count();
		long expected = 10;
		assertEquals(expected, actual); 
	}
	
	@Test
	public void testFindByEmail() {
		String email = "superman@gmail.com";
		Users user = userDAO.findByEmail(email);
		
		assertNotNull(user);
	}
	
	@Test
	public void checkLogin() {
		String email = "superman@gmail.com";
		String password = "superman";
		
		boolean actual =  userDAO.checkLogin(email, password);
		boolean expected  = true;
		
		assertEquals(expected, actual);
	}
	
	


}
