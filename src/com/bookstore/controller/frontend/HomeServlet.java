package com.bookstore.controller.frontend;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.dao.BookDAO;
import com.bookstore.dao.CategoryDAO;
import com.bookstore.entity.Book;
import com.bookstore.entity.Category;
import com.bookstore.service.CategoryServices;

@WebServlet("")
public class HomeServlet extends HttpServlet {
       
    public HomeServlet() {
        //super();
        // TODO Auto-generated constructor stub
    }
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub 
		CategoryDAO categoryDAO  = new CategoryDAO();
		
		List<Category> listCategory = categoryDAO.listAll();
		
		request.setAttribute("listCategory", listCategory);
		
		BookDAO bookDAO = new BookDAO();
		List<Book> listNewBooks = bookDAO.listNewBooks();
		request.setAttribute("listNewBooks", listNewBooks);
		
		List<Book> listBestSelling = bookDAO.listBestSelling();
		request.setAttribute("listBestSelling", listBestSelling);

		List<Book> listMostFavored = bookDAO.mostFavoredBook();
		request.setAttribute("listMostFavored", listMostFavored);
		
		String homepage = "frontend/index.jsp";
		RequestDispatcher dispatcher =  request.getRequestDispatcher(homepage);
		dispatcher.forward(request, response);
	}
}
