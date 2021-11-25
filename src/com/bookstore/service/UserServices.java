package com.bookstore.service;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.bookstore.dao.UserDAO;
import com.bookstore.entity.Users;


public class UserServices {
	private UserDAO userDAO;
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	public UserServices( HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		
		userDAO = new UserDAO();
	}
	
	
	public void listUser() 
			throws ServletException, IOException{
		this.listUser( null);
		
	}
	
	public void listUser( String message) 
			throws ServletException, IOException{
		List<Users> listUsers = userDAO.listAll();
		
		request.setAttribute("listUsers", listUsers);
		if (message !=null) {
			request.setAttribute("message", message);	
		}
		
		
		String listPage = "user_list.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(listPage);
		dispatcher.forward(request, response);
		
	}
	
	public void createUser() throws ServletException, IOException{

		
		String email = request.getParameter("email");
		String fullName = request.getParameter("fullname");
		String password = request.getParameter("password");
		
		if (userDAO.findByEmail(email)!=null) {
			String message =  "User with email "+email+ " already exists";
			request.setAttribute("message", message);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("message.jsp");
			dispatcher.forward(request, response);
		}
		else{
			Users newUser = new Users(email, fullName, password);
			userDAO.create(newUser);
			listUser("New User created successfully");
		}	
	}
	
	public void editUser() throws ServletException, IOException {
		Integer userId = Integer.parseInt(request.getParameter("id"));
		
		Users user = userDAO.get(userId);
		String editPage = "user_form.jsp";
		request.setAttribute("user", user);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(editPage);
		dispatcher.forward(request, response);
		
	}
	
	public void deleteUser() throws ServletException, IOException{
		int userId = Integer.parseInt(request.getParameter("id")) ;
		userDAO.delete(userId);
		listUser("Deleted user with id "+ userId);
		
	}
	
	public void updateUser() throws ServletException, IOException{
		int userId = Integer.parseInt(request.getParameter("userId"));
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String fullName = request.getParameter("fullname");

		System.out.println("\nemail:"+email+"\nfullName:"+fullName+"\nuserId:"+userId);
		
		Users userGivenId = userDAO.get(userId);
		Users userGivenEmail = userDAO.findByEmail(email);
		
		if (userGivenEmail != null && userGivenId.getUserId()!=userGivenEmail.getUserId()) {
			String message = "User with email:\""+email+"\" already exists, try another email";
			request.setAttribute("message", message);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("message.jsp");
			dispatcher.forward(request, response);
		}
		else {
			
			Users user = new Users(userId, email, fullName , password);
			userDAO.update(user);
			listUser("Updated successfully");
		}
		
	}
	
	
	public void login() throws ServletException, IOException{
		String email  = request.getParameter("email");
		String password = request.getParameter("password");
		
		
		if (userDAO.checkLogin(email, password)==true) {
			//System.out.println("login successfully");
			request.getSession().setAttribute("useremail", email);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/");
			dispatcher.forward(request, response);
		}
		else {
			//System.out.println("login fail");
			String loginFailMessage = "Incorrect email or password, Try again";
			request.setAttribute("message", loginFailMessage);
			
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
			dispatcher.forward(request, response);
		}
	}
	
}
