package com.bookstore.dao;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.entity.Book;
import com.bookstore.entity.Category;

public class BookDAOTest{
	private static BookDAO bookDAO;
	
	@BeforeClass
	public static void setUpClass() {
		bookDAO = new BookDAO();
	}
	
	@AfterClass
	public static void tearDownClass() {
		bookDAO.close();
	}
	
	
	@Test
	public void testCreateBook() throws ParseException, IOException{
		Book book = new Book();
		Category category = new Category("Advance Java");
		category.setCategoryId(3);
		book.setCategory(category);
		book.setTitle("Effective Java (2nd Edition)");
		book.setAuthor("Joshua Bloch");
		book.setDescription("New coverage of generics, enums, annotations, autoboxing");
		book.setIsbn("0321356683");
		book.setPrice(38.87f);
		
		
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date publishDate = dateFormat.parse("05/28/2008");
		book.setPublishDate(publishDate);
		
		String imagePath = "D:\\servlet\\19 Code BookDAO and Unit Tests\\064 dummy-data-books\\books\\Effective Java.jpg";
		
		byte[] imageBytes = Files.readAllBytes(Paths.get(imagePath));
		book.setImage(imageBytes);
		
//		boolean test = true;
//		assertTrue(test);
		System.out.println("hello1");

		Book createdBook = bookDAO.create(book);
		System.out.println("hello2");
		assertTrue(createdBook.getBookId()>0);
		
//		Book newBook = new Book();
//		System.out.println("hELOOOO");
//		String dir = "dummy_books";
//		File file = new File(dir);
//		for(String name: file.list()) {
//			System.out.println(name);
//
//			if (name.contains(".txt") && (!name.equals("Book Template.txt"))) {
//				String content = new String(Files.readAllBytes(Paths.get(dir+"/"+name)));
//				
//				Pattern categoryPattern = Pattern.compile("^(Category:) (.+)" , Pattern.MULTILINE);
//				Pattern titlePattern = Pattern.compile("^(Title:) (.+)", Pattern.MULTILINE);
//				Pattern authorPattern = Pattern.compile("^(Author:) (.+)", Pattern.MULTILINE);
//				Pattern isbnPattern = Pattern.compile("^(ISBN:) (.+)" , Pattern.MULTILINE);
//				Pattern pricePattern = Pattern.compile("^(Price:) (.+)",  Pattern.MULTILINE);
//				Pattern pushlishDatePattern = Pattern.compile("^(Publish Date:) (.+)", Pattern.MULTILINE);
//				
//				Matcher matcher;
//				
//				matcher = titlePattern.matcher(content);
//				if (matcher.find()) {
//					String title = matcher.group(2);
//					String imagePath = name.split("- ")[1].split(".txt")[0]+".jpg";
//					byte[] image = Files.readAllBytes(Paths.get(dir+"/"+imagePath));
//					
//					newBook.setTitle(title);
//					newBook.setImage(image);
//					
//				}
//				matcher=categoryPattern.matcher(content);
//				if (matcher.find()) {
//					String categoryString = matcher.group(2);
//					Category category = new Category(categoryString);
//					
//					newBook.setCategory(category);
//
//				}
//				matcher=authorPattern.matcher(content);
//				if (matcher.find()) {
//					String author = matcher.group(2);
//					
//					newBook.setAuthor(author);
//
//				}
//				matcher=isbnPattern.matcher(content);
//				if (matcher.find()) {
//					String isbn = matcher.group(2);
//					
//					newBook.setIsbn(isbn);
//
//				}
//				matcher=pricePattern.matcher(content);
//				if (matcher.find()) {
//					Float price = Float.parseFloat(matcher.group(2));
//					
//					newBook.setPrice(price);
//
//				}
//				matcher=pushlishDatePattern.matcher(content);
//				if (matcher.find()) {
//					String pushlishDateString = matcher.group(2);
//					
//					DateFormat dateFormat = new SimpleDateFormat("MMMM dd, YYYY");
//					Date pushlishDate = dateFormat.parse(pushlishDateString);
//					
//					newBook.setPublishDate(pushlishDate);
//
//				}
//				
//				Book createdBook = bookDAO.create(newBook);
//					
//			}
			
			
		
	}
	@Test
	public void testUpdate() throws ParseException, IOException{
		Book existedBook = new Book();
		existedBook.setBookId(10);
		
		Category category = new Category("Advanced Java");
		category.setCategoryId(15);
		existedBook.setCategory(category);
		existedBook.setTitle("Spring in Action: Covers Spring 4");
		existedBook.setAuthor("Craig Walls");
		existedBook.setDescription("Spring in Action, Fourth Edition is a hands-on guide to the Spring Framework, updated for version 4.");
		existedBook.setIsbn("161729120X");
		existedBook.setPrice(33.99f);
		
		
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date publishDate = dateFormat.parse("11/30/2014");
		existedBook.setPublishDate(publishDate);
		
		String imagePath = "D:\\servlet\\19 Code BookDAO and Unit Tests\\064 dummy-data-books\\books\\Spring in Action.jpg";
		
		byte[] imageBytes = Files.readAllBytes(Paths.get(imagePath));
		existedBook.setImage(imageBytes);
		
//		boolean test = true;
//		assertTrue(test);
		System.out.println("HELLO1");
		Book updatedBook = bookDAO.update(existedBook);
		System.out.println("HELLO2");

		assertEquals(updatedBook.getTitle(), "Spring in Action: Covers Spring 4");
	}
	
	@Test
	public void testGet() {
		Integer idToGet = 33;
		Book search = bookDAO.get(idToGet);

		assertNotNull(search);
	}
	
	@Test
	public void testDelete() {
		Integer idToDelete = 10;
		bookDAO.delete(idToDelete);
		
		Book search = bookDAO.get(idToDelete);
		assertNull(search);
	}
	
	@Test
	public void testListAll() {
		List<Book> list = bookDAO.listAll();
		
//		// check size
//		Integer expected = 5;
//		Integer actual = list.size();	
//		assertEquals(expected, actual);
		
		assertTrue(list.size()>0);
	}
	
	@Test
	public void testFindByTitle() {
		String titleToFind = "Effective Java (2nd Edition)";
		Book search = bookDAO.findByTitle(titleToFind);
		assertNotNull(search);
	}
	
	@Test
	public void testCountAll() {
		long actual = bookDAO.count();
		long expected  = 1;
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testListByCategory() {
		int categoryId = 7;
		List<Book> list = bookDAO.listByCategory(categoryId);
		
		int expected = 4;
		int actual = list.size();
		assertEquals(expected, actual);
	}
	
	@Test
	public void testListNewBooks() {
		List<Book> list = bookDAO.listNewBooks();
		
		int expected = 4;
		int actual = list.size();
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testSearch() {
		List<Book> list = bookDAO.search("Java");
		
		int expected = 6;
		int actual = list.size();
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testListBestSelling() {
		List<Book> list= bookDAO.listBestSelling();
		
		
		assertEquals(31, list.get(0).getBookId());
		assertEquals(33, list.get(3).getBookId());
	}
	
	@Test
	public void testMostFavored() {
		List<Book> list= bookDAO.mostFavoredBook();
		
		
		assertEquals(32, list.get(0).getBookId());
	}
}
