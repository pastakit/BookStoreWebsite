package com.bookstore.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.entity.Book;
import com.bookstore.entity.Customer;
import com.bookstore.entity.Review;


public class ReviewDAOTest {
	private static ReviewDAO reviewDAO;
	
	@BeforeClass
	public static void setUpClass() {
		reviewDAO = new ReviewDAO();
	}
	@AfterClass
	public static void tearDownClass() {
		reviewDAO.close();
	}
	
	@Test
	public void testCreateReview() {
		Review newReview = new Review();
		
		Book book = new Book();
		Integer bookId= 33;
		book.setBookId(bookId);
		newReview.setBook(book);
		
		Customer customer = new Customer();
		Integer customerId = 8;
		customer.setCustomerId(customerId);
		newReview.setCustomer(customer);
		
		
		newReview.setRating(5);
		newReview.setHeadline("this is headline for book id "+ bookId+" from customer id "+customerId);
		newReview.setComment("this is comment for book id "+ bookId+" from customer id "+customerId);
		
		Review created = reviewDAO.create(newReview);
		
		assertTrue(created.getReviewId()>0);
	}
	
	@Test
	public void testGetReview() {
		Integer id = 2;
		Review search  = reviewDAO.get(id);
		
		assertTrue(search.getReviewId()==id);
	}
	
	@Test
	public void testDeleteReview() {
		Integer id = 2;
		reviewDAO.delete(id);
		
		Review search = reviewDAO.get(id);
		
		assertNull(search);
	}
	
	@Test
	public void testListAll() {
		List<Review> list = reviewDAO.listAll();
		
		assertTrue(list.size()==1);
		
	}
	
	@Test
	public void testCount() {
		long size = reviewDAO.count();
		
		assertTrue(size==1);
	}
	
	
	@Test
	public void testFindByCustomerAndBook() {
		int bookId = 31;
		int customerId = 8;
		
		Review search = reviewDAO.findByCustomerAndBook(customerId, bookId);
		
		// compare id
		int expected = 4;
		int actual = search.getReviewId();
		
		assertEquals(expected, actual);
	}
	


}
