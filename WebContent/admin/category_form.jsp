<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
   
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
		<c:if test="${category!=null}">
			Edit Category
		</c:if>
		
		<c:if test="${category==null}">
			Create New Category
		</c:if>
	</div>
	
	<div align="center">
		<c:if test="${category!=null}">
		<form action="update_category" method="post" onsubmit="return validateFormInput()">
		<input type="hidden" name="categoryId" value="${category.categoryId}">
		</c:if>	
		
		<c:if test="${category==null}">
		<form action="create_category" method="post" onsubmit="return validateFormInput()">
		</c:if>	
			<table>
				<tr>
					<td>Name:</td>
					<td><input type="text" id="name" name="name" size="20" value="${category.name}" /></td>
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
		var fieldName = document.getElementById("name");


		if (fieldName.value.length == 0){
			alert("Name is requied");
			fieldName.focus();
			return false;	
		}
		return true;
	}
</script>
</html>