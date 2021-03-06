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

	<jsp:directive.include file="header.jsp" />

	<div align="center">
		<h2>Cagories Management</h2>
		<a href="category_form.jsp" class="admin_create_link">Create New Cateogry</a>
	</div>
		<br>
	

<!-- 	<div align="center"> -->
<%-- 		<h4><i>${message}</i></h4> --%>
<!-- 	</div> -->

<!-- 	<div align="center"> -->
<!-- 		<table border="1"> -->
<!-- 			<tr> -->
<!-- 				<th>Index</th> -->
<!-- 				<th>ID</th> -->
<!-- 				<th>Name</th> -->
<!-- 				<th>Actions</th> -->
				
<!-- 			</tr> -->

<%-- 			<c:forEach var="cate" items="${listCategories}" varStatus="status"> --%>
<!-- 				<tr> -->
<%-- 					<td>${status.index}</td> --%>
<%-- 					<td>${cate.categoryId}</td> --%>
<%-- 					<td>${cate.name}</td> --%>
					
<!-- 					<td> -->
					
<%-- 					<a href="edit_category?id=${cate.categoryId}">Edit</a>  --%>
<%-- 					<a href="delete_category?id=${cate.categoryId}">Delete</a> --%>

<!-- 					</td> -->
<!-- 				</tr> -->
				
<%-- 			</c:forEach> --%>

<!-- 		</table> -->

<!-- 	</div> -->
	
<c:if test="${message!=null}">
	<div class="alert alert-success text-center" role="alert">
		<strong>${message}</strong> 
	</div>
</c:if>
		<div id="category_list">
	<table class="table table-striped">
  <thead>
    <tr >
		<th width="" scope="col">#</th>
		<th width="" scope="col">ID</th>
		<th width="" scope="col">Name</th>
		<th width="" scope="col">Actions</th>
    </tr>
  </thead>
  <tbody>

    	<c:forEach var="cate" items="${listCategories}" varStatus="status">
				<tr>
					<td>${status.index}</td>
					<td>${cate.categoryId}</td>
					<td>${cate.name}</td>
					<td>
						<a href="edit_category?id=${cate.categoryId}">Edit</a> 
						<a href="delete_category?id=${cate.categoryId}">Delete</a>

					</td>
				</tr>
				
			</c:forEach>
    
  </tbody>
</table>
</div>

	<jsp:directive.include file="footer.jsp" />
	
</body>
</html>