package com.bookstore.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bookstore.dao.BookDAO;
import com.bookstore.dao.ReviewDAO;
import com.bookstore.entity.Book;
import com.bookstore.entity.Customer;
import com.bookstore.entity.Review;

public class ReviewServices {
	private ReviewDAO reviewDAO;
	
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	public ReviewServices(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;	
		
		reviewDAO = new ReviewDAO();
	}
	
	public void listReview(String message) throws ServletException, IOException{
		List<Review> listReviews = reviewDAO.listAll();
		request.setAttribute("listReviews", listReviews);
		if (message!=null) {
			request.setAttribute("message", message);
		}
		String page = "review_list.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}
	
	public void listReview() throws ServletException, IOException{
		listReview(null);
	}
	
	public void deleteReview() throws IOException, ServletException{
		Integer id = Integer.parseInt(request.getParameter("id"));
		reviewDAO.delete(id);
		
		listReview("Deleted Review successfully");
	}
	
	public void showReviewForm()  throws ServletException, IOException{
		Integer bookId = Integer.parseInt(request.getParameter("book_id"));
		BookDAO bookDAO = new BookDAO();
		Book book = bookDAO.get(bookId);
		
		HttpSession session  = request.getSession();
		session.setAttribute("book", book);
		
		Customer customer = (Customer)session.getAttribute("loggedCustomer");
		Review existReview = reviewDAO.findByCustomerAndBook(customer.getCustomerId(), bookId);
		
		if(existReview !=null) {
			request.setAttribute("review", existReview);
			
			String page = "frontend/review_info.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(page);
			dispatcher.forward(request, response);
		}
		else {
			String page = "frontend/review_form.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(page);
			dispatcher.forward(request, response);
		}
		

	}
	
	public void submitReview() throws ServletException, IOException{
		Integer bookId = Integer.parseInt(request.getParameter("bookId"));
		String headline = request.getParameter("headline");
		String comment = request.getParameter("comment");
		Integer rating = Integer.parseInt(request.getParameter("rating"));
		
		Review newReview = new Review();
		newReview.setHeadline(headline);
		newReview.setComment(comment);
		newReview.setRating(rating);
		

		
		Book book = new Book();
		book.setBookId(bookId);
		newReview.setBook(book);
		
		Customer customer = (Customer)request.getSession().getAttribute("loggedCustomer");
		newReview.setCustomer(customer);
		
		reviewDAO.create(newReview);
		
		String page = "frontend/review_done.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}
	
}
