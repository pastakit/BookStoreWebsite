package com.bookstore.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bookstore.entity.BookOrder;
import com.bookstore.entity.Customer;

public class OrderDAO extends JpaDAO<BookOrder>  implements GenericDAO<BookOrder>{
	
	@Override
	public BookOrder create(BookOrder bo) {
		bo.setOrderDate(new Date());
		bo.setPaymentMethod("Cash on Delivery");
		bo.setOrderStatus("Processing");
		return super.create(bo);
	}
	
	@Override
	public BookOrder update(BookOrder bo) {
		return super.update(bo);
	}
	
	@Override
	public BookOrder get(Object id) {
		return super.find(BookOrder.class, id);
	}
	
	public BookOrder get(Integer orderId , Integer customerId) {
		Map<String, Object> params = new HashMap<>();
		params.put("orderId", orderId);
		params.put("customerId", customerId);
		List<BookOrder> orders =  super.findWithNamedQuery("BookOrder.findByIdAndCustomer", params);
		if (!orders.isEmpty()) {
			return orders.get(0);
		}
		return null;
	}
	
	@Override
	public void delete(Object id) {
		super.delete(BookOrder.class, id);
	}
	
	@Override
	public List<BookOrder> listAll(){
		return super.findWithNamedQuery("BookOrder.findAll");
	}
	
	@Override
	public long count() {
		return super.countWithNamedQuery("BookOrder.countAll");
	}
	
	public List<BookOrder> listByCustomer(Integer customerId) {
		return super.findWithNamedQuery("BookOrder.listByCustomer", "customerId", customerId);
	}
	
}
