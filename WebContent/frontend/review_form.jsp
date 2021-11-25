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

<script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="js/jquery.validate.min.js"></script>

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/rateYo/2.3.2/jquery.rateyo.min.css">
<!-- Latest compiled and minified JavaScript -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/rateYo/2.3.2/jquery.rateyo.min.js"></script>
</head>
<body>
	<jsp:directive.include file="header.jsp" />
	
	<div align="center">
		Your Review
	</div>

	<div align="center">
		<form id="reviewForm" action="submit_review" method="post">
			<div class="container">
	  			<div class="row">
					<div class="col-6">
						Your reviews
					</div>
					<div class="col-6">
						${loggedCustomer.fullName}
					</div>
			  	</div>
				<hr>
			  	<div class="row">
					<div class="col-2">
						<h5>${book.title}</h5>
						<img width = "210" height="275" alt="book_thumbnail" src="data:image/jpg;base64, ${book.base64Image}">
					</div>
   					<div class="col-6">
   						<form action="submit_review" method="post">
							<table>
								<input type="hidden" name="bookId" value="${book.bookId}" />
								<tr>
									<div id="rateYo"></div>
									<input type="hidden" id="rating" name="rating" />
									<input type="hidden" name="bookId" value ="${book.bookId}" />
								</tr>
								<tr>
<!-- 									<td>Headline:</td> -->
									<td><input placeholder="headline here" type="text" id="headline" name="headline" size="20" value="" /></td>
								</tr>
								<tr>
<!-- 									<td>Comment:</td> -->
									<td><textarea placeholder="comment here" id="comment" name="comment" rows="10" ></textarea> </td>
								</tr>
								<tr>
									<td><input type="submit" value="Submit" /></td>
									<td><input type="button" value="Cancel" id="buttonCancel" /></td>
								</tr>
	
							</table>
						</form>
					</div>
			  	</div>
			 </div>
			
		</form>
	</div>
	<br>


	<jsp:directive.include file="footer.jsp" />
	
<script>
	$(document).ready(function() {
		
		
		$("#buttonCancel").click(function() {
			history.go(-1);
		})
		
		$("#reviewForm").validate({
			rules:{
				headline: "required",
				comment:"required"
			},
			
			messages:{
				headline:"please enter headline",
				comment:"please enter comment"
			}
		})
		
		//--------- rateYo
		$("#rateYo").rateYo({
			starWidth:"40px",
			fullStar: true,
			onSet: function(rating, rateYoInstance){
				$("#rating").val(rating);
			}
		})
	})
		
</script>
</body>
</html>