<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div align = "center"> 	
	<div>
		<img class="banner" alt="logo_pic" src="images/logo7.jpg" />
	</div>
	<br><br>
	
	<div>
	
<!-- 	<input type = "text" name="keyword" size=50 /> -->
<!-- 	<input type='button' value="Search"/> -->
		
    <form action="search_book" method="post" class="form-inline justify-content-center">
      <input class="form-control" name="keyword" type="search" placeholder="Search" aria-label="Search">&nbsp;
      <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
      	&nbsp;&nbsp;&nbsp;
      	<a href="login">Login</a>&nbsp;|&nbsp;
		<a href="register">Register</a>&nbsp;|&nbsp; 
		<a href="view_cart">Cart</a>&nbsp
    </form>
		
	</div>
	
	<div>
		<c:forEach var="category" items="${listCategory}" varStatus="status">
			<a href="view_category?id=${category.categoryId}"> ${category.name} </a>
			<c:if test="${not status.last}">
			 |
			</c:if>	
		</c:forEach>
	</div>
	
</div>
