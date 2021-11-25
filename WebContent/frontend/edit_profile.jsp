<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Evergreen Books - Online Books Store</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
<link rel="stylesheet" href="//use.fontawesome.com/releases/v5.0.7/css/all.css">
<link rel="stylesheet" href="css/style.css">

</head>
<body>
	<jsp:directive.include file="header.jsp" />
	
	<div align="center">
		Edit My Profile
	</div>

	<div align="center">
		<form action="edit_profile" method="post">
			
			<table>
				<tr>
					<td>Email:</td>
					<td>${loggedCustomer.email}  &nbsp;<b>(Can not be changed)</b></td>
					
				</tr>

				<tr>
					<td>Full Name:</td>
					<td><input type="text" id="fullname" name="fullname" size="20" value="${loggedCustomer.fullName}" /></td>
				</tr>

				<tr>
					<td>Phone Number:</td>
					<td><input type="text" id="phonenumber" name="phonenumber" size="20" value="${loggedCustomer.phoneNumber}"/></td>
				</tr>				
				
				<tr>
					<td>Address:</td>
					<td><input type="text" id="address" name="address" size="20" value="${loggedCustomer.address}"/></td>
				</tr>				
				<tr>
					<td>City:</td>
					<td><input type="text" id="city" name="city" size="20" value="${loggedCustomer.city}"/></td>
				</tr>				
				<tr>
					<td>Zip Code:</td>
					<td><input type="text" id="zipcode" name="zipcode" size="20" value="${loggedCustomer.zipCode}"/></td>
				</tr>				
				<tr>
					<td>Country:</td>
					<td><input type="text" id="country" name="country" size="20" value="${loggedCustomer.country}"/></td>
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