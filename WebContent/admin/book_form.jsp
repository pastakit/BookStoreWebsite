<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create New User</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
<link rel="stylesheet" href="//use.fontawesome.com/releases/v5.0.7/css/all.css">
<link rel="stylesheet" href="../css/style.css">
</head>
<body>
	<jsp:directive.include file="header.jsp" />

	<div align="center">
		
		<c:if test="${book!=null}">
			Edit Book
		</c:if>
		
		
		<c:if test="${book==null}">
			Create New Book
		</c:if>

	</div>

	<div align="center">
		<c:if test="${book!=null}">
		<form action="update_book" method="post" enctype="multipart/form-data">
		<input type="hidden" name="bookId" value="${book.bookId}">
		</c:if>	
		
		<c:if test="${book==null}">
		<form action="create_book" method="post"  enctype="multipart/form-data">
		</c:if>	
			
			<table>
<!-- 				<tr> -->
<!-- 					<td>Email:</td> -->
<%-- 					<td><input type="text" id="email" name="email" size="20" value="${user.email}" /></td> --%>
<!-- 				</tr> -->

<!-- 				<tr> -->
<!-- 					<td>Full Name:</td> -->
<%-- 					<td><input type="text" id="fullname" name="fullname" size="20" value="${user.fullName}" /></td> --%>
<!-- 				</tr> -->
<!-- 				<tr> -->
<!-- 					<td>Password:</td> -->
<%-- 					<td><input type="password" id="password" name="password" size="20" value="${user.password}"/></td> --%>
<!-- 				</tr> -->

				<tr>
					
					<td>Category:</td>
					<td>
						<select name="category" id="category">
							<c:forEach var="cate" items="${listCategory}" varStatus="status">
								<c:choose>
									<c:when test="${cate.getCategoryId()==book.getCategory().getCategoryId()}">
										<option value="${cate.categoryId}" selected="selected">${cate.name}</option>
									
									</c:when>
									<c:otherwise>
										<option value="${cate.categoryId}">${cate.name}</option>
									</c:otherwise>
								</c:choose>	
							</c:forEach>
						</select>
					</td>
				
				</tr>	
				
				<tr>
					<td>Title:</td>
					<td><input type="text" id="title" name="title" size="20" value="${book.title}" /></td>
				</tr>
				<tr>
					<td>Author:</td>
					<td><input type="text" id="author" name="author" size="20" value="${book.author}" /></td>
				</tr>				
				<tr>
					<td>ISBN:</td>
					<td><input type="text" id="isbn" name="isbn" size="20" value="${book.isbn}" /></td>
				</tr>				
				<tr>
					<td>Publish Date:</td>
					<td><input type="date" id="publishdate" name="publishdate" value="<fmt:formatDate pattern='yyyy-MM-dd' value='${book.publishDate}'/>  "/> </td>
				</tr>				
				<tr>
					<td>Book Image:</td>
					<td><input  type="file" id="image" name="image" size="20"/>
						<div id="imagepreview">
							<img alt="preview image" src="data:image/jpg;base64,${book.base64Image}" id="imagepreview__image">
						</div>
					</td>
				</tr>
				<tr>
					<td>Price:</td>
					<td><input type="text" id="price" name="price" size="20" value="${book.price}" />

					</td>
				</tr>								
				<tr>
					<td>Description:</td>
					<td>
					
					<textarea rows="5" cols="50" id="description" name="description" >${book.description}</textarea>
					</td>
				</tr>
				
				<tr>
					<td><input type="submit" value="Save" /></td>
					<td><input type="button" value="Cancel" onclick = "javascript:history.go(-1);" /></td>
				</tr>
				
			</table>
		</form>
	</div>
	<br>


	<jsp:directive.include file="footer.jsp" />


</body>

<script type="text/javascript">

	function validateFormInput(){
		var fieldEmail = document.getElementById("email");
		var fieldFullName = document.getElementById("fullname");
		var fieldPassWord = document.getElementById("password");

		if (fieldEmail.value.length == 0){
			alert("Email is requied");
			fieldEmail.focus();
			return false;
		}
		if (fieldFullName.value.length == 0){
			alert("Full name is requied");
			fieldFullName.focus();
			return false;
		}
		if (fieldPassWord.value.length == 0){
			alert("Password is requied");
			fieldPassWord.focus();
			return false;
		}
		return true;
	}
	
	
	const inputFile = document.querySelector("#image");
	const imagePreview = document.querySelector("#imagepreview__image");
	
	console.log("hello");
	
	inputFile.addEventListener("change", function() {
		file = inputFile.files[0];
		if (file){
			var reader = new FileReader();
			reader.addEventListener("load", function() {
				imagePreview["src"] = this.result;
			})
			
			reader.readAsDataURL(file);
		}
	})
	
</script>

</html>