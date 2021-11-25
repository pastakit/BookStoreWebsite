package com.bookstore.controller.frontend.shoppingcart;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bookstore.dao.BookDAO;
import com.bookstore.entity.Book;


@WebServlet("/add_to_cart")
public class AddBookToCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		Integer bookId = Integer.parseInt(request.getParameter("book_id"));
		
		HttpSession session = request.getSession();
		Object cartObj = session.getAttribute("cart");
		ShoppingCart cart = null;
		
		if (cartObj!=null && cartObj instanceof ShoppingCart) {
			cart = (ShoppingCart) cartObj;
			
		}else {
			cart = new ShoppingCart();
			session.setAttribute("cart", cart);
		}
		
		BookDAO bookDAO = new BookDAO();	
		Book book = bookDAO.get(bookId);
	
		cart.addItem(book);
		
		String page= request.getContextPath().concat("/view_cart");
		response.sendRedirect(page);
	}



}
