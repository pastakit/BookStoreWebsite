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
		Your Review
	</div>

	<div align="center">
		<form action="submit_review" method="post">
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
   						<h4>Your review has been posted. Thank you</h4> 
					</div>
			  	</div>
			 </div>

		</form>
	</div>
	<br>

	<jsp:directive.include file="footer.jsp" />
</body>
</html>