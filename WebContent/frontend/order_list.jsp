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
		<h2>My Orders</h2>
<!-- 		<a href="category_form.jsp">Create New Cateogry</a> -->
	</div>
		<br>
	
	
<c:if test="${message!=null}">
	<div class="alert alert-success text-center" role="alert">
		<strong>${message}</strong> 
	</div>
</c:if>
		<div id="order_list">
	<table class="table table-striped">
  <thead>
    <tr >
		<th width="" scope="col">#</th>
		<th width="" scope="col">Order Id</th>
		<th width="" scope="col">Ordered by</th>
		<th width="" scope="col">Book copies</th>
		<th width="" scope="col">Total</th>
		<th width="" scope="col">Payment method</th>
		<th width="" scope="col">Status</th>
		<th width="" scope="col">Order date</th>
		<th width="" scope="col">Actions</th>
    </tr>
  </thead>
  <tbody>

    	<c:forEach var="order" items="${listOrders}" varStatus="status">
				<tr>
					<td>${status.index}</td>
					<td>${order.orderId}</td>
					<td>${order.customer.fullName}</td>
					<td>${order.getBookCopies()}</td>
					<td><fmt:formatNumber value="${order.orderTotal}" type="currency"/></td>
					<td>${order.paymentMethod}</td>
					<td>${order.orderStatus}</td>
					<td>${order.orderDate}</td>
					<td>
						<a href="view_order?id=${order.orderId}">Details</a> 
					</td>
				</tr>	
			</c:forEach>
  </tbody>
</table>
</div>

	<jsp:directive.include file="footer.jsp" />
	
</body>
</html>