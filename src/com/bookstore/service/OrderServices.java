package com.bookstore.service;

import java.io.IOException;
import java.util.List;
import java.util.Map.Entry;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.controller.frontend.shoppingcart.ShoppingCart;
import com.bookstore.dao.OrderDAO;
import com.bookstore.entity.Book;
import com.bookstore.entity.BookOrder;
import com.bookstore.entity.Customer;
import com.bookstore.entity.OrderDetail;

public class OrderServices {
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	private  OrderDAO orderDAO;
	
	public OrderServices(HttpServletRequest request, HttpServletResponse response) {
		this.request=request;
		this.response=response;
		
		orderDAO = new OrderDAO();
	}
	
	public void listOrder(String message) throws IOException, ServletException{
		List<BookOrder> listOrders = orderDAO.listAll();
		request.setAttribute("listOrders", listOrders);
		
		if (message!=null) {
			request.setAttribute("message",message);
		}

		String listPage = "order_list.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(listPage);
		dispatcher.forward(request, response);
	}
	
	public void listOrder() throws IOException, ServletException{
		this.listOrder(null);
	}
	
	public void viewOrderDetailForAdmin() throws IOException, ServletException{
		int orderId = Integer.parseInt(request.getParameter("id"));
		
		BookOrder order = orderDAO.get(orderId);
		request.setAttribute("order", order);
		
		String page = "order_detail.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}
	
	public void showCheckoutForm() throws IOException, ServletException{
		String page = "frontend/checkout.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}
	
	public void placeOrder() throws IOException, ServletException{
		// get shipping form 
		String recipientName = request.getParameter("recipientname");
		String recipientPhone = request.getParameter("recipientphone");
		String streetAddress = request.getParameter("streetaddress");
		String city = request.getParameter("city");
		String zipcode = request.getParameter("zipcode");
		String country = request.getParameter("country");
		String payment = request.getParameter("payment");
		String shippingAddress = streetAddress+", "+city+", "+ zipcode;
		
		// init new Order object
		BookOrder order  = new BookOrder();
		order.setRecipientName(recipientName);
		order.setRecipientPhone(recipientPhone);
		order.setShippingAddress(shippingAddress);
		order.setPaymentMethod(payment);
		
		Customer customer = (Customer)request.getSession().getAttribute("loggedCustomer");
		System.out.println(customer.getFullName());
		order.setCustomer(customer);
//		System.out.println(recipientName+"-"
//				+recipientPhone+"-"
//				+streetAddress+"-"
//				+city+"-"
//				+country+"-"
//				+payment+"");
		
		
		float total = 0;
		// get items from cart in session
		ShoppingCart cart = (ShoppingCart) request.getSession().getAttribute("cart");
		for(Entry<Book, Integer> entry: cart.getItems().entrySet()) {
			Book book = entry.getKey();
			int quantity = entry.getValue();
			float subTotal = book.getPrice()*quantity;
			
			OrderDetail od = new OrderDetail();
			od.setBook(book);
			od.setBookOrder(order);
			od.setQuantity(quantity);
			od.setSubTotal(subTotal);
			
			total+=subTotal;
			
			order.getOrderDetails().add(od);
		}
		
		order.setOrderTotal(total);
		orderDAO.create(order);
		
		String message = "Thank for your order, this will be deliveried soon";
		cart.clear();
		request.setAttribute("message", message);
		request.setAttribute("typeMessage","success");
		
		String page = "frontend/message.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}
	
	public void listOrdersByCustomer() throws IOException, ServletException{
		Customer loggedCustomer  = (Customer)request.getSession().getAttribute("loggedCustomer");
		Integer customerId = loggedCustomer.getCustomerId();
		
		List<BookOrder> listOrders = orderDAO.listByCustomer(customerId);
		request.getSession().setAttribute("listOrders", listOrders);
		
		String page = "frontend/order_list.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}
	
	public void showOrderDetailForCustomer() throws IOException, ServletException{
		//get both orderId and customerId
		Integer orderId = Integer.parseInt(request.getParameter("id"));
		Customer customerId =  (Customer) request.getSession().getAttribute("loggedCustomer");
//		System.out.println("test");
//		System.out.println(orderId);
//		System.out.println(customerId);

		
		BookOrder order = orderDAO.get(orderId, customerId.getCustomerId());
		
		request.getSession().setAttribute("order",order);
		
		String page = "frontend/order_detail.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}
	
	public void showEditOrderForm() throws IOException, ServletException {
		Integer orderId = Integer.parseInt(request.getParameter("id"));
		
		
		Object penddingBookFlag = request.getSession().getAttribute("NewBookPendingToAddToOrder");
		if (penddingBookFlag==null) {
			BookOrder order = orderDAO.get(orderId);
			request.getSession().setAttribute("order", order);
		}
		else {
			request.getSession().removeAttribute("NewBookPendingToAddToOrder");
		}
		
		
		String page = "order_form.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);

	}	
	
	public void updateOrder() throws IOException, ServletException {
		String recipientname = request.getParameter("recipientname");
		String recipientphone = request.getParameter("recipientphone");
		String shippingAddress = request.getParameter("shippingAddress");
		String payment = request.getParameter("payment");
		
		BookOrder order = (BookOrder) request.getSession().getAttribute("order");
		
		order.setRecipientName(recipientname);
		order.setRecipientPhone(recipientphone);
		order.setShippingAddress(shippingAddress);
		order.setPaymentMethod(payment);
		

		String[] bookIdArr = request.getParameterValues("bookId");
		String[] priceArr = request.getParameterValues("price");
		String[] quantityArr = new String[bookIdArr.length]; 
		
		for(int i =0;i<bookIdArr.length;i++) {
			OrderDetail od = new OrderDetail();
			
			Integer bookId = Integer.parseInt(bookIdArr[i]);
			Float price = Float.parseFloat(priceArr[i]);
					
			Integer quantity = Integer.parseInt(request.getParameter("quantity"+i));
			
			System.out.println(price+"$ - "+quantity);

		}
		
//		// if dont remove from child ==> orderdetail auto recover
//		// or you can use orphanremoval 
		order = orderDAO.update(order);
		
//		// clear order
//		order.getOrderDetails().clear();
//		
//		// get arrays : bookId, price, quantity[i]
//		
//		String[] bookIdArr = request.getParameterValues("bookId");
//		String[] priceArr = request.getParameterValues("price");
//		String[] quantityArr = new String[bookIdArr.length]; 
//		
//		for(int i =0;i<bookIdArr.length;i++) {
//			OrderDetail od = new OrderDetail();
//			
//			Integer bookId = Integer.parseInt(bookIdArr[i]);
//			Integer price = Integer.parseInt(priceArr[i]);
//			Integer quantity = Integer.parseInt(quantityArr[i]);
//			
//			System.out.println(price+"$ - "+quantity);
//
//		}
		
		this.listOrder("Updated Order successfully");
	}
}
