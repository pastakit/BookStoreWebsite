<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>List Books</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
<link rel="stylesheet" href="//use.fontawesome.com/releases/v5.0.7/css/all.css">
<link rel="stylesheet" href="../css/style.css">

<script type="text/javascript" src="../js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="../js/jquery.validate.min.js"></script>
</head>
<body>

	<jsp:directive.include file="header.jsp" />

	<div align="center">
		<h2>Books Management</h2>
		<a href="new_book" class="admin_create_link">Create New Book</a>
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
<div id="book_list">
	<table class="table table-striped">
  <thead>
    <tr >
		<th width="" scope="col">#</th>
		<th width="" scope="col">ID</th>
		
		<th width="" scope="col">Title</th>
				
		<th width="" scope="col">Thumbnail</th>
		
		<th width="" scope="col">Author</th>
		<th width="" scope="col">Category</th>
		
		<th width="" scope="col">Isbn</th>
		<th width="" scope="col">Price</th>
		<th width="" scope="col">Publish Date</th>
		<th width="" scope="col">Last Update Time</th>
		<th width="" scope="col">Actions</th>
    </tr>
  </thead>
  <tbody>

    	<c:forEach var="book" items="${listBooks}" varStatus="status">
				<tr>
					<td>${status.index}</td>
					<td>${book.bookId}</td>
					<td>${book.title}</td>
					
					<td>
					
						<img width = "84" height="110" alt="thumbnail" src="data:image/jpg;base64,${book.base64Image}">
					
					</td>
					
					<td>${book.author}</td>
					<td>${book.category.name}</td>
					<td>${book.isbn}</td>
					<td>${book.price}</td>
					<td>${book.publishDate}</td>
					<td>${book.lastUpdateTime}</td>
					
					<td>
						<a href="edit_book?id=${book.bookId}">Edit</a> 
						<a href="javascript:void(0);" class="deleteLink" id="${book.bookId}" ">Delete</a>
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
 				bookId = $(this).attr("id");
 				if ( confirm('Are you sure to delete the Book with ID '+bookId)){
 					window.location='deleteee_book?id='+bookId;
 				}
			})
		})
	})
	

</script>
	
</body>
</html>