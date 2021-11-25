package com.bookstore.dao;

import java.util.List;

import javax.persistence.EntityManager;

import com.bookstore.entity.Category;

public class CategoryDAO extends JpaDAO<Category> implements GenericDAO<Category>{
	public CategoryDAO() {
		//super();
	}
	
	@Override
	public Category create(Category cate) {
		return super.create(cate);
	}
	
	@Override
	public Category update(Category cate) {
		return super.update(cate);
	}
	@Override
	public Category get(Object id) {
		return super.find(Category.class, id); 
	}
	@Override
	public void delete(Object id) {
		//
		super.delete(Category.class, id);
		
	}
	@Override
	public List<Category> listAll(){
		return super.findWithNamedQuery("Category.findAll");
		
	}
	
	public Category findByName(String name) {
		List<Category> list = super.findWithNamedQuery("Category.findByName", "name", name);
		
		if (list!=null && list.size()>0) {
			return list.get(0);
		}
		return null;
	}
	
	
	
	@Override 
	public long count() {
		return super.countWithNamedQuery("Category.countAll");

	}
	
}
