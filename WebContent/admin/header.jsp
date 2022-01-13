<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<div align = "center"> 	
	<div>
		<a href="${pageContext.request.contextPath}/admin/">
		<img class="banner img-fluid"  alt="admin_logo_pic" src="../images/logo7.jpg" />
		</a>
	</div>
		<br><br>
	<div>
	<h4>Welcome, <c:out value="${sessionScope.useremail}" /> | <a href="logout">Logout</a></h4>	
	</div>
	
	<br>
<!-- 		<div align="center"> -->
<!-- 		<a href="list_users">Users</a> -->
<!-- 		<a href="list_categories">Categories</a> -->
<!-- 		<a href="books">Books</a> -->
<!-- 		<a href="customers">Customers</a> -->
<!-- 		<a href="reviews">Reviews</a> -->
<!-- 		<a href="orders">Orders</a> -->
		<h2>Administrative Dashboard</h2>
		
	</div>
	<br>
<ul class="nav nav-pills mb-3 list-inline justify-content-center"  >
  <li class="nav-item">
    <a class="nav-link active m-1"  href="list_users">Users</a>
  </li>
  <li class="nav-item">
    <a class="nav-link active m-1" href="list_categories">Categories</a>
  </li>
    <li class="nav-item">
    <a class="nav-link active m-1" href="list_books">Books</a>
  </li>
    <li class="nav-item">
    <a class="nav-link active m-1" href="list_customers">Customers</a>
  </li>
    <li class="nav-item">
    <a class="nav-link active m-1" href="list_reviews">Reviews</a>
  </li>
      <li class="nav-item">
    <a class="nav-link active m-1" href="list_orders" >Orders</a>
  </li>
</ul>



</div>
