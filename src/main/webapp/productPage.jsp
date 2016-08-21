<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Product Page</title>
	<!-- BootStrap -->
	<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/resources/css/bootstrap.css">
	<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/resources/css/bootstrap.min.css">
	<script src="${pageContext.servletContext.contextPath}/resources/js/bootstrap.js"></script>
	<script src="${pageContext.servletContext.contextPath}/resources/js/bootstrap.min.js"></script>
	
	<!-- jQuery -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
	
	<!-- CSS Google Text -->
	<link href="https://fonts.googleapis.com/css?family=Roboto:400,900" rel="stylesheet" type="text/css">
	
	<!-- Custom CSS -->
	<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/resources/css/index.css">
</head>
<body>

	<div class="container main .col-xs-12 .col-sm-6 .col-lg-8">

		<div class="container header .col-xs-12 .col-sm-6 .col-lg-8">
			<jsp:include page="/headerPage/header.jsp"></jsp:include>
		</div>
	
		<div class="container nav .col-xs-12 .col-sm-6 .col-lg-8">
			<jsp:include page="/navPage/nav.jsp"></jsp:include>
		</div>
		<br />
	
		<div class="container body .col-xs-12 .col-sm-6 .col-lg-8">
			<table id="productTable">
				<tr>
		    		<th>Product UPC</th>
		    		<th>Name</th>
		    		<th>Cost</th>
		    		<th>In Stock</th>
		    		<th>Description</th>
		    		<th>Option</th>
		    		
		  		</tr>
		 		 <c:forEach var="p" items="${products}">
		  		<tr>
		  			<td><c:out value="${products.id}"></c:out></td>
		  			<td><c:out value="${products.name}"></c:out></td>
		  			<td><c:out value="${products.cost}"></c:out></td>
		  			<td><c:out value="${products.stock}"></c:out></td>
		  			<td><c:out value="${products.description}"></c:out></td>
		  			<td><input type="button" value="Delete"></td> <!-- Need to make this...Ajax-y... -->
		  		</tr>
		  		</c:forEach>
		 	 <tr id="blanktr" class="blanktr"></tr>
		  	<tr>
		  		<td></td>
		  	 	<td><input type="text" name="name" placeholder="Product Name"/></td>
		  	 	<td><input type="text" name="name" placeholder="Cost"/></td>
		  		<td><input type="text" name="name" placeholder="In Stock"/></td>
		  	 	<td><input type="text" name="name" placeholder="Description"/></td>
		  	 	<td></td>
		  	</tr>
			</table>
		</div>
		<br />
		<!-- Trigger the modal with a button -->
		<button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#myModal">Open Modal</button>
		<br />
		<br />
		</div>
		<br />
	
		<div class="container footer .col-xs-12 .col-sm-6 .col-lg-8">
			<jsp:include page="/footerPage/footer.jsp"></jsp:include>
		</div>
		<br />
	
	</div>	
	
</body>
</html>