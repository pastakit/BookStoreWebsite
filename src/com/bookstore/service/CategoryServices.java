package com.bookstore.service;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.dao.BookDAO;
import com.bookstore.dao.CategoryDAO;
import com.bookstore.entity.Book;
import com.bookstore.entity.Category;

public class CategoryServices {
	private CategoryDAO categoryDAO;
	private BookDAO bookDAO;
	
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	
	public CategoryServices(HttpServletRequest request, HttpServletResponse response) {
		categoryDAO = new CategoryDAO();
		bookDAO = new BookDAO();
		this.request=request;
		this.response=response;
		
	}
	
	public void listCategory(String message) throws ServletException, IOException{
		System.out.println("listCategory function");
		List<Category> listCategories = categoryDAO.listAll();
		request.setAttribute("listCategories", listCategories);
		
		if (message!=null) {
			request.setAttribute("message", message);
		}
		
		String listPage = "category_list.jsp";
		RequestDispatcher dispatcher  = request.getRequestDispatcher(listPage);
		dispatcher.forward(request, response);
		
	}
	
	public void listCategory() throws ServletException, IOException{
		listCategory(null);
		
	}
	
	public void createCategory() throws ServletException, IOException{
		String name = request.getParameter("name");
		if(categoryDAO.findByName(name)!=null) {
			String message = "Category "+ name+" already exists";
			String typeMessage = "alert";
			//System.out.println(message);
			request.setAttribute("message", message);
			request.setAttribute("typeMessage", typeMessage);

			RequestDispatcher dispatcher = request.getRequestDispatcher("message.jsp");
			dispatcher.forward(request, response);
		}
		else {
			Category cate = new Category(name);
			categoryDAO.create(cate);
			listCategory("New Category created successfully");
			
		}
	}
	
	public void editCategory() throws ServletException, IOException{
		Integer  cateId = Integer.parseInt(request.getParameter("id"));
		//System.out.println("cate id ="+cateId);
		
		Category category = categoryDAO.get(cateId);
		
		request.setAttribute("category", category);
		
		String page = "category_form.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
		
	}
	
	public void updateCategory() throws ServletException, IOException{
		Integer cateId = Integer.parseInt(request.getParameter("categoryId"));
		String name = request.getParameter("name");
			
		Category cateById = categoryDAO.get(cateId);
		Category cateByName = categoryDAO.findByName(name);
		// cateId,
		// 1 C++ 2 Python 3 C#
		
		
		if ((cateByName != null) && (cateByName.getCategoryId() != cateById.getCategoryId())) {
			String alreadyExistsMessage = "Category \""+name+"\" already exists!!!!";
			request.setAttribute("message", alreadyExistsMessage);
			request.setAttribute("typeMessage", "alert");

			RequestDispatcher dispatcher = request.getRequestDispatcher("message.jsp");
			dispatcher.forward(request, response);
			
		}
		else {
			System.out.println("Successful creation");
//			Category category = new Category(cateId, name);
			cateById.setName(name);
			categoryDAO.update(cateById);
			listCategory("Updated successfully");
			
		}
			
	}
	
	public void deleteCategogy() throws ServletException, IOException {
		Integer cateId = Integer.parseInt(request.getParameter("id"));
		
		System.out.println("deleteCategogy");
		// can not delete
		List<Book> booksByCategory = bookDAO.listByCategory(cateId);
		
		int numBooks = booksByCategory.size();
		if(numBooks>0) {
			request.setAttribute("message", "category id " +cateId+"has some books belong to");
			request.setAttribute("typeMessage", "alert");

			RequestDispatcher dispatcher = request.getRequestDispatcher("message.jsp");
			dispatcher.forward(request, response);
		}
		else {
			categoryDAO.delete(cateId);
			listCategory("Deleted Category with ID="+cateId);
		}
		

		
		
	}
}
