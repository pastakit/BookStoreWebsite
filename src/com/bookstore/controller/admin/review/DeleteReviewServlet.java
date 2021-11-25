package com.bookstore.controller.admin.review;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.service.ReviewServices;

/**
 * Servlet implementation class DeleteReviewServlet
 */
@WebServlet("/admin/delete_review")
public class DeleteReviewServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ReviewServices reviewServices = new ReviewServices(request, response);
		reviewServices.deleteReview();
	}

}
