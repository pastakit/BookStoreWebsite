package com.bookstore.dao;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.entity.Book;
import com.bookstore.entity.BookOrder;
import com.bookstore.entity.Customer;
import com.bookstore.entity.OrderDetail;
import com.bookstore.entity.OrderDetailId;

public class draft1 {
	private static OrderDAO orderDAO;
	
	@BeforeClass
	public static void setUpClass() {
		orderDAO = new OrderDAO();
	}
	
	@AfterClass
	public static void tearDownClass() {
		orderDAO.close();
	}
	
	
	
	@Test
	public void testCreateOrder1() {
		
		
		BookOrder order  = new BookOrder();
		
		Customer customer = new Customer();
		customer.setCustomerId(8);
		
		order.setCustomer(customer);
		order.setRecipientName("Customer Z");
		order.setRecipientPhone("0123456789");
		order.setShippingAddress("District 8 Cao Lo, HCM city");
		
		Set<OrderDetail> orderDetails = new HashSet<>();
		OrderDetail orderDetail = new OrderDetail();
		
		Book book = new Book(31);
		orderDetail.setBook(book);
		orderDetail.setBookOrder(order); // required
		orderDetail.setQuantity(3);
		orderDetail.setSubTotal(116.61f);
			
		orderDetails.add(orderDetail);
		
		order.setOrderDetails(orderDetails);
		
		BookOrder created = orderDAO.create(order);		
		
		assertTrue(created.getOrderId()>0);
	}
	
	
	@Test
	public void testCreateOrder2() {		
		
		BookOrder order  = new BookOrder();
		
		Customer customer = new Customer();
		customer.setCustomerId(8);
		
		order.setCustomer(customer);
		order.setRecipientName("Customer id 8");
		order.setRecipientPhone("0123456789");
		order.setShippingAddress("District 8 Cao Lo, HCM city");
		
		
		
		Set<OrderDetail> orderDetails = new HashSet<>();
		
		OrderDetail orderDetail1 = new OrderDetail();
		Book book1 = new Book(31);
		orderDetail1.setBook(book1);
		orderDetail1.setBookOrder(order); // required
		orderDetail1.setQuantity(3);
		orderDetail1.setSubTotal(300);
			
		OrderDetail orderDetail2 = new OrderDetail();
		Book book2 = new Book(32);
		orderDetail2.setBook(book2);
		orderDetail2.setBookOrder(order); // required
		orderDetail2.setQuantity(2);
		orderDetail2.setSubTotal(200);
			
		orderDetails.add(orderDetail1);
		orderDetails.add(orderDetail2);

		
		order.setOrderDetails(orderDetails);
		
		BookOrder created = orderDAO.create(order);		
		
		assertTrue(created.getOrderId()>0);
	}
	
	@Test
	public void testGet() {
		Integer id =  12;
		BookOrder searchedOrder = orderDAO.find(BookOrder.class, id);
		
		assertTrue(searchedOrder.getOrderId()==12);
	}
	
	@Test
	public void testListAll() {
		
	}
	
}
