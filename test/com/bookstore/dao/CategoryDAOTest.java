package com.bookstore.dao;

import static org.junit.Assert.*;

import java.util.List;

//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.Persistence;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.entity.Category;

public class CategoryDAOTest extends BaseDAOTest{


	private static CategoryDAO categoryDAO;
	
	@BeforeClass
	public static void setUpClass() {
		BaseDAOTest.setUpClass();
		categoryDAO = new CategoryDAO(em);
		
	}
	
	
	@Test
	public void testCreateCategory() {
		//fail("Not yet implemented");
		Category cate1 = new Category("Advanced Java");
		
		cate1 = categoryDAO.create(cate1);
		assertTrue(cate1.getCategoryId()>0);
	}
	@Test
	public void testUpdate() {
		Category cate = new Category();
		cate.setCategoryId(8);
		cate.setName("CSS");
		
		Category cateReturn = categoryDAO.update(cate);
		
		String actual = cateReturn.getName();
		String expected = "CSS";
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testGetCategoryFound() {
		Integer catId = 3;
		Category cat= categoryDAO.get(catId);
		assertNotNull(cat);
	}
	
	@Test
	public void testGetCategoryNotFound() {
		Integer catId = 999;
		Category cat=  categoryDAO.get(catId);
		assertNull(cat);
	}
	
	@Test
	public void testDeleteCategory() {
		Integer catId = 2;
		categoryDAO.delete(catId);
		
		Category cat = categoryDAO.get(catId);
		assertNull(cat);
	}
	
	@Test
	public void testListAll() {
		List<Category> list = categoryDAO.listAll();
		
		int actual = list.size();
		int expected = 11;
		assertEquals(expected, actual);
	}
	
	@Test
	public void testCount() {
		long actual = categoryDAO.count();
		long expected = 11;
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testFindByName() {
		Category cat = categoryDAO.findByName("Python");
		
//		String actual = cat.getName();
//		String expected = "Python";
		int actual = cat.getCategoryId();
		int expected = 3;
		assertEquals(expected, actual);
	}
	
	@AfterClass
	public static void tearDownClass() {
		BaseDAOTest.tearDownClass();
	}

}
