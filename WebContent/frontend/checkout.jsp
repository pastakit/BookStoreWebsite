<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="js/jquery.validate.min.js"></script>

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
<link rel="stylesheet" href="//use.fontawesome.com/releases/v5.0.7/css/all.css">
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<jsp:directive.include file="header.jsp" />
	
	<div align="center">
		
		<h3>Review your Order Details <a href="view_cart">Edit</a> </h3>
		<c:set var="cartVariable" value="${sessionScope['cart']}"></c:set>
		
		<c:if test="${cartVariable.totalItems==0}">
			<h2>There's no items in your cart</h2>
		</c:if>
		
		<c:if test="${cartVariable.totalItems>0}">
			
			<div id="cart_list">
				<form method="post" action="update_cart"  id="cartForm">
					
					<table class="table table-striped">
						<thead>
							<tr>
								<th width="" scope="col">No</th>
								<th width="" scope="col">Book</th>
								<th width="" scope="col">Price</th>
								
								<th width="" scope="col">Quantity</th>
								<th width="" scope="col">Subtotal</th>
<!-- 								<th width="" scope="col"> <button id="clearCart">Clear cart</button> </th> -->
<!-- 								<th width="" scope="col"> <input type="button" id="clearCart" value="Clear cart" /> </th> -->
								
							</tr>
						</thead>
						
						<tbody>
							<c:forEach var="entry" items="${cartVariable.items}" varStatus="status">
								<tr>
									<td>${status.index}</td>
									<td>
										<img alt="thumbnail" height="110" width="84" src="data:image/jpg;base64, ${entry.key.base64Image}">
										${entry.key.title}
									</td>
									<td> <fmt:formatNumber value="${entry.key.price}" type="currency" />  </td>
									
									<td>
										<input type="hidden" name="bookId" value="${entry.key.bookId}">
<%-- 										<input type="text" name="quantity${status.index}"  size="3" value="${entry.value}"/>	 --%>
										${entry.value}
									</td>
									<td> <fmt:formatNumber value="${entry.key.price*entry.value}" type="currency"/> </td>
<%-- 									<td> <a href="remove_from_cart?book_id=${entry.key.bookId}">Remove</a> </td> --%>
								</tr>
							</c:forEach>	
								<tr>
									<td></td>
									<td></td>
									<td><b>Total:</b></td>	
									<td>${cartVariable.totalQuantity} Books</td>
									<td><fmt:formatNumber value="${cartVariable.totalAmount}" type="currency"/></td>
									
								</tr>
<!-- 								<tr> -->
<!-- 									<td></td> -->
<!-- 									<td></td>									 -->
<!-- 									<td><button type="submit">Update</button></td> -->
<%-- 									<td><a href="${pageContext.request.contextPath}/">Continue Shopping</a></td> --%>
<!-- 									<td><a href="checkout">Checkout</a></td> -->
<!-- 								</tr> -->
						</tbody>
					</table>
				</form>
			</div>
			<div id="shippingFormDiv">
				<h3>Your shipping information</h3>
				<form method="post" action="place_order" id="orderForm">
<!-- 					<table> -->
<!-- 						<tr> -->
<!-- 							<th>No</th> -->
<!-- 							<th>Book</th> -->
<!-- 							<th>Quantity</th> -->
<!-- 							<th>Price</th> -->
<!-- 							<th>Subtotal</th> -->
<!-- 						</tr> -->
<!-- 					</table> -->
					
					<table class="table table-borderless w-50">	
						<tbody>
								
							<tr>
								<td ><b>Recipient name:</b></td>
								<td><input type="text" name="recipientname"  size="30" value=""/></td>
							</tr>
							<tr>
								<td ><b>Recipient phone:</b></td>
								<td><input type="text" name="recipientphone"  size="30" value=""/></td>
							</tr>								
							<tr>
								<td > <b>Street address:</b></td>
								<td><input type="text" name="streetaddress"  size="30" value=""/></td>
							</tr>								
							<tr>
								<td ><b>City:</b></td>
								<td><input type="text" name="city"  size="30" value=""/></td>
							</tr>
							<tr>
								<td ><b>Zipcode:</b></td>
								<td><input type="text" name="zipcode"  size="30" value=""/></td>
							</tr>							
							
							<tr>
								<td><b>Country:</b></td>
								<td><input type="text" name="country"  size="30" value=""/></td>
							</tr>							
							
						</tbody>
					</table>
					
					<div id="paymentSelectDiv">
				<h3>Payment:</h3>	
				
				<div class="form-group">
   					<label for="paymentMethodSelect">choose your payment method:</label>
				    <select class="form-control" id="paymentMethodSelect" name="payment">
				      <option>Cash on Delivery</option>
				      <option>Credit card</option>
	    			</select>
	    		</div>    		
			</div>
			
			<div id="placeAndContinueButtonDiv">				
				<tr>
					<td><button type="submit">Place Order</button></td>
					<td><a href="${pageContext.request.contextPath}/">Continue shopping</a> </td>
				</tr>	
			</div>
				</form>
			</div>
			
			
			
<!-- 			<div id="placeAndContinueButtonDiv">				 -->
<!--     		<table class="table table-borderless w-50">	 -->
<!-- 			<tbody>		 -->
<!-- 				<tr> -->
<!-- 					<td><button type="submit">Place Order</button></td> -->
<%-- 					<td><a href="${pageContext.request.contextPath}/">Continue shopping</a> </td> --%>
<!-- 				</tr>	 -->
<!-- 			</tbody> -->
<!-- 		</table> -->
<!-- 			</div> -->
			
		</c:if>
		
	</div>
	<br>


	<jsp:directive.include file="footer.jsp" />
	
	
	<script type="text/javascript">
		$(document).ready(function() {
			$("#orderForm").validate({
				rules:{
					recipientname: "required" ,
					recipientphone: "required",
					streetaddress: "required",
					city: "required",
					country: "required",
					zipcode: "required"
				},
				messages:{
					recipientname: "Please enter recipientname",
					recipientphone: "Please enter recipientphone",
					streetaddress: "Please enter streetaddress",
					city: "Please enter city",
					country: "Please enter country",
					zipcode: "Please enter zipcode"
				}
			});
				
		});
		
	</script>
</body>
</html>