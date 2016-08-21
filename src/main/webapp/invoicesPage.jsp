<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<script type="text/javascript">
	function openModal()
	{
		$("#myModal").modal()
	}
	
	$(document).ready(function() {
        $('.ajaxCon').click(function()
        {
            $.ajax({
                type: "get",
                url: "http://localhost:9001/imsWeb/clientInfo.do", //this is my servlet
                data: "input=" +$('#ip').val()+"&output="+$('#op').val(),
                success: function(msg)
                {      
                        openModal();
                }
            });
        });

    });
	
 function clientInfo()
{
   $.ajax({
         type: "get",
        url: "http://localhost:9001/imsWeb/clientInfo.do", //this is my servlet
          data: "input=" +$('#ip').val()+"&output="+$('#op').val(),
    success: function(msg)
   {      
            openModal();
     }
	});
}
</script> 

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Generate Invoice</title>
<!-- BootStrap -->

	<!-- MODAL BOOTSTRAP -->
	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
  	<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<!-- MODAL BOOTSTRAP -->
	
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

	<div id="radioBtn" class="visible radio-inline">
		<label><input type="radio" name="invChoice" value="Incoming"/>Supplier</label>
		<label><input type="radio" name="invChoice" value="Outgoing"/>Retailer</label>
	</div>
	<br />
	<br />
	
		<table class="invisible">
			
		</table>
	</div>
	
	<div>
		<table id="invoiceTable">
			<tr>
		    	<th>Client</th>
		    	<th>Product</th>
		    	<th>Price</th>
		    	<th>Quantity</th>
		    	<th>Cost</th>
		    	<th>Subtotal</th>
		    	<th>Option</th>
		  	</tr>
		  	<tr>
		  	<td><span id="dropDownClient"> 
				<select name="clients" onchange="">  <!-- combo box for clients based on type, needs AJAX -->
				<option value="def" selected>Please select a client!</option>
				<c:forEach var="c" items="${clients}">
				<option value="${c.id}" onclick="javascript:getProducts(${c.name})">${c.name}</option>
				</c:forEach>
				</select>
				</span>
			</td>
			<td>
				<select name="products">  <!-- combo box for clients based on type, needs AJAX -->
				<option value="def" selected>Please select a product!</option>
				<c:forEach var="p" items="${products}">
				<option value="${p.id}">${p.name}</option>
				</c:forEach>
				</select>
			</td>
		  	<td><label value="${p.cost}"></label></td>  <!-- THESE ALL NEED WIDTH: 100% IN CSS -->
		  	 <td><input type="number" name="numOfProds" onchange="javascript:orderPrice(this.value)"><label>${prodPrice}</label>
				<!--  <button>Change amount!</button>  <!-- THIS LITERALLY DOES NOTHING, AND WILL DO NOTHING, BUT IS NECESSARY FOR THE ABOVE ONCHANGE!!! -->
			</td>
		  	 <td><label value="0"></label></td>
		  	 <td><input type="button" value="Add!"></td>
		  	 </tr>
		  	
		  	
		  <c:forEach var="i" items="${invoices}">
		  	<tr onclick="" data-toggle="modal" data-target="#myModal"> <!-- WILL INSERT JS TO SHOW MORE DETAILS IN POP-UP -->
		  		<td><c:out value="${i.invoiceCK}"></c:out></td>
		  		<td><c:out value="${i.price}"></c:out></td>
		  		<td><c:out value="${i.prodId.name}, ${i.prodId.cost}"></c:out></td>
		  		<td><c:out value="${i.quantity}"></c:out></td>
		  		<td><c:out value="THIS NEEDS STUFF"></c:out></td> <!-- NEED AJAX CALL TO GET CLIENT -->
		  		<td><input type="button" value="Edit!"></td> <!-- Need to make this...Ajax-y... -->
		  	</tr>
		  </c:forEach>
		  <tr id="blanktr" class="blanktr"></tr>
		 
		  	
		</table>
		<br />
		
		<div class="container footer .col-xs-12 .col-sm-6 .col-lg-8">
			<jsp:include page="/footerPage/footer.jsp"></jsp:include>
		</div>
		
		<br />
	</div>
	
	</div>
	<br />
	
	<!-- Trigger the modal with a button -->
	<button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#myModal">Open Modal</button>
	
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
	<br />
	

	<br />
	</div>
	
	
		
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
</body>
</html>