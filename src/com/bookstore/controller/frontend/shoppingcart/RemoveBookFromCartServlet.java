package com.bookstore.controller.frontend.shoppingcart;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bookstore.entity.Book;


@WebServlet("/remove_from_cart")
public class RemoveBookFromCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Integer bookId = Integer.parseInt(request.getParameter("book_id"));
		HttpSession session = request.getSession();
		Object cartObj = session.getAttribute("cart");
		ShoppingCart cart = (ShoppingCart) cartObj;
		
		cart.removeItem(new Book(bookId));
		
		String page = request.getContextPath().concat("/view_cart");
		response.sendRedirect(page);
	}
}
