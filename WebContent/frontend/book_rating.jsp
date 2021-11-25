<c:forTokens items="${book.getRatingString()}" delims="," var="token">
	<c:if test="${token=='on'}">
		<img alt="star" src="images/rating_on.png">
	</c:if>
	
	<c:if test="${token=='off'}">
		<img alt="star" src="images/rating_off.png">
	</c:if>
	
	<c:if test="${token=='half'}">
		<img alt="star" src="images/rating_half.png">
	</c:if>
</c:forTokens>