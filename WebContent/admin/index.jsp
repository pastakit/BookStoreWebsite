<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Book Store Administration</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
<link rel="stylesheet" href="../css/style.css">
<link rel="stylesheet" href="//use.fontawesome.com/releases/v5.0.7/css/all.css">

</head>
<body>

		<jsp:directive.include file="header.jsp" />
		
		<div align="center">
			<hr width="60%">
			<h2>Quick actions:</h2>
			<a href="new_book">New Book</a> 
			<a href="user_form.jsp">New User</a>
			<a href="category_form.jsp">New Category</a> 
			<a href="new_customer">New Customer</a>
	
		</div>

		<div align="center">
			<hr width="60%">

			<h2>Recent sales</h2>
		</div>

		<div align="center">
			<hr width="60%">

			<h2>Recent reviews</h2>
		</div>

		<div align="center">
			<hr width="60%">
			<h2>Statistics</h2>
			<hr width="60%">

		</div>

		<jsp:directive.include file="footer.jsp" />



</body>
</html>