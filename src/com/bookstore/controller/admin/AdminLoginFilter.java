package com.bookstore.controller.admin;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@WebFilter("/admin/*")
public class AdminLoginFilter implements Filter {


    public AdminLoginFilter() {
        // TODO Auto-generated constructor stub
    }


	public void destroy() {
		// TODO Auto-generated method stub
	}


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		System.out.println("---AdminLoginFilter doFilter -----");

		HttpServletRequest httpRequest = (HttpServletRequest)request;
		HttpSession session=  httpRequest.getSession(false); // false: if not exists=> null
		
		boolean isLoggedIn = (session!=null) && (session.getAttribute("useremail")!=null);	
		boolean isPage = httpRequest.getRequestURI().equals(httpRequest.getContextPath()+"/admin/login.jsp");
		boolean isLogginRequest = httpRequest.getRequestURI().equals(httpRequest.getContextPath()+"/admin/login");
		
		if (isLoggedIn) {
			System.out.println("already loggined");

			if (isPage || isLogginRequest) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/");
				dispatcher.forward(request, response);
			}
			else {
				chain.doFilter(request, response);

			}
		}
		
		else {
			System.out.println("not login");
			System.out.println("httpRequest.getRequestURI():" +httpRequest.getRequestURI());
			if (isLogginRequest) {
				System.out.println("isLogginRequest is true");
				chain.doFilter(request, response);
			}
			else {
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
				dispatcher.forward(request, response);
			}

		}
		
//		System.out.println("context path:"+ httpRequest.getContextPath());
//		System.out.println("getRequestURI:"+ httpRequest.getRequestURI());
		// pass the request along the filter chain
	}


	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
