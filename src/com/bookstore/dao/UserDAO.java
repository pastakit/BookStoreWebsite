package com.bookstore.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.bookstore.entity.Users;

public class UserDAO extends JpaDAO<Users> implements GenericDAO<Users> {

	public UserDAO() {
		//super();
		// TODO Auto-generated constructor stub
	}
	
	
	public Users create(Users user) {
		return super.create(user);
	}
	
	@Override
	public Users update(Users t) {
		// TODO Auto-generated method stub
		return super.update(t);
	}

	@Override
	public Users get(Object userId) {
		// TODO Auto-generated method stub
		return super.find(Users.class, userId);
	}
	
	
	public Users findByEmail(String email) {
		List<Users> listUsers = super.findWithNamedQuery("Users.findByEmail", "email", email);
		if (listUsers != null && listUsers.size()>0 ) {
			return listUsers.get(0);
		}
		return null;
	}

	@Override
	public void delete(Object userId) {
		// TODO Auto-generated method stub
		super.delete(Users.class, userId);
		
	}
	

	@Override
	public List<Users> listAll() {
		// TODO Auto-generated method stub
		return super.findWithNamedQuery("Users.findAll");
	}
	

	@Override
	public long count() {
		// TODO Auto-generated method stub
		//return 0;
		return super.countWithNamedQuery("Users.countAll");
	}
	
	
	public boolean checkLogin(String email, String password) {
		Map<String, Object> params = new HashMap<>();
		params.put("email",email);
		params.put("password", password);
		
		List<Users> listUsers =  super.findWithNamedQuery("Users.checkLogin", params);
		if (listUsers.size()>0) {
			return true;
		}
		return false;
		
	}
	
}
