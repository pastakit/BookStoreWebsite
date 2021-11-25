package com.bookstore.service;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bookstore.dao.CategoryDAO;
import com.bookstore.dao.CustomerDAO;
import com.bookstore.entity.Category;
import com.bookstore.entity.Customer;

public class CustomerServices {
	private HttpServletRequest request;
	private HttpServletResponse response;
	private CustomerDAO customerDAO;
	private CategoryDAO categoryDAO;

	
	public CustomerServices(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		
		customerDAO = new CustomerDAO();
		categoryDAO = new CategoryDAO();
	}
	
	public void listCustomer(String message) throws IOException, ServletException{
		List<Customer> listCustomers = customerDAO.listAll();
		request.setAttribute("listCustomers", listCustomers);
		
		if (message != null) {
			request.setAttribute("message", message);
		}
		
		String page = "customer_list.jsp";
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
		
	}
	
	public void listCustomer() throws IOException, ServletException{
		listCustomer(null);
	}
	
	public void showCustomerNewForm() throws IOException, ServletException{
		String page = "customer_form.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
		
	}
	
	public void createCustomer() throws IOException, ServletException{
		String email = request.getParameter("email");
		String fullName = request.getParameter("fullname");
		String password = request.getParameter("password");
		String address = request.getParameter("address");
		String city = request.getParameter("city");
		String country = request.getParameter("country");
		String phoneNumber = request.getParameter("phonenumber");
		String zipCode = request.getParameter("zipcode");
		
		Customer search = customerDAO.findByEmail(email);
		if (search!=null) {
			String message = "Customer with email: \""+ email+"\" already exists";
			String typeMessage = "alert";
			
			request.setAttribute("message", message);
			request.setAttribute("typeMessage", typeMessage);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("message.jsp");
			dispatcher.forward(request, response);
		}
		else {
			
			Customer newCustomer = new Customer();
			newCustomer.setEmail(email);
			newCustomer.setFullName(fullName);
			newCustomer.setPassword(password);
			newCustomer.setAddress(address);
			newCustomer.setCity(city);
			newCustomer.setCountry(country);
			newCustomer.setPhoneNumber(phoneNumber);
			newCustomer.setZipCode(zipCode);
			
			Customer created =  customerDAO.create(newCustomer);
			
			listCustomer("Created new Customer successfully");
		}
		
	}
	
	public void editCustomer() throws IOException, ServletException{
		Integer id = Integer.parseInt(request.getParameter("id"));
		Customer customer = customerDAO.get(id);
		
		request.setAttribute("customer", customer);
		
		String page = "customer_form.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}
	
	public void updateCustomer() throws IOException, ServletException {
		Integer id = Integer.parseInt(request.getParameter("id"));
		System.out.println("id customer:"+id);
		String email = request.getParameter("email");
		
		Customer customerByEmail = customerDAO.findByEmail(email);
		
		if ( customerByEmail !=null && customerByEmail.getCustomerId()!=id) {
			String message = "Customer with email: \""+ email+"\"already exists";
			String typeMessage = "alert";
			
			request.setAttribute("message", message);
			request.setAttribute("typeMessage", typeMessage);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("message.jsp");
			dispatcher.forward(request, response);
			
		}
		else {
			System.out.println("updating...");
			String fullName = request.getParameter("fullname");
			String password = request.getParameter("password");
			String address = request.getParameter("address");
			String city = request.getParameter("city");
			String country = request.getParameter("country");
			String phoneNumber = request.getParameter("phonenumber");
			String zipCode = request.getParameter("zipcode");
			
			
			String registerDayString = request.getParameter("registerDay");
			Date registerDay = null;
			try {
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				registerDay = dateFormat.parse(registerDayString);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				throw new ServletException("Error parsing register date (format is yyyy-MM-dd)");
			}
			
			//System.out.println("registerDay:"+registerDay);
			
			
			Customer newCustomer = new Customer();
			newCustomer.setCustomerId(id);
			newCustomer.setEmail(email);
			newCustomer.setFullName(fullName);
			newCustomer.setPassword(password);
			newCustomer.setAddress(address);
			newCustomer.setCity(city);
			newCustomer.setCountry(country);
			newCustomer.setPhoneNumber(phoneNumber);
			newCustomer.setRegisterDay(registerDay);
			newCustomer.setZipCode(zipCode);
			
			Customer updated =  customerDAO.update(newCustomer);
			
			listCustomer("Updated Customer successfully");
		}
		
	}
	
	public void deleteCustomer() throws ServletException, IOException{
		Integer id = Integer.parseInt(request.getParameter("id"));
		customerDAO.delete(id);
		
		listCustomer("Deleted Customer successfully");
	}
	
	public void showCustomerRegisterForm() throws IOException, ServletException{
		List<Category> listCategory = categoryDAO.listAll();
		request.setAttribute("listCategory", listCategory);
		
		String page = "frontend/register_form.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
		
		
	}
	
	public void registerCustomer() throws IOException, ServletException{
		String message = "";
		String typeMessage = "";

		
		System.out.println("registerCustomer");
		String email = request.getParameter("email");
		
		Customer search = customerDAO.findByEmail(email);
		if (search!=null) {
			message = "Could not register, customer with email\""+email+"\" already exists";
			typeMessage = "alert";
		}
		else {
			String fullName = request.getParameter("fullname");
			String password = request.getParameter("password");
			String address = request.getParameter("address");
			String city = request.getParameter("city");
			String country = request.getParameter("country");
			String phoneNumber = request.getParameter("phonenumber");
			String zipCode = request.getParameter("zipcode");
			
			Customer newCustomer = new Customer();
			newCustomer.setEmail(email);
			newCustomer.setFullName(fullName);
			newCustomer.setPassword(password);
			newCustomer.setAddress(address);
			newCustomer.setCity(city);
			newCustomer.setCountry(country);
			newCustomer.setPhoneNumber(phoneNumber);
			newCustomer.setZipCode(zipCode);
			newCustomer.setRegisterDay(new Date());
			Customer created = customerDAO.create(newCustomer);
			message = "You have registered successfully! Thank you.";
			typeMessage = "success";
		}
		
	
		//System.out.println("Created new customer successfully");
		
		String messagePage = "frontend/message.jsp";
		
		
		
		request.setAttribute("message", message);
		request.setAttribute("typeMessage", typeMessage);

		
		RequestDispatcher dispatcher = request.getRequestDispatcher(messagePage);
		dispatcher.forward(request, response);
		//listCustomer("Created new customer successfully");
	}
	
	public void showLogin() throws IOException, ServletException{
		String page = "frontend/login_form.jsp";
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}
	
	public void doLogin() throws IOException, ServletException{
		boolean isValidLoggin = false;

		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		Customer search = customerDAO.checkLogin(email, password);
		if (search!=null) {
			isValidLoggin = true;
		}
		
		if(isValidLoggin==true) {
			System.out.println("isValidLoggin==true");
			//String page = "frontend/customer_profile.jsp";
			
			request.getSession().setAttribute("loggedCustomer", search);
			//request.setAttribute("loggedCustomer", search);
			//RequestDispatcher dispatcher = request.getRequestDispatcher(page);
			//dispatcher.forward(request, response);
			
			HttpSession session = request.getSession();
			Object objRedirectURL = session.getAttribute("redirectURL");
			if (objRedirectURL != null) {
				String redirectURL = (String) objRedirectURL;
				System.out.println("redirectURL:"+redirectURL);

				session.removeAttribute("redirectURL");
				
				response.sendRedirect(redirectURL);
			}
			
			else {
				showCustomerProfile();

			}
			
			
		}else {
			showLogin();
		}
	}
	
	public void showCustomerProfile() throws IOException, ServletException {
		String page = "frontend/customer_profile.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}

	
	public void showCustomerEditProfileForm() throws IOException, ServletException {
		String page = "frontend/edit_profile.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}
}
