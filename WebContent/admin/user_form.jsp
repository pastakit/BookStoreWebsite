<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
		
		<c:if test="${user!=null}">
			Edit User
		</c:if>
		
		
		<c:if test="${user==null}">
			Create New User
		</c:if>

	</div>

	<div align="center">
		<c:if test="${user!=null}">
		<form action="update_user" method="post" onsubmit="return validateFormInput()">
		<input type="hidden" name="userId" value="${user.userId}">
		</c:if>	
		
		<c:if test="${user==null}">
		<form action="create_user" method="post" onsubmit="return validateFormInput()">
		</c:if>	
			
			<table>
				<tr>
					<td>Email:</td>
					<td><input type="text" id="email" name="email" size="20" value="${user.email}" /></td>
				</tr>

				<tr>
					<td>Full Name:</td>
					<td><input type="text" id="fullname" name="fullname" size="20" value="${user.fullName}" /></td>
				</tr>
				<tr>
					<td>Password:</td>
					<td><input type="password" id="password" name="password" size="20" value="${user.password}"/></td>
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
</script>

</html>