<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>


<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Client Section</title>

	
	<!-- MODAL BOOTSTRAP -->
	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
 	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
  	<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>


	<!-- BootStrap -->
	<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/resources/css/bootstrap.css">
	<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/resources/css/bootstrap.min.css">
	<script src="${pageContext.servletContext.contextPath}/resources/js/bootstrap.js"></script>
	<script src="${pageContext.servletContext.contextPath}/resources/js/bootstrap.min.js"></script>
	
	<!-- jQuery -->
	
	
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
		  			<td><button type="button" class="btn btn-primary" value="${c}" data-toggle="modal" data-target="#clientEditModal">Edit</button></td> <!-- Need to make this...Ajax-y... -->
		  		</tr>
		  		</c:forEach>
			</table>
		</div>
		<br/>
		
		<!-- Trigger the modal with a button -->
		<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#clientModal">Add Client</button>
		<br />	
		<br />
			<div class="container footer .col-xs-12 .col-sm-6 .col-lg-8">
				<jsp:include page="/footerPage/footer.jsp"></jsp:include>
			</div>
		<br />
		</div>
		<br />
		
			
	
	
	<!-- Modal -->
	<div id="clientModal" class="modal fade" role="dialog">
	  <div class="modal-dialog">
	
	
	    <!-- Modal content-->
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal">&times;</button>
	        <h4 class="modal-title">Add a Client</h4>
	      </div>
	      <div class="modal-body">
	      <table id="modal-table">
	        <tr>
	        	<td>Client:</td>
	        	<td><input id="clientName" type="text" class="form-control" name="clientName"></td>
	        	
	        </tr>
	        <tr>
	        	<td>Email:</td>
	        	<td><input id="email" type="text" class="form-control" name="email"></td>
	        </tr>
	        <tr>
	        	<td>Contact Name:</td>
	        	<td><input id="contactName" type="text" class="form-control" name="contactName"></td>
	        </tr>
	        <tr>
	        	<td>Phone:</td>
	        	<td><input id="phone" type="text" class="form-control" name="Phone"></td>
	        </tr>
	        <tr>
	        	<td>Fax:</td>
	        	<td><input id="fax" type="text" class="form-control" name="fax"></td>
	        </tr>
	        <tr>
	        	<td>Address Line 1:</td>
	        	<td><input id="adLine1" type="text" class="form-control" name="adLine1"></td>
	        </tr>
	        <tr>
	        	<td>Address Line 2:</td>
	        	<td><input id="adLine2" type="text" class="form-control" name="adLine2"></td>
	        </tr>
	        <tr>
	        	<td>City:</td>
	        	<td><input id="city" type="text" class="form-control" name="city"></td>
	        </tr>
	        <tr>
	        	<td>State:</td>
	        	<td>
	        		<select name="state" id="state" class="form-control" id="state">
	        			<option selected disabled>Select a State</option>
	        			<c:forEach var="s" items="${states}">
	        			<option id="${s.id}" value="${s.id}"><c:out value="${s.name}"></c:out></option>
	        			</c:forEach>
	        		</select>
	        	</td>
	        </tr>
	        <tr>
	        	<td>Zip:</td>
	        	<td><input id="zip" type="text" class="form-control" name="zip"></td>
	        </tr>
	        <tr>
	        	<td>Type:</td>
	        	<td>
					<select name="type" id="type" class="form-control">
						<option selected disabled>Select Client Type</option>
						<c:forEach var="t" items="${clientTypes}">
						<option value="${t.id}"><c:out value="${t.clientList}"></c:out></option>
						</c:forEach>
					</select>
				</td>
	        </tr>
	        </table>
	      </div>
	    <div class="modal-footer">
        <input id="addClient" type="button"  value="Add" class="btn btn-primary" ></input>
        <button type="button" class="btn btn-warning" data-dismiss="modal">Cancel</button>
      	</div>
	    </div>
	  </div>
	</div>
	

		<!-- Modal for Updating and Deletion -->
	<div id="clientEditModal" class="modal fade" role="dialog">
	  <div class="modal-dialog">
	
	    <!-- Modal content-->
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal">&times;</button>
	        <h4 class="modal-title">Edit a Client</h4>
	      </div>
	      <div class="modal-body">
	      <table id="modal-table">
	      	<tr>
	      		<td>Id:</td>
	      		<td><c:out value="${c.id}"></c:out></td>
	      	</tr>
	        <tr>
	        	<td>Client:</td>
	        	<td><input id="clientName" type="text" class="form-control" name="clientName"><c:out value="${c.name}"></c:out></td>
	        	
	        </tr>
	        <tr>
	        	<td>Email:</td>
	        	<td><input id="email" type="text" class="form-control" name="email"><c:out value="${c.email}"></c:out></td>
	        </tr>
	        <tr>
	        	<td>Contact Name:</td>
	        	<td><input id="contactName" type="text" class="form-control" name="contactName"><c:out value="${c.pocName}"></c:out></td>
	        </tr>
	        <tr>
	        	<td>Phone:</td>
	        	<td><input id="phone" type="text" class="form-control" name="Phone"></td>
	        </tr>
	        <tr>
	        	<td>Fax:</td>
	        	<td><input id="fax" type="text" class="form-control" name="fax"></td>
	        </tr>
	        <tr>
	        	<td>Address Line 1:</td>
	        	<td><input id="adLine1" type="text" class="form-control" name="adLine1"></td>
	        </tr>
	        <tr>
	        	<td>Address Line 2:</td>
	        	<td><input id="adLine2" type="text" class="form-control" name="adLine2"></td>
	        </tr>
	        <tr>
	        	<td>City:</td>
	        	<td><input id="city" type="text" class="form-control" name="city"></td>
	        </tr>
	        <tr>
	        	<td>State:</td>
	        	<td>
	        		<select name="state" id="state" class="form-control" id="state">
	        			<option selected disabled>Select a State</option>
	        			<c:forEach var="s" items="${states}">
	        			<option id="${s.id}" value="${s.id}"><c:out value="${s.name}"></c:out></option>
	        			</c:forEach>
	        		</select>
	        	</td>
	        </tr>
	        <tr>
	        	<td>Zip:</td>
	        	<td><input id="zip" type="text" class="form-control" name="zip"></td>
	        </tr>
	        <tr>
	        	<td>Type:</td>
	        	<td>
					<select name="type" id="type" class="form-control">
						<option selected disabled>Select Client Type</option>
						<c:forEach var="t" items="${clientTypes}">
						<option value="${t.id}"><c:out value="${t.clientList}"></c:out></option>
						</c:forEach>
					</select>
				</td>
	        </tr>
	        </table>
	      </div>
	    <div class="modal-footer">
        <input id="editClient" type="button"  value="Update" class="btn btn-success" ></input>
        <input id="deleteClient" type="button"  value="Delete" class="btn btn-danger" ></input>
        <button type="button" class="btn btn-warning" data-dismiss="modal">Cancel</button>
      	</div>
	    </div>
	  </div>
	</div>
	
</body>

<script type="text/javascript">

		function openModal()
		{
			jQuery("#clientModal").modal()
		}

	jQuery(document).ready(function(){
		jQuery("#addClient").click(function(){
			var clientName = jQuery("#clientName").val();
			var email = jQuery("#email").val();
			var contactName = jQuery("#contactName").val();
			var phone = jQuery("#phone").val();
			var fax = jQuery("#fax").val();
			var adLine1 = jQuery("#adLine1").val();
			var adLine2 = jQuery("#adLine2").val();
			var city = jQuery("#city").val();
			var stateId = jQuery("#state").val();
			var zip = jQuery("#zip").val();
			var typeId = jQuery("#type").val();
			jQuery.ajax({
			// contentType application/json
			headers: {          
    			"Content-Type": "application/json"
    		},
			url: "http://localhost:9001/IMS/insertClient.do",
			method: "POST",
			data: JSON.stringify({
				name : clientName, email : email, 
				pocName : contactName, phone : phone,
				fax : fax, addLine1 : adLine1, addLine2 : adLine2,
				stateId : stateId, city : city, zip: zip, clientTypeId : typeId
			}),
			success: function(){
				alert("Added Client successfully!");
			}
		});
	});
	
});

</script>
</html>