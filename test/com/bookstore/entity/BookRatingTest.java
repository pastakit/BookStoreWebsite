package com.bookstore.entity;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class BookRatingTest {
	
	@Test
	public void testAverageRating() {
		Book book = new Book();
		Set<Review> reviews  = new HashSet<>();
		Review r1 = new Review();
		r1.setRating(3);
		Review r2=  new Review();
		r2.setRating(5);
		
		reviews.add(r1);
		reviews.add(r2);
		book.setReviews(reviews);
		
		float result = book.getAverageRating();
		assertTrue(result==4.0f);
	}
	
	@Test
	public void testRatingString() {
		Book book = new Book();
		Set<Review> reviews  = new HashSet<>();
		Review r1 = new Review();
		r1.setRating(4);
		Review r2=  new Review();
		r2.setRating(5);
		
		
		reviews.add(r1);
		reviews.add(r2);
		book.setReviews(reviews);
		
		String actual  = book.getRatingString();
		String expected  = "on,on,on,on,half,";
		System.out.println(actual);
		assertEquals(expected, actual);
	}

}
