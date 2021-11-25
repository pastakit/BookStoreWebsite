package com.bookstore.controller.admin.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.service.UserServices;

/**
 * Servlet implementation class UpdateUserServlet
 */
@WebServlet("/admin/update_user")
public class UpdateUserServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		UserServices userServices = new UserServices(request, response);
		userServices.updateUser();
	}

}
