package com.bookstore.controller.admin.book;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.controller.BaseServlet;
import com.bookstore.service.BookServices;


@WebServlet("/admin/delete_book")
public class DeleteBookServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       

    public DeleteBookServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		BookServices bookServices = new BookServices(em, request, response);
		bookServices.deleteBook();
	}




}
