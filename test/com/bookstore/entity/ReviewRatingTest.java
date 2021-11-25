package com.bookstore.entity;

import static org.junit.Assert.*;

import org.junit.Test;

public class ReviewRatingTest {

	@Test
	public void testGetStars() {
		Review review = new Review();
		review.setRating(5);
		
		String actual = review.getStars();
		String expected = "on,on,on,on,on";
		System.out.println(actual);
		assertEquals(expected, actual);
	}

}
