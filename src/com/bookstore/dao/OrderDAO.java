package com.bookstore.dao;

import java.util.Date;
import java.util.List;

import com.bookstore.entity.BookOrder;

public class OrderDAO extends JpaDAO<BookOrder>  implements GenericDAO<BookOrder>{
	public BookOrder create(BookOrder bo) {
		bo.setOrderDate(new Date());
		bo.setPaymentMethod("Cash on Delivery");
		bo.setOrderStatus("Processing");
		return super.create(bo);
	}
	
	public BookOrder update(BookOrder bo) {
		return null;
	}
	
	public BookOrder get(Object id) {
		return null;
	}
	
	public void delete(Object id) {
		return;
	}
	
	public List<BookOrder> listAll(){
		return null;
	}
	
	public long count() {
		return -1;
	}
	
}
