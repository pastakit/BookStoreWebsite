<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Book Detail</title>

<script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
<link rel="stylesheet" href="//use.fontawesome.com/releases/v5.0.7/css/all.css">
<link rel="stylesheet" href="css/style.css">

</head>
<body>
		<jsp:directive.include file="header.jsp" />
		
			<div class="container">
			  <div class="row">
			    <div class="col">
			      <h5>${book.title}</h5>
			      <span class = "book_detail_title">
			      by ${book.author} 
			      </span>
				<img width = "210" height="275" alt="book_thumbnail" src="data:image/jpg;base64, ${book.base64Image}">&nbsp;
			      
			    </div>
			    
			    <div class="col-6 book_detail_rating">
			      <jsp:directive.include file="book_rating.jsp" /> 
			      <a href="#reviews" class="">${book.reviews.size()} Reviews</a>
			      <br><br><br><br>
			      <p>
			      	${book.description}
			      </p>

			    </div>
			    <div class="col book_detail_price">
			      	<span class="book_col_price">$${book.price}</span><br><br>
			      	<button id="buttonAddToCart" type="submit">Add to cart</button>
			    </div>
			  </div>
			  
			  
			  	<div class="row">
		
			    
			    <div class="col-6 ">
					<div id="reviews">
					<h3>Customer reviews</h3>
					<c:forEach items="${book.reviews}" var="review">
				  		<div class="row">
							<span>
								<c:forTokens items="${review.getStars()}" delims="," var="token">
									<c:if test="${token=='on'}">
										<img alt="star_on" src="images/rating_on.png">	
									</c:if>
									<c:if test="${token=='off'}">
										<img alt="star_off" src="images/rating_off.png">	
									</c:if>
									
								</c:forTokens>
							</span>
							<h5 class="m-0 p-0 mt-1 ml-1">${review.headline}</h5>
						</div>
						
						<div class="row">
							<span>
								by <i>${review.customer.fullName}</i> on ${review.reviewTime}
							</span>
						</div>
						
						<div class="row">
							<p>
								"${review.comment}"
							</p>
						</div>
							
					</c:forEach>
					</div>
			    </div>
			    
			    <div class="col-6 book_detail_rating">
					<div>
						<a href="write_review?book_id=${book.bookId}"> <input type="button" value="Write Review" /> </a> 
					</div>

			    </div>
			  </div>
			  
			  

			  </div>
			&nbsp;
		<jsp:directive.include file="footer.jsp" />
		
	<script type="text/javascript">
		$(document).ready(function() {
			$("#buttonAddToCart").click(function() {
				window.location = "add_to_cart?book_id="+${book.bookId}
			});
		});
	</script>
</body>
</html>