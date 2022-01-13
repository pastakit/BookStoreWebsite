<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
<link rel="stylesheet" href="//use.fontawesome.com/releases/v5.0.7/css/all.css">
<link rel="stylesheet" href="../css/style.css">
</head>
<body>
	<div align="center">
		<h2>Add book to Order Id:</h2>
		<form action="add_book_to_order" method="post">
			<table>
				<tr>
					<td>Select a book</td>	
					<td>
						<select class="form-control" name="bookId">
					      <c:forEach var="book" items="${listBook}">
					      	<option value="${book.bookId}">${book.title}-${book.author}</option>
					      </c:forEach>
				  		</select>
			  		</td>		  	
			  	</tr>		
			  	<tr>
			  		<td>Number of copies</td>
					<td>
					    <select name="quantity">
					      <c:forEach var="book" items="${listBook}">
					      	<option value="1">1</option>
					      	<option value="2">2</option>
					      	<option value="3">3</option>
					      	<option value="4">4</option>
					      	<option value="5">5</option>
					      	
					      </c:forEach>
				  		</select>
			  		</td>
			  	</tr>
			  	&nbsp;		  	
			  	<tr align="center">
			  		<td >
			  			<input type="submit" value="Add" />
					    <input type="button" value="Cancel" onclick="javascript:self.close()" />
			  		</td>
			  	</tr>	
		  	<table>	
	  	</form>	
	</div>
</body>
</html>