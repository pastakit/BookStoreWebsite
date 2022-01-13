<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript" src="../js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="../js/jquery.validate.min.js"></script>

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
<link rel="stylesheet" href="//use.fontawesome.com/releases/v5.0.7/css/all.css">
<link rel="stylesheet" href="../css/style.css">


</head>
<body>

	<jsp:directive.include file="header.jsp" />
<div align="center">

	<div align="center">
		<h2>Edit your Order ID ${order.orderId}</h2>
	</div>
		<br>
	
<c:if test="${message!=null}">
	<div class="alert alert-success text-center" role="alert">
		<strong>${message}</strong> 
	</div>
</c:if>
<form method="post" action="update_order" id="orderForm">
<div align="center" id="order_overview">
	<h3>Order overview</h3>
	<table class="table table-striped">
		
		<tr>
			<td><b>Order by:</b></td>
			<td>${order.customer.fullName}</td>
		</tr>
		<tr>
			<td><b>Order Date:</b></td>
			<td>${order.orderDate}</td>
		</tr>	
	
		<tr>
			<td><b>Recipient name:</b></td>
			<td><input type="text" name="recipientname"  size="30" value="${order.recipientName}"/></td>
		</tr>
		<tr>
			<td><b>Recipient phone:</b></td>
			<td><input type="text" name="recipientphone"  size="30" value="${order.recipientPhone}"/></td>
		</tr>		
		
		<tr>
			<td><b>Ship to:</b></td>
			<td><input type="text" name="shippingAddress"  size="30" value="${order.shippingAddress}"/></td>
		</tr>	
		<tr>
			<td><b>Payment method:</b></td>
			<td>
				    <select class="form-control" id="paymentMethodSelect" name="payment">
				      <option>Cash on Delivery</option>
				      <option>Credit card</option>
	    			</select>
			</td>
			
			
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
		<th width="" scope="col"><u><a href="#">Add Books</a></u></th>

    </tr>
  </thead>
  <tbody>

    	<c:forEach var="od" items="${order.orderDetails}" varStatus="status">
				<tr>
					<td>${status.index}</td>
					<td>${od.book.title}</td>
					<td>${od.book.author}</td>
					<td>
						
						<input type="hidden" name="bookId" value="${od.book.bookId}" />
						<input type="hidden" name="price" value="${od.book.price}" />
						
						<fmt:formatNumber value="${od.book.price}" type="currency"/>
					</td>
					<td>
					<input type="text" name="quantity${status.index}"  size="1" value="${od.quantity}"/>
					</td>
					<td><fmt:formatNumber value="${od.subTotal}" type="currency"/></td>
					<td><u><a href="remove_book_from_order?id=${od.book.bookId}">Remove</a></u></td>
				</tr>	
		</c:forEach>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td><b>Total:</b></td>
					<td><b>${order.bookCopies}</b></td>
					<td><b><fmt:formatNumber value="${order.orderTotal}" type="currency"/></b></td>
					<td></td>
				</tr>
  </tbody>
</table>
</div>
<div align="center">
	<a href="javascript:showAddBookPopup()">Add books</a>
	<button type="submit">Update</button>
	<button type="button">Cancel</button>
</div>

<form/>
	</div>

	<jsp:directive.include file="footer.jsp" />
	
	<script type="text/javascript">
		function showAddBookPopup(){
			var width = 800;
			var height= 400;
			var left=(screen.width - width)/2;
			var top= (screen.height - height)/2;
			
		
			window.open('add_book_form', '_blank', 'width='+width+','+'height='+height+',top='+top+',left='+left);
		}
		
		$(document).ready(function() {
			$("#orderForm").validate({
				
				rules:{
					recipientname: "required",
					recipientphone: "required",
					shippingAddress: "required",
					
					<c:forEach var="item" items="${order.orderDetails}" varStatus="status">
						quantity${status.index}:{
							required:true,
							number:true,
							min:1
						},
					</c:forEach>
				},
			
			messages:{
				recipientname: "please enter recipientname",
				recipientphone: "please enter recipientphone",
				shippingAddress: "please enter shippingAddress",
				<c:forEach var="item" items="${order.orderDetails}" varStatus="status">
				quantity${status.index}:{
					required:"please enter quantity",
					number: "quantity must be a number",
					min:"minimum is 1"
				},
				</c:forEach>
			}
				
			});
		});
	
	</script>
	
</body>
</html>