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
	      		<td id="eId"></td>
	      	</tr>
	      	<tr style="display: none">
	      		<td>AddId:</td>
	      		<td><input type="text" id="eAddId"></td>
	      	</tr>
	        <tr>
	        	<td>Client:</td>
	        	<td><input required id="eName" type="text" class="form-control" name="clientName"></td>
	        	
	        </tr>
	        <tr>
	        	<td>Email:</td>
	        	<td><input required id="eEmail" type="text" class="form-control" name="email"></td>
	        </tr>
	        <tr>
	        	<td>Contact Name:</td>
	        	<td><input required id="eContactName" type="text" class="form-control" name="contactName"></td>
	        </tr>
	        <tr>
	        	<td>Phone:</td>
	        	<td><input required id="ePhone" type="text" class="form-control" name="Phone"></td>
	        </tr>
	        <tr>
	        	<td>Fax:</td>
	        	<td><input required id="eFax" type="text" class="form-control" name="fax"></td>
	        </tr>
	        <tr>
	        	<td>Address Line 1:</td>
	        	<td><input required id="eAdLine1" type="text" class="form-control" name="adLine1"></td>
	        </tr>
	        <tr>
	        	<td>Address Line 2:</td>
	        	<td><input required id="eAdLine2" type="text" class="form-control" name="adLine2"></td>
	        </tr>
	        <tr>
	        	<td>City:</td>
	        	<td><input required id="eCity" type="text" class="form-control" name="city"></td>
	        </tr>
	        <tr>
	        	<td>State:</td>
	        	<td>
	        		<select name="eState" id="eState" class="form-control" id="state">
	        			<option selected >Select A State</option>
	        			<c:forEach var="s" items="${states}">
	        			<option id="${s.id}" value="${s.id}"><c:out value="${s.name}"></c:out></option>
	        			</c:forEach>
	        		</select>
	        	</td>
	        </tr>
	        <tr>
	        	<td>Zip:</td>
	        	<td><input required id="eZip" type="text" class="form-control" name="zip"></td>
	        </tr>
	        <tr>
	        	<td>Type:</td>
	        	<td>
					<select name="eType" id="eType" class="form-control">
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

function getProduct(pName)
	{
		var productName = pName;
		var eProduct;
		
		$.get("http://localhost:9001/IMS/clientInfo.do?productName=" + productName, function(response){
			eProduct = response;
			
			$("#eId").text(eProuct.id);
		/*	$("#eAddId").val(eClient.address.id);
			$("#eName").val(eClient.name);
			$("#eEmail").val(eClient.email);
			$("#eContactName").val(eClient.pocName);
			$("#ePhone").val(eClient.phone);
			$("#eFax").val(eClient.fax);
			$("#eAdLine1").val(eClient.address.line1);
			$("#eAdLine2").val(eClient.address.line2);
			$("#eCity").val(eClient.address.city);
			$("#eState").val(eClient.address.state.id);
			$("#eZip").val(eClient.address.zip);
			$("#eType").val(eClient.clientType.id);*/
			
		});

	}

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
			var catDesc = jQuery("#catDesc").val();
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
	jQuery(document).ready(function(){
		jQuery("#editProduct").click(function(){
			var clientId = jQuery("#eId").text();
			var addressId = jQuery("#eAddId").val();
			var clientName = jQuery("#eName").val();
			var email = jQuery("#eEmail").val();
			var contactName = jQuery("#eContactName").val();
			var phone = jQuery("#ePhone").val();
			var fax = jQuery("#eFax").val();
			var adLine1 = jQuery("#eAdLine1").val();
			var adLine2 = jQuery("#eAdLine2").val();
			var city = jQuery("#eCity").val();
			var stateId = jQuery("#eState").val();
			var zip = jQuery("#eZip").val();
			var typeId = jQuery("#eType").val();
			
			jQuery.ajax({
			// contentType application/json
			headers: {          
    			"Content-Type": "application/json"
    		},
			url: "http://localhost:9001/IMS/editClient.do",
			method: "POST",
			data: JSON.stringify({
				strId : clientId, strAddId : addressId, name : clientName, email : email, 
				pocName : contactName, phone : phone,
				fax : fax, addLine1 : adLine1, addLine2 : adLine2,
				stateId : stateId, city : city, zip : zip, clientTypeId : typeId
			}),
			success: function(){
				alert("Edited Client successfully!");
			}
		});
	});
	
});

	jQuery(document).ready(function(){
		jQuery("#deleteProduct").click(function(){
			var clientName = jQuery("#eName").val();
			jQuery.ajax({
			// contentType application/json
			headers: {          
    			"Content-Type": "application/json"
    		},
			url: "http://localhost:9001/IMS/deleteProduct.do",
			method: "POST",
			data: JSON.stringify({
				delName : clientName
			}),
			success: function(){
				alert("Deleted Client successfully!");
			}
		});
	});
	
});

</script>
</html>