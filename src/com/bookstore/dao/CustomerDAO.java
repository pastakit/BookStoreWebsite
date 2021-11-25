package com.bookstore.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bookstore.entity.Book;
import com.bookstore.entity.Customer;

public class CustomerDAO extends JpaDAO<Customer> implements GenericDAO<Customer>{
	
	public Customer get(Object id) {
		return super.find(Customer.class, id);
	}
	
	public Customer create(Customer c) {
		c.setRegisterDay(new Date());
		return super.create(c);
	}
	
	public Customer update(Customer c) {
		return super.update(c);
	}
	
	public void delete(Object id) {
		super.delete(Customer.class, id);
	}
	
	public List<Customer> listAll(){
		return super.findWithNamedQuery("Customer.findAll");
	}

	public long count() {
		return super.countWithNamedQuery("Customer.countAll");
	}
	
	public Customer findByEmail(String email) {
		List<Customer> list = super.findWithNamedQuery("Customer.findByEmail", "email", email);
		if (list!=null && list.size()>0) {
			return list.get(0);
		}
		return null;
	}
	
	public Customer checkLogin(String email, String password) {
		Map<String, Object> params = new HashMap<>();
		
		params.put("email", email);
		params.put("password", password);
		
		List<Customer> list = super.findWithNamedQuery("Customer.checkLogin", params);
		if (list!=null && list.size()>0) {
			return list.get(0);
		}
		return null;
	}
	
}
