package com.bookstore.controller.frontend.shoppingcart;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.fabric.xmlrpc.base.Array;

/**
 * Servlet implementation class UpdateCartServlet
 */
@WebServlet("/update_cart")
public class UpdateCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub	
		String[] strBookIds = request.getParameterValues("bookId");
		String[] strQuantities = new String[strBookIds.length];
		
		for(int i=0; i< strBookIds.length; i++) {
			strQuantities[i] = request.getParameter("quantity"+i);
		}
		
		int[] bookIds = Arrays.stream(strBookIds).mapToInt(Integer::parseInt).toArray();
		int[] quantities  = Arrays.stream(strQuantities).mapToInt(Integer::parseInt).toArray();
		
		HttpSession session = request.getSession();
		ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
		
		cart.updateCart(bookIds, quantities);
		
		String page = request.getContextPath().concat("/view_cart");
		response.sendRedirect(page);
	}

}
