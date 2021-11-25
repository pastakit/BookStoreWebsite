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


@WebServlet("/view_cart")
public class ViewCartServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
//		HttpSession session = request.getSession();
//		Object cartObject = session.getAttribute("cart");
//		if (cartObject==null) {
//			ShoppingCart cart = new ShoppingCart();
//			//cart.ge
//			
//			session.setAttribute("cart", cart);
//		}
		
//		BookDAO bookDAO = new BookDAO();
//		Book book1 = bookDAO.get(31);
//		Book book2 = bookDAO.get(32);

		
//		ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute("cart");
//		shoppingCart.addItem(book1);
//		shoppingCart.addItem(book2);

		String page = "frontend/shopping_cart.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}
}
