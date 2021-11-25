package com.bookstore.dao;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.entity.Book;
import com.bookstore.entity.BookOrder;
import com.bookstore.entity.Customer;
import com.bookstore.entity.OrderDetail;
import com.bookstore.entity.OrderDetailId;

public class BookOrderDAOTest {
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
	public void testCreateOrder() {
//		BookOrder order = new BookOrder();
//		
//		Customer customer = new Customer();
//		customer.setCustomerId(5);
//		Date orderDate = new Date();
//		String address = "address";
//		String recipientName = "Chip";
//		String phone = "113";
//		String paymentMethod = "COD";
//		float orderTotal = 5.0f;
//		String orderStatus = "valid";
//		
//		order.setCustomer(customer);
//		order.setOrderDate(orderDate);
//		order.setShippingAddress(address);
//		order.setRecipientName(recipientName);
//		order.setRecipientPhone(phone);
//		order.setPaymentMethod(paymentMethod);
//		order.setOrderTotal(orderTotal);
//		order.setOrderStatus(orderStatus);
//		
//		BookOrder created = orderDAO.create(order);
//		assertTrue(created.getOrderId()>0);
		
		
		BookOrder order  = new BookOrder();
		
		Customer customer = new Customer();
		customer.setCustomerId(8);
		
		order.setCustomer(customer);
		order.setRecipientName("Customer A");
		order.setRecipientPhone("0123456789");
		order.setShippingAddress("District 7, HCM city");
		
		Set<OrderDetail> orderDetails = new HashSet<>();
		OrderDetail orderDetail = new OrderDetail();
		
		Book book = new Book(31);
		orderDetail.setBook(book);
		
		OrderDetailId orderDetailId = new OrderDetailId();
		orderDetailId.setQuantity(2);
		orderDetailId.setSubTotal(77.74f);
		
		orderDetail.setId(orderDetailId);

		
		orderDetails.add(orderDetail);
		
		order.setOrderDetails(orderDetails);
		
		BookOrder created = orderDAO.create(order);		
		
		assertTrue(created.getOrderId()>0);
	}
	
	
}
