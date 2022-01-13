package com.bookstore.dao;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.entity.Book;
import com.bookstore.entity.BookOrder;
import com.bookstore.entity.Customer;
import com.bookstore.entity.OrderDetail;
import com.bookstore.entity.OrderDetailId;


public class OrderDAOTest {

	private static OrderDAO orderDAO;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		orderDAO = new OrderDAO();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		orderDAO.close(); 
	}
	
	@Test
	public void testCreateBookOrder1() {
		BookOrder order = new BookOrder();
		Customer customer = new Customer();
		customer.setCustomerId(5);
		
		order.setCustomer(customer);
		order.setRecipientName("Nam ha minh");
		order.setRecipientPhone("123456789");
		order.setShippingAddress("HCM q8");
		
		
		Set<OrderDetail> orderDetails = new HashSet<>();

		
		Book book1 = new Book(31);
		OrderDetail orderDetail1 = new OrderDetail();
		orderDetail1.setBook(book1);
		orderDetail1.setBookOrder(order);
		orderDetail1.setQuantity(2);
		orderDetail1.setSubTotal(77.74f);
		
		orderDetails.add(orderDetail1);
		
		order.setOrderDetails(orderDetails);
		
		BookOrder created = orderDAO.create(order);	
		
		assertNotNull(created.getOrderId()>0 && created.getOrderDetails().size()==2);
		
	}

	@Test
	public void testCreateBookOrder2() {
		BookOrder order = new BookOrder();
		Customer customer = new Customer();
		customer.setCustomerId(5);
		
		order.setCustomer(customer);
		order.setRecipientName("Nam ha minh");
		order.setRecipientPhone("123456789");
		order.setShippingAddress("HCM q8");
		
		
		Set<OrderDetail> orderDetails = new HashSet<>();

		
		Book book1 = new Book(31);
		OrderDetail orderDetail1 = new OrderDetail();
		orderDetail1.setBook(book1);
		orderDetail1.setBookOrder(order);
		orderDetail1.setQuantity(2);
		orderDetail1.setSubTotal(77.74f);
		
		orderDetails.add(orderDetail1);
		
		Book book2 = new Book(35);
		OrderDetail orderDetail2 = new OrderDetail();
		orderDetail2.setBook(book2);
		orderDetail2.setBookOrder(order);
		orderDetail2.setQuantity(1);
		orderDetail2.setSubTotal(27.33f);
		
		orderDetails.add(orderDetail2);

		order.setOrderDetails(orderDetails);
		
		BookOrder created = orderDAO.create(order);	
		
		assertNotNull(created.getOrderId()>0 && created.getOrderDetails().size()==2);
		
	}
	
	@Test
	public void testGet() {
		Integer id = 10;
		BookOrder order = orderDAO.get(id);
		
		System.out.println(order.getRecipientName());
		System.out.println(order.getRecipientPhone());
		System.out.println(order.getShippingAddress());
		System.out.println(order.getOrderStatus());
		System.out.println(order.getOrderTotal());
		System.out.println(order.getPaymentMethod());


		
		assertEquals(1, order.getOrderDetails().size());
	}
	
	@Test
	public void testListAll() {
		List<BookOrder> orders = orderDAO.findWithNamedQuery("BookOrder.findAll");
		for(BookOrder order: orders) {
			System.out.println(order.getOrderId()+"-"
					+order.getCustomer().getFullName()+"-"
					+order.getOrderTotal()+"-"
					+order.getOrderStatus());
			
			for(OrderDetail od: order.getOrderDetails()) {
				System.out.println(od.getQuantity()+"-"
						+od.getSubTotal()+"-"
						+od.getBook().getTitle());
			}
		}
		
		assertTrue(orders.size()>0);
	}
	
	@Test
	public void testUpdateOrderDetailShippingAddress() {
		Integer id = 8;
		
		BookOrder searchedOrder = orderDAO.get(id);
		
		searchedOrder.setShippingAddress("New shipping address");
		
		orderDAO.update(searchedOrder);
		
		BookOrder updatedOrder = orderDAO.get(id);
		
		assertEquals(searchedOrder.getShippingAddress(),updatedOrder.getShippingAddress());
	}
	
	// where pick order with id =11, change orderdetail of book31: quantity to 3, subtotal to 120 
	@Test
	public void testUpdateOrderDetailBook() {
		Integer id = 11;
		BookOrder searchedOrder = orderDAO.get(id);
		
		Iterator<OrderDetail> iter = searchedOrder.getOrderDetails().iterator();
		
		while(iter.hasNext()) {
			OrderDetail od = iter.next();
			if (od.getBook().getBookId()==31) {
				od.setQuantity(3);
				od.setSubTotal(120);
			}
		}
		
		orderDAO.update(searchedOrder);
		
		BookOrder updatedOrder = orderDAO.get(id);
		
		iter = updatedOrder.getOrderDetails().iterator();
		
		int expectedQuantity = 3;
		float expectedSubtotal = 120;
		int actualQuantity = 0;
		float actualSubtotal = 0;
		
		while(iter.hasNext()) {
			OrderDetail od = iter.next();
			if (od.getBook().getBookId()==31) {
				actualQuantity = od.getQuantity();
				actualSubtotal = od.getSubTotal();
			}
		}
		
		assertEquals(expectedQuantity, actualQuantity);
		assertEquals(expectedSubtotal, actualSubtotal, 0.0f);
		
	}
	
	// where pick order with id =13, add a oderDetail (book 38, quantity 10)
	@Test
	public void testUpdateOrderDetailBook2() {
		Integer orderId = 13;
		BookOrder order = orderDAO.get(orderId);

		
		OrderDetail od = new OrderDetail();
		od.setBookOrder(order);
		od.setBook(new Book(38));
		od.setQuantity(10);
		od.setSubTotal(99);
		
		order.getOrderDetails().add(od);
		
		orderDAO.update(order);
		
		BookOrder searchedOrder = orderDAO.get(orderId);
		assertTrue(searchedOrder.getOrderDetails().size()==2);
		
	}
	
	
	@Test
	public void testCountAll() {
		assertEquals(6, orderDAO.count());
	}
	
	@Test
	public void testDelete() {
		Integer id = 17;
		orderDAO.delete(id);
		
		BookOrder searchedOrder = orderDAO.get(id);
		
		assertNull(searchedOrder);
	}
	
	@Test
	public void testListByCustomer() {
		Integer customerId = 5;
		List<BookOrder> list = orderDAO.listByCustomer(customerId);
		assertEquals(3,	 list.size());
	}
	
	@Test
	public void testFindOrderByIdAndCustomerNull() {
		Integer orderId = 8;
		Integer customerId = 99;
		
		BookOrder order = orderDAO.get(orderId, customerId);
		
		assertNull(order);
	}
	
	@Test
	public void testFindOrderByIdAndCustomerNotNull() {
		Integer orderId = 8;
		Integer customerId = 5;
		
		BookOrder order = orderDAO.get(orderId, customerId);
		
		assertNotNull(order);

	}
	
	@Test
	public void myTest1() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("BookStoreWebsite");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		
		BookOrder s = em.find(BookOrder.class, 99);
		
		assertNull(s);
//		
//		Integer id = s.getOrderId();
//		assertEquals(id, Integer.valueOf(4));
		
		em.getTransaction().commit();
		em.close();
		emf.close();

	}

}
