package com.bookstore.service;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.bookstore.dao.BookDAO;
import com.bookstore.dao.CategoryDAO;
import com.bookstore.entity.Book;
import com.bookstore.entity.Category;

public class BookServices {
	private BookDAO bookDAO;
	
	private CategoryDAO categoryDAO;
	
	private EntityManager em;
	
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	public BookServices(EntityManager em, HttpServletRequest request, HttpServletResponse response){
		this.em = em;
		this.request = request;
		this.response = response;
		
		bookDAO = new BookDAO(this.em);
		categoryDAO = new CategoryDAO(this.em);
	}
	
	public void listBook(String message) throws IOException, ServletException{
		List<Book> listBooks = bookDAO.listAll();
		request.setAttribute("listBooks", listBooks);
		
		if(message != null) {
			request.setAttribute("message", message);
		}
		
		String listPage = "book_list.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(listPage);
		dispatcher.forward(request, response);
		
		
	}
	
	public void listBook() throws IOException, ServletException{
		listBook(null);
	}
	
	public void showBookNewForm() throws IOException, ServletException {
		List<Category> listCategory = categoryDAO.listAll();
		request.setAttribute("listCategory", listCategory);
		
		
		String newPage = "book_form.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(newPage);
		dispatcher.forward(request, response);

	}
	
	public void createBook() throws ServletException, IOException{
		Integer categoryId = Integer.parseInt(request.getParameter("category"));
		String title = request.getParameter("title");
		
//		Book existingBook = bookDAO.findByTitle(title);
//		if(existingBook != null) {
//			String message = "Could not create new book because title \"" 
//					+ existingBook.getTitle() + "\" already exists" ;
//			listBook(message);
//			return;
//		}
//		
		String author = request.getParameter("author");
		String isbn = request.getParameter("isbn");
		String description = request.getParameter("description");
		Float price = Float.parseFloat(request.getParameter("price"));
		
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date publishDate = null;
		try {
			publishDate = dateFormat.parse(request.getParameter("publishdate"));

		} catch (ParseException e) {	
			// TODO: handle exception
			e.printStackTrace();
			//throw new ServletException("Error parsing publish date (format is yyyy-MM-dd)");
		}
		
		System.out.println("categoryId:"+categoryId);
		System.out.println("title:"+title);
		System.out.println("author:"+author);
		System.out.println("isbn:"+isbn);
		System.out.println("publishDate:"+publishDate);
		
		
		Book newBook = new Book();
		newBook.setTitle(title);
		newBook.setAuthor(author);
		newBook.setIsbn(isbn);
		newBook.setPublishDate(publishDate);
		newBook.setDescription(description);
		newBook.setPrice(price);

		Category category = categoryDAO.get(categoryId);
		newBook.setCategory(category);
		
		
		
		Part part = request.getPart("image");
		
		if( part!=null && part.getSize()>0) {
			System.out.println("reading image");
			long size = part.getSize();
			byte[] imageBytes = new byte[(int) size];
			
			InputStream inputStream = part.getInputStream();
			inputStream.read(imageBytes);
			inputStream.close();
			newBook.setImage(imageBytes);
		}
		
		System.out.println("passed all");
		
		bookDAO.create(newBook);
		String message = "Created a new book successfully";
		listBook(message);

	}
	
	public void editBook() throws ServletException,IOException{
		Integer bookId = Integer.parseInt(request.getParameter("id"));
		Book book = bookDAO.get(bookId);
		
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String publishDateString = dateFormat.format(book.getPublishDate());
		request.setAttribute("publishDateString", publishDateString);
		request.setAttribute("book", book);
		
		List<Category> listCategory = categoryDAO.listAll();
		request.setAttribute("listCategory", listCategory);
		
		String page  = "book_form.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}
	
	public void deleteBook() throws ServletException, IOException{
		Integer idToDelete = Integer.parseInt(request.getParameter("id"));
		bookDAO.delete(idToDelete);
		listBook("Deleted Book with ID="+idToDelete);

	}
	
	
	
	public void updateBook() throws IOException, ServletException{
		System.out.println("updateBook() function");
		Integer bookId = Integer.parseInt(request.getParameter("bookId"));
		Integer categoryId = Integer.parseInt(request.getParameter("category"));
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		String isbn = request.getParameter("isbn");
		String description = request.getParameter("description");
		Float price = Float.parseFloat(request.getParameter("price"));
		
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date publishDate = null;
		try {
			publishDate = dateFormat.parse(request.getParameter("publishdate"));

		} catch (ParseException e) {	
			// TODO: handle exception
			e.printStackTrace();
			//throw new ServletException("Error parsing publish date (format is yyyy-MM-dd)");
		}
		
		
		Book bookGivenId = bookDAO.get(bookId);
		Book bookGivenTitle = bookDAO.findByTitle(title);
		
		if (bookGivenTitle!=null && bookGivenId.getBookId()!=bookGivenId.getBookId()) {
			String message = "Book with title \""+title+"\" already exists";
			String typeMessage = "alert";
			
			request.setAttribute("message", message);
			request.setAttribute("typeMessage", typeMessage);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("message.jsp");
			dispatcher.forward(request, response);
			
		} else {
			Book toUpdateBook = new Book();
			

			toUpdateBook.setBookId(bookId);
			toUpdateBook.setTitle(title);
			toUpdateBook.setAuthor(author);
			toUpdateBook.setIsbn(isbn);
			toUpdateBook.setPublishDate(publishDate);
			toUpdateBook.setDescription(description);
			toUpdateBook.setPrice(price);
			
			Category category = categoryDAO.get(categoryId);
			toUpdateBook.setCategory(category);
			
			Part part = request.getPart("image");
			
			// need to change thumbnail
			if (part!=null && part.getSize()>0) {
				InputStream inputStream = part.getInputStream();
				
				long size = part.getSize();
				byte[] imageBytes = new byte[(int) size];
				
				inputStream.read(imageBytes);
				toUpdateBook.setImage(imageBytes);
				
				inputStream.close();
				
			}
			else {
				toUpdateBook.setImage(bookGivenId.getImage());
			}
			
			bookDAO.update(toUpdateBook);
			listBook("Updated book successfully");	

		}
	}
	
	public void listBooksByCategory() throws IOException, ServletException{
		
		Integer categoryId =  Integer.parseInt(request.getParameter("id"));
		List<Book> listBooks = bookDAO.listByCategory(categoryId);
		request.setAttribute("listBooks", listBooks);
		
		String categoryName = categoryDAO.get(categoryId).getName();
		request.setAttribute("categoryName", categoryName);
		
		String page = "frontend/books_list_by_category.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}
	
	public void viewBookDetail() throws IOException, ServletException{
		Integer bookId = Integer.parseInt(request.getParameter("id"));
		Book book = bookDAO.get(bookId);
		request.setAttribute("book", book);
		
		List<Category> listCategory = categoryDAO.listAll();
		request.setAttribute("listCategory", listCategory);
		
		String page = "frontend/book_detail.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}
	
	public void search() throws IOException, ServletException{
		String keyword = request.getParameter("keyword");
		List<Book> searchResult = bookDAO.search(keyword);
		request.setAttribute("searchResult", searchResult);
		
		request.setAttribute("keyword", keyword);
		
		String page = "frontend/search_result.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}
	
}
