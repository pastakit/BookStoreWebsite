<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Evergreen Books - Online Books Store</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
<link rel="stylesheet" href="//use.fontawesome.com/releases/v5.0.7/css/all.css">
<link rel="stylesheet" href="css/style.css">

</head>
<body>
	<jsp:directive.include file="header.jsp" />
	
		<h3 align="center">${categoryName}</h3>
		&nbsp;&nbsp;&nbsp;
		<div class="container">
			<div class="row">
			
				<c:forEach var = "book" items="${listBooks}">
					<div class="col-3 book_col align-items-end" >
						<img width = "84" height="110" alt="thumbnail" src="data:image/jpg;base64,${book.base64Image}">&nbsp;
						<br>
						<span class="book_col_title"><a href="view_book?id=${book.bookId}">${book.title}</a></span>
						<br>
						<span class="book_col_rating">
							<jsp:directive.include file="book_rating.jsp"/>
						</span>
						<br>
						<span class="book_col_author">by ${book.author}</span>
						<br>
						<span class="book_col_price">$${book.price}</span>
						
						<br>
					</div>
	
				</c:forEach>

			</div>
		</div>
		&nbsp;&nbsp;&nbsp;
		

	<jsp:directive.include file="footer.jsp" />


</body>
</html>