package com.bookstore.controller.admin.order;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.dao.BookDAO;
import com.bookstore.entity.Book;
import com.bookstore.entity.BookOrder;
import com.bookstore.entity.OrderDetail;


@WebServlet("/admin/add_book_to_order")
public class AddBookToOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Integer bookId = Integer.parseInt(request.getParameter("bookId"));
		Integer quantity = Integer.parseInt(request.getParameter("quantity"));
		
		BookDAO bookDAO = new BookDAO();
		Book book = bookDAO.get(bookId);
		
		BookOrder order =(BookOrder) request.getSession().getAttribute("order");
//		if (order==null) {
//			System.out.println("order is null");
//		}
//		else {
//			System.out.println("order is NOT null");
//
//		}
		
		float subTotal  = book.getPrice()*quantity;
		
		OrderDetail od = new OrderDetail();
		od.setBook(book);
		od.setBookOrder(order);
		od.setQuantity(quantity);
		od.setSubTotal(subTotal);
		
		float newTotal = order.getOrderTotal() + subTotal;

		order.setOrderTotal(newTotal);
		
		order.getOrderDetails().add(od);

		request.setAttribute("book", book);
		request.getSession().setAttribute("NewBookPendingToAddToOrder", true);
		
		String page = "add_book_result.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
		
	}
}
