package com.bookstore.controller.frontend;

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


@WebFilter("/*")
public class CustomerLoginFilter implements Filter {
	private static final String[] loginRequiredURLs = {
			"/view_profile", "/write_review", "/checkout","/place_order", 
			"/view_orders", "/view_order"
	};

    public CustomerLoginFilter() {
        // TODO Auto-generated constructor stub
    }


	public void destroy() {
		// TODO Auto-generated method stub
	}


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here

		// pass the request along the filter chain
		System.out.println("---CustomerLoginFilter doFilter -----");

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session = httpRequest.getSession(false);
		
		String path = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());
		System.out.println("path:"+path);
		
		String requestURL = httpRequest.getRequestURL().toString();
		String requestURI = httpRequest.getRequestURI().toString();
		String fullRequestUrl = requestURL + "?"+httpRequest.getQueryString();
		System.out.println("requestURL:"+requestURL);
		System.out.println("requestURI:"+requestURI);
		System.out.println("fullRequestUrl:"+fullRequestUrl);

		
		if (path.startsWith("/admin/")) {
			System.out.println("if (path.startsWith(\"/admin/\"))");

			chain.doFilter(request, response);
			return;
		}
		
		boolean loggedIn = session!=null && session.getAttribute("loggedCustomer") != null;
		
		if (!loggedIn && this.isLoginRequired(path)) {
			//System.out.println("not login customer");
			
			String redirectURL = requestURL;
			String queryString = httpRequest.getQueryString();
			
			if (queryString!=null){
				System.out.println("queryString:"+queryString);
				redirectURL += "?"+queryString;
			}
			
			session.setAttribute("redirectURL", redirectURL);
			
			String page = "frontend/login_form.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(page);
			dispatcher.forward(request, response);
		}else {
			chain.doFilter(request, response);
		}
		
		System.out.println("22222222");
		
		//chain.doFilter(request, response);
	}
	
	private boolean isLoginRequired(String requestURL) {
		for (String loginRequiredURL :loginRequiredURLs) {
			if (requestURL.contains(loginRequiredURL)) {
				return true;
			}
		}
		return false;
	}

	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
