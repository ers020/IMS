<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Product Page</title>

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
		  			<td><c:out value="${p.id}"></c:out></td>
		  			<td><c:out value="${p.name}"></c:out></td>
		  			<td><fmt:formatNumber type="currency"><c:out value="${p.cost}"></c:out></fmt:formatNumber></td>
		  			<td><c:out value="${p.stock}"></c:out></td>
		  			<td><c:out value="${p.description}"></c:out></td>
		  			<td><input type="button" value="Delete"></td> <!-- Need to make this...Ajax-y... -->
		  		</tr>
		  		</c:forEach>
			</table>
		</div>
		<br />
		<!-- Trigger the modal with a button -->
		<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#productModal">Open Modal</button>
		<br />
		<br />
		<div class="container footer .col-xs-12 .col-sm-6 .col-lg-8">
			<jsp:include page="/footerPage/footer.jsp"></jsp:include>
		</div>
		<br />
		</div>
		<br />
	
		
		<br />
	
	<!-- Modal -->
	<div id="productModal" class="modal fade" role="dialog">
	  <div class="modal-dialog">
	
	
	    <!-- Modal content-->
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal">&times;</button>
	        <h4 class="modal-title">Add a Product</h4>
	      </div>
	      <div class="modal-body">
	      <table id="modal-table">
	        <tr>
	        	<td>Product Name:</td>
	        	<td><input id="prodName" type="text" class="form-control" name="prodName"></td>
	        	
	        </tr>
	        <tr>
	        	<td>Short Name:</td>
	        	<td><input id="shortName" type="text" class="form-control" name="shortName"></td>
	        </tr>
	        <tr>
	        	<td>Product Description:</td>
	        	<td><input id="prodDesc" type="text" class="form-control" name="prodDesc"></td>
	        </tr>
	        <tr>
	        	<td>Cost:</td>
	        	<td><input id="cost" type="text" class="form-control" name="cost"></td>
	        </tr>
	        <tr>
	        	<td>Size:</td>
	        	<td><input id="size" type="text" class="form-control" name="size"></td>
	        </tr>
	        <tr>
	        	<td>Stock:</td>
	        	<td><input id="stock" type="text" class="form-control" name="stock"></td>
	        </tr>
	        <tr>
	        	<td>Pre-order Threshold:</td>
	        	<td><input id="quantity" type="text" class="form-control" name="quantity"></td>
	        </tr>
	        <tr>
	        	<td>Retail Price:</td>
	        	<td><input id="retail" type="text" class="form-control" name="retail"></td>
	        </tr>
	        <tr>
	        <td>Category Description:</td>
	        <td><select name="catDesc[]" id="catDesc" class="selectpicker" multiple>
	        	<c:forEach var="cd" items="${catDesc}">
  						<option value="${cd.id}"><c:out value="${cd.description}"></c:out></option>	
  				</c:forEach>
				</select>
			</td>
			</tr>
	        </table>
	      </div>
	    <div class="modal-footer">
        <input id="addProduct" type="button"  value="Add" class="btn btn-primary" ></input>
        <button type="button" class="btn btn-warning" data-dismiss="modal">Cancel</button>
      	</div>
	    </div>
	  </div>
	</div>	
	
</body>

<script type="text/javascript">

		function openModal()
		{
			jQuery("#productModal").modal()
		}
		
	jQuery(document).ready(function(){
		jQuery("#addProduct").click(function(){
			var prodName = jQuery("#prodName").val();
			var shortName = jQuery("#shortName").val();
			var prodDesc = jQuery("#prodDesc").val();
			var cost = jQuery("#cost").val();
			var size = jQuery("#size").val();
			var stock = jQuery("#stock").val();
			var quantity = jQuery("#quantity").val();
			var retail = jQuery("#retail").val();
			var catDesc = jQuery("#catDesc[]").val();
			jQuery.ajax({
			// contentType application/json
			headers: {          
    			"Content-Type": "application/json"
    		},
			url: "http://localhost:9001/IMS/insertProduct.do",
			method: "POST",
			data: JSON.stringify({
				name:prodName, sName:shortName, description:prodDesc,
				strCost:cost, strSize:size, strStock:stock,
				strPreQuantity:quantity, strRetailPrice:retail,
				catDescId:catDesc
			}),
			success: function(){
				alert("Added Product successfully!");
			}
		});
	});
	
});
	

</script>
</html>