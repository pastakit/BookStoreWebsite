package com.bookstore.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.bookstore.entity.Book;
import com.bookstore.entity.Review;

public class ReviewDAO extends JpaDAO<Review> implements GenericDAO<Review>{

	@Override
	public Review create(Review r) {
		// TODO Auto-generated method stub
		r.setReviewTime(new Date());
		return super.create(r);
	}
	
	
	@Override
	public Review get(Object id) {
		// TODO Auto-generated method stub
		return super.find(Review.class, id);
	}

	@Override
	public void delete(Object id) {
		// TODO Auto-generated method stub
		super.delete(Review.class, id);
		
	}

	@Override
	public List<Review> listAll() {
		// TODO Auto-generated method stub
		return super.findWithNamedQuery("Review.findAll");
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return super.countWithNamedQuery("Review.countAll");
	}
	
	public Review findByCustomerAndBook(int customerId, int bookId) {
		Map<String, Object> params = new HashMap<>();
		params.put("customerId", customerId);
		params.put("bookId", bookId);
		List<Review> list = super.findWithNamedQuery("Review.findByCustomerAndBook", params);
		if (list!=null &&list.size()>0) {
			return list.get(0);
		}
		return null;
	}
	

	
}
