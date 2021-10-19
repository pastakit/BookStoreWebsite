<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 
   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin Login</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
<link rel="stylesheet" href="//use.fontawesome.com/releases/v5.0.7/css/all.css">
<link rel="stylesheet" href="../css/style.css">
</head>	
<body>
	<div align="center">
		<h1>Book Store Administration</h1>
		
		<c:if test="${message!=null}">
			<div class="alert alert-danger" role="alert">
			  ${message}
			</div>
		</c:if>
		
		<form action="login" method="post">
		  <div class="form-group">
		  	<label for="email">Email address</label>
		  
		  	<div class="col-sm-5">
			    <input type="email" class="form-control" id="email" name="email">
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="password">Password</label>
		   	<div class="col-sm-5">
		    	<input type="password" class="form-control" id="password" name="password">
		  	</div>
		  
		  </div>
		  <button type="submit" class="btn btn-primary">Submit</button>
		</form>
	</div>
</body>
</html>