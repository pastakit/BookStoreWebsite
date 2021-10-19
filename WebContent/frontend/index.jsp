<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
	
	<div align="center">
		<h2>New Books:</h2>
				<div class="container">
			<div class="row">
			
				<c:forEach var = "book" items="${listNewBooks}">
					<div class="col-3 book_col align-items-end" >
						<img width = "84" height="110" alt="thumbnail" src="data:image/jpg;base64,${book.base64Image}">&nbsp;
						<br>
						<span class="book_col_title">${book.title}</span>
						<br>
						<span class="book_col_rating">Rating 5*</span>
						<br>
						<span class="book_col_author">by ${book.author}</span>
						<br>
						<span class="book_col_price">$${book.price}</span>
						
						<br>
					</div>
	
				</c:forEach>

			</div>
		</div>
		<h2>Best-selling books</h2>
		<h2>Most-favored books</h2>
	</div>

	<jsp:directive.include file="footer.jsp" />


</body>
</html>