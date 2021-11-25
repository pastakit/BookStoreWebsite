<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
	
	<div align="center">
		Customer Login
	</div>

	<div align="center">
		<c:if test="${message!=null}">
			<div class="alert alert-success text-center" role="alert">
				<strong>${message}</strong> 
			</div>
		</c:if>
		<form action="login" method="post">
			
			<table>
				<tr>
					<td>Email:</td>
					<td><input type="text" id="email" name="email" size="20" value="" /></td>
				</tr>
				<tr>
					<td>Password:</td>
					<td><input type="password" id="password" name="password" size="20" value=""/></td>
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
</html>