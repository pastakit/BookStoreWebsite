<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Book Store Administration</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
<link rel="stylesheet" href="//use.fontawesome.com/releases/v5.0.7/css/all.css">
<link rel="stylesheet" href="../css/style.css">

<script type="text/javascript" src="../js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="../js/jquery.validate.min.js"></script>
</head>
<body>
	<jsp:directive.include file="header.jsp" />

	<div align="center">
		<h2>Users Management</h2>
		<a href="user_form.jsp" class="admin_create_link">Create new User</a>
		

	</div>
	<br>

<c:if test="${message!=null}">
<div class="alert alert-success text-center" role="alert">
 <strong>${message}</strong> 
</div>
</c:if>
	<div id="user_list">
	<table class="table table-striped">
  <thead>
    <tr >
		<th width="" scope="col">#</th>
		<th width="" scope="col">ID</th>
		<th width="" scope="col">Email</th>
		<th width="" scope="col">Full Name</th>	
		<th width=""scope="col">Actions</th>
    </tr>
  </thead>
  <tbody>
  	<c:forEach var="user" items="${listUsers}" varStatus="status">
	    <tr>
		  	<td>${status.index }</td>
			<td>${user.userId}</td>
			<td>${user.email}</td>
			<td>${user.fullName}</td>
			<td>
				<a href="edit_user?id=${user.userId}">Edit</a> 
				<a href="javascript:void(0);" class="deleteLink" id="${user.userId}">Delete</a>
				
			</td>
	    </tr>
    </c:forEach>
    
  </tbody>
</table>
</div>
	<jsp:directive.include file="footer.jsp" />

	<script type="text/javascript">
	
		$(document).ready(function() {
			$(".deleteLink").each(function(){
				$(this).on("click", function(){
	 				userId = $(this).attr("id");
	 				if ( confirm('Are you sure to delete the user with ID '+userId)){
	 					window.location='delete_user?id='+userId;
	 				}
				})
			})
		})
		
	
	</script>
</body>
</html>