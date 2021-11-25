package com.bookstore.controller.admin.book;

import java.io.IOException;
import java.text.ParseException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.service.BookServices;

@WebServlet("/admin/create_book")
@MultipartConfig(
		fileSizeThreshold = 1024*10, //10 KB
		maxFileSize = 1024 * 300, // 300KB
		maxRequestSize = 1024 * 1000 // 1MB
)
public class CreateBookServlet extends HttpServlet {
       

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		BookServices bookServices = new BookServices(request, response);
		
		bookServices.createBook();

	}

}
