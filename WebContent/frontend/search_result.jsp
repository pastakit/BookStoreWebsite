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
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<jsp:directive.include file="header.jsp" />
	&nbsp;
	<h4 align="center">Search for "${keyword}"</h4>&nbsp;
		<div class="container">
		<c:forEach items="${searchResult}" var="book">
		  <div class="row book_result_item .row-eq-height mt-2 mb-2" >
		    <div class="col-2 h-100">
		      <!-- Content -->
		      <img alt="book_thumbnail"  width = "84" height="110" src="data:image/jpg;base64,${book.base64Image}">
		      
		    </div>
		    <div class="col-8 h-100">
		      <!-- Content -->
		      <div class="book_title_rating_author">
		      	<span>${book.title}</span><br>
		      	<span>5 *****</span><br>
		      	<span>${book.author}</span>
		      	
		      </div>
   		      <div class="book_description">
		      	<p>${book.description}</p>      	
		      </div>
		    </div>
		    <div class="col-2 h-100">
		      <!-- Content -->
		      <span class="book_price">$${book.price}</span><br><br>
			  <button type="submit">Add to cart</button>
		    </div>
		  </div>
		</c:forEach>
		 
		</div>
		
	
	
	<jsp:directive.include file="footer.jsp" />
</body>
</html>