<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Book Detail</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
<link rel="stylesheet" href="//use.fontawesome.com/releases/v5.0.7/css/all.css">
<link rel="stylesheet" href="css/style.css">
</head>
<body>
		<jsp:directive.include file="header.jsp" />
		
			<div class="container">
			  <div class="row">
			    <div class="col">
			      <h5>${book.title}</h5>
			      <span class = "book_detail_title">
			      by ${book.author} 
			      </span>
				<img width = "210" height="275" alt="book_thumbnail" src="data:image/jpg;base64, ${book.base64Image}">&nbsp;
			      
			    </div>
			    
			    <div class="col-6 book_detail_rating">
			      Rating ***** <br><br><br><br>
			      
			      <p>
			      	${book.description}
			      </p>

			    </div>
			    <div class="col book_detail_price">
			      	<span class="book_col_price">$${book.price}</span><br><br>
			      	<button type="submit">Add to cart</button>
			    </div>
			  </div>
			</div>
		<jsp:directive.include file="footer.jsp" />
</body>
</html>