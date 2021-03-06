package com.bookstore.controller.frontend.customer;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.dao.CustomerDAO;
import com.bookstore.service.CustomerServices;


@WebServlet("/register")
public class ShowCustomerRegisterForm extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("ShowCustomerRegisterForm");
		CustomerServices customerServices = new CustomerServices(request, response);
		customerServices.showCustomerRegisterForm();
	}
}
