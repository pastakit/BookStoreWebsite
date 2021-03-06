package com.bookstore.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.bookstore.entity.Book;

public class BookDAO extends JpaDAO<Book> implements GenericDAO<Book>{
	
	public BookDAO(){
		//super();
	}
	
	public Book create(Book book) {
		book.setLastUpdateTime(new Date());
		return super.create(book);
	}
	
	public Book update(Book book) {
		book.setLastUpdateTime(new Date());
		return super.update(book);
	}
	
	public Book get(Object id) {
		return super.find(Book.class, id);
	}
	
	public void delete(Object id) {
		super.delete(Book.class, id);
	}
	
	public List<Book> listAll(){
		return super.findWithNamedQuery("Book.findAll");
	}
	
	public Book findByTitle(String title) {
		List<Book> list = super.findWithNamedQuery("Book.findByTitle", "title", title);
		if (list!=null && list.size()>0) {
			return list.get(0);
		}
		
		return null;
	}
	
	public List<Book> listByCategory(int categoryId){
		return super.findWithNamedQuery("Book.findByCategory", "catId", categoryId);
	}
	
	public List<Book> listNewBooks() {
		return super.findWithNamedQuery("Book.listNew", 0, 4);
	}
	
	public List<Book> search(String keyword){
		return super.findWithNamedQuery("Book.search", "keyword", keyword);
	}
	
	public long count() {
		return super.countWithNamedQuery("Book.countAll");
	}
	
	public List<Book> listBestSelling(){
		return super.findWithNamedQuery("OrderDetail.bestSelling", 0, 4);
	}
	
	public List<Book> mostFavoredBook() {
		return super.findWithNamedQuery("Review.mostFavoredBook", 0, 4);
	}
}
