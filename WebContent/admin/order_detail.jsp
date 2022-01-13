<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
    
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
		<h2>Details of Order</h2>
	</div>
		<br>
	
<c:if test="${message!=null}">
	<div class="alert alert-success text-center" role="alert">
		<strong>${message}</strong> 
	</div>
</c:if>

<div align="center" id="order_overview">
	<h3>Order overview</h3>
	<table class="table table-striped">
		<tr>
			<td><b>Order by:</b></td>
			<td>${order.customer.fullName}</td>
		</tr>
		<tr>
			<td><b>Book copies:</b></td>
			<td>${order.bookCopies}</td>
		</tr>		
		<tr>
			<td><b>Total amount:</b></td>
			<td>${order.orderTotal}</td>
		</tr>		
	
			<tr>
			<td><b>Recipient name:</b></td>
			<td>${order.recipientName}</td>
		</tr>
		<tr>
			<td><b>Recipient phone:</b></td>
			<td>${order.recipientPhone}</td>
		</tr>			
		<tr>
			<td><b>Ship to:</b></td>
			<td>${order.shippingAddress}</td>
		</tr>	
		<tr>
			<td><b>Payment method:</b></td>
			<td>${order.paymentMethod}</td>
		</tr>
		<tr>
			<td><b>Order Date:</b></td>
			<td>${order.orderDate}</td>
		</tr>			
		<tr>
			<td><b>Order status:</b></td>
			<td>${order.orderStatus}</td>
		</tr>	
		
	</table>
</div>

<div align="center" id="order_details">
	<h3>Ordered Books</h3>
	<table class="table table-striped">
  <thead>
    <tr >
		<th width="" scope="col">#</th>
		<th width="" scope="col">Book title</th>
		<th width="" scope="col">Author</th>
		<th width="" scope="col">Price</th>
		<th width="" scope="col">Quantity</th>
		<th width="" scope="col">Sub Total</th>

    </tr>
  </thead>
  <tbody>

    	<c:forEach var="od" items="${order.orderDetails}" varStatus="status">
				<tr>
					<td>${status.index}</td>
					<td>${od.book.title}</td>
					<td>${od.book.author}</td>
					<td><fmt:formatNumber value="${od.book.price}" type="currency"/></td>
					<td>${od.quantity}</td>
					<td><fmt:formatNumber value="${od.subTotal}" type="currency"/></td>
				</tr>
				
			</c:forEach>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td><b>Total:</b></td>
					<td><b>${order.bookCopies}</b></td>
					<td><b><fmt:formatNumber value="${order.orderTotal}" type="currency"/></b></td>
				</tr>
  </tbody>
</table>
</div>

	<jsp:directive.include file="footer.jsp" />
	
</body>
</html>