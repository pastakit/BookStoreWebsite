<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-88	59-1">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
<link rel="stylesheet" href="//use.fontawesome.com/releases/v5.0.7/css/all.css">
<link rel="stylesheet" href="css/style.css">
</head>	
<body>
	<jsp:directive.include file="header.jsp" />
	&nbsp;
	<c:if test="${typeMessage=='alert'}">
	<div align="center">
		<div class="alert alert-danger" role="alert">
  			<strong>${message}</strong> 
		</div>
	</div>		
	</c:if>
	
	<c:if test="${typeMessage=='success'}">
		<div align="center">
			<div class="alert alert-success" role="alert">
	  			<strong>${message}</strong>
			</div>
		</div>
	</c:if>
	
	
<!-- 	<div align="center"> -->
<%-- 		<h4	>${message}</h4> --%>
<!-- 	</div> -->
	



	<jsp:directive.include file="footer.jsp" />

</body>
</html>