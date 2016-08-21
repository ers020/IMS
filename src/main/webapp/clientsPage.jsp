<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Client Section</title>

	<!-- jQuery -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>

	<!-- BootStrap -->
	<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/resources/css/bootstrap.css">
	<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/resources/css/bootstrap.min.css">
	<script src="${pageContext.servletContext.contextPath}/resources/js/bootstrap.js"></script>
	<script src="${pageContext.servletContext.contextPath}/resources/js/bootstrap.min.js"></script>
	
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
			<table id="personTable">
				<tr>
		    		<th>ID</th>
		    		<th>Name</th>
		    		<th>Email</th>
		    		<th>Address</th>
		    		<th>Type</th>
		    		<th>Option</th>
		  		</tr>
		 		 <c:forEach var="c" items="${clients}">
		  		<tr>
		  			<td><c:out value="${c.id}"></c:out></td>
		  			<td><c:out value="${c.name}"></c:out></td>
		  			<td><c:out value="${c.email}"></c:out></td>
		  			<td><c:out value="${c.address.city}, ${c.address.state.name}"></c:out></td>
		  			<td>
		  				<c:if test="${c.clientType.id == 1}"><c:out value="Supplier"></c:out></c:if>
		  				<c:if test="${c.clientType.id == 2}"><c:out value="Retailer"></c:out></c:if>
		  			</td>
		  			<td><input type="button" value="Edit!"></td> <!-- Need to make this...Ajax-y... -->
		  		</tr>
		  		</c:forEach>
		 	 <tr id="blanktr" class="blanktr"></tr>
		  	<tr>
		  		<td></td>
		  	 	<td><input type="text" name="name" placeholder="Name!"/></td>
		  	 	<td><input type="text" name="name" placeholder="Email!"/></td>
		  		<td><input type="text" name="name" placeholder="Address!"/></td>
		  	 	<td><input type="text" name="name" placeholder="Type!"/></td>
		  	</tr>
			</table>
		</div>
		<br />
		<!-- Trigger the modal with a button -->
		<button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#myModal">Open Modal</button>
		<br />
		<br />
			<div class="container footer .col-xs-12 .col-sm-6 .col-lg-8">
				<jsp:include page="/footerPage/footer.jsp"></jsp:include>
			</div>
		<br />
	
		</div>
		<br />
	
	
	
	<!-- Modal -->
	<div id="myModal" class="modal fade" role="dialog">
	  <div class="modal-dialog">
	
	    <!-- Modal content-->
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal">&times;</button>
	        <h4 class="modal-title">Modal Header</h4>
	      </div>
	      <div class="modal-body">
	      <table id="modal-table">
	        <tr><td>Name:</td><td>"${client.name}"</td></tr>
	        <tr><td>Email:</td><td>"${client.email}"</td></tr>
	        <tr><td>Contact Name:</td><td>"${client.pocName}"</td></tr>
	        <tr><td>Phone:</td><td>"${client.phone}"</td></tr>
	        <tr><td>Fax:</td><td>"${client.fax}"</td></tr>
	        <tr><td>Address:</td><td>"${clientAddress}"</td></tr>
	        <tr><td>Type:</td><td>"${clientType}"</td></tr>
	        </table>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	      </div>
	    </div>
	
	  </div>

	  
	</div>
	
		
</body>
<script type="text/javascript">
$(document).ready(function(){

	$.ajax({
		headers: {          
    			"Accept" : "application/json"
    		},
		url:"https://localhost9001/IMS/getAll.do",
		method: "GET",
		success:function(response){
		$("#personTable").html("<tr><th>Name</th><th>Age</th><th>Email</th></tr>");
			$.each(response, function(c, clients){
				$("#personTable").append(
					"<tr><td>" 		+ clients.id
					+"</td><td>" 	+ clients.name
					+"</td><td>"	+ clients.email
					+"</td><td>"	+ clients.address.city + ", " + client.state.name
					+"</td><td>"	+ clients.clientType.clientType
					+"</td></tr>");
			
			});
		}
		
		
	
	})

})
</script>
</html>