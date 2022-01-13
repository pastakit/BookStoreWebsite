package com.bookstore.controller.admin.order;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.entity.Book;
import com.bookstore.entity.BookOrder;
import com.bookstore.entity.OrderDetail;


@WebServlet("/admin/remove_book_from_order")
public class RemoveBookFromOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Integer bookId = Integer.parseInt(request.getParameter("id"));
		
		BookOrder order = (BookOrder)request.getSession().getAttribute("order");
		
		Set<OrderDetail> orderDetails = order.getOrderDetails();
		Iterator<OrderDetail> iter = orderDetails.iterator();
		System.out.println("order.getOrderDetails().size():"+order.getOrderDetails().size());
		while(iter.hasNext()) {
			System.out.println("1 time in while loop");
			OrderDetail od = iter.next();
			if (od.getBook().getBookId()==bookId) {
				// orderDetails.remove(od); dont do this
				float newTotal = order.getOrderTotal()-od.getSubTotal();
				order.setOrderTotal(newTotal);
				iter.remove();
				
			}
		}
		
		String page = "order_form.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}
}
