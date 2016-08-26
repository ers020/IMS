<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

	<!-- MODAL BOOTSTRAP -->
	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
  	<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<!-- MODAL BOOTSTRAP -->
	
	<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/bootstrap.css">
	<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/bootstrap.min.css">
	<script src="${pageContext.servletContext.contextPath}/javascript/bootstrap.js"></script>
	<script src="${pageContext.servletContext.contextPath}/javascript/bootstrap.min.js"></script>
	
	<!-- jQuery -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
	
	<!-- CSS Google Text -->
	<link href="https://fonts.googleapis.com/css?family=Roboto:400,900" rel="stylesheet" type="text/css">
	
	<!-- Custom CSS -->
	<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/index.css">

<script type="text/javascript">

function radioFunc()
{
	if($('#r1').is(':checked'))
	{
		document.getElementById("cVal").value = 1;		
	}
	
	else if($('#r2').is(':checked'))
	{
		document.getElementById("cVal").value = 2;
	}

	$.ajax({
		    type: "GET",
		    contentType: "application/json",
		    dataType: "json",
			url: "http://localhost:9001/IMS/getClientsByType.do?var=" + $("#cVal").val(),
			success: function(resp)
			{
				var comboBox = document.createElement("select");
				var def = document.createElement("option");
				def.value = -1;
				def.text = "--Select--";
				comboBox.appendChild(def);
				
				//alert(resp);
				
				var blah = [];
				blah = resp;
								
				$.each(resp, function(c, blah){
					var option = document.createElement("option");
					option.value = blah.id;
					option.text = blah;
					comboBox.appendChild(option);
					});
				
				$('#dropDownClient').append(comboBox);
				
				document.getElementById("r1").disabled = true;
				document.getElementById("r2").disabled = true;
				//document.getElementById("prodBox").disabled = false;
			}
		});
}

function doobie()
{
	$.ajax({
		    type: "GET",
		    contentType: "application/json",
		    dataType: "json",
			url: "http://localhost:9001/IMS/getProds.do",
			success: function(resp)
			{
				var prodComboBox = document.createElement("select");
				var def = document.createElement("option");
				def.value = -1;
				def.text = "--Select--";
				prodComboBox.appendChild(def);
				
				var blah = [];
				blah = resp;
								
				$.each(resp, function(c, blah){
					var option = document.createElement("option");
					option.value = blah;
					option.text = blah;
					prodComboBox.appendChild(option);
					});
				
				$('#prodBox').append(prodComboBox);
				var newBox = document.getElementById("dropDownClient").firstChild;
				
				newBox.disabled = true;
				
				document.getElementById("dropDownClient").replaceChild(newBox, document.getElementById("dropDownClient").firstChild);
			}
		});
}/* ); */

function qntyUnlock() 
{
	//document.getElementById("qnty").disabled = false;
	
	var e = document.getElementById("prodBox").firstChild;
    var strOption = e.options[e.selectedIndex].value;
    
    //alert(strOption);
    
    $("#pVal").value = strOption;
	
	if(strOption == "--Select--")
	{
		document.getElementById("qnty").disabled = true;
	}	
	
	else 
	{
		document.getElementById("qnty").disabled = false;
	}
	
	$.ajax({
		    type: "GET",
		    contentType: "application/json",
		    dataType: "json",
			url: "http://localhost:9001/IMS/getProdPrice.do?var=" + strOption,
			success: function(resp)
			{				
				//alert(resp);
				
				document.getElementById("prodCost").value = resp;
				
				/* var blah = [];
				blah = resp;
								
				$.each(resp, function(c, blah){
					document.getElementById("cost").value = blah;
					}); */

				//alert("success");
			}
		});
			
	setTimeout(changeSubTotal(), 1000);
}

function changeTotal()
{
	var tagId = "subtotal";
	var i = 0;
	var tot = 0;
	
	//alert(tagId);
	
	while(document.getElementById(tagId) != null)
	{
		tot += parseFloat(document.getElementById(tagId).value);
		
		tot = tot + parseFloat(tot * parseFloat(7.5 / 100));
		
		i++;
		tagId += i;
	}
		
	//alert(tot);
	
	document.getElementById("total").innerHTML = tot;
	
	//$("#total").value = tot;
}

function addProduct()
{
	var c = document.getElementById("dropDownClient").firstChild;
    var cOption = c.options[c.selectedIndex].value;
	var p = document.getElementById("prodBox").firstChild;
    var pOption = p.options[p.selectedIndex].value;
    
    alert(p);
    alert(c);
    
    var table = document.getElementById("invoiceTable");
    var row = table.insertRow(1);
    var cell0 = row.insertCell(0);
    var cell1 = row.insertCell(1);
    var cell2 = row.insertCell(2);
    var cell3 = row.insertCell(3);
    var cell4 = row.insertCell(4);
    
    cell0.innerHTML = cOption;
    cell1.innerHTML = pOption;
    cell2.innerHTML = $("#prodCost").val();
    cell3.innerHTML = $("#qnty").val();
    cell4.innerHTML = $("#subtotal").val();
    
	/* var newRow = "<tr><td>" + cOption + "</td><td>" + pOption + "</td><td>" + $("#prodCost").val() + "</td><td>" + $("#qnty").val() + "</td><td>" + $("#subtotal").val() + "</td></tr>"
	document.getElementById("invoiceTable").appendChild(newRow); */
}

function submit()
{
	var table = document.getElementById("invoiceTable");
	var invoice = [][];
	
	for (var i = 0, row; row = table.rows[i]; i++)
	{
		var client = row.cells[0].value;
		var product = row.cells[0].value;
		var cost = row.cells[0].value;
		var quantity = row.cells[0].value;
		var subtotal = row.cells[0].value;
	}
}

function changeSubTotal() 
{
	var quantity = $("#qnty").val();
	var price = $("#prodCost").val();
	
	var sub = quantity * price;
	
	//alert("Q: " + quantity + ", P: " + price + ", S: " + sub);
	
	document.getElementById("subtotal").value = sub;
	
	/* jQuery.ajax
	({
		// contentType application/json
		headers: 
		{          
   			"Content-Type": "application/json"
   		},
		url: "http://localhost:9001/IMS/getProdsByClient.do",
		method: "POST",
		data: JSON.stringify
		({
			quantity : quantity
		}),
		success: function(resp)
		{
			//alert("Edited Client successfully!");
			$("#subt").val(resp);
		}
	}); */
	
	changeTotal();
}
</script> 

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Generate Invoice</title>
<!-- BootStrap -->
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
	<div id="title">
		<h2>Invoice Creation</h2>
	</div>
	<br />
	<div id="radioBtn" class="radio">
		<label><input type="radio" onclick="radioFunc()" id="r1" name="invChoice" value="Incoming"/>Supplier</label><br/>
		<label><input type="radio" onclick="radioFunc()" id="r2" name="invChoice" value="Outgoing"/>Retailer</label><br/>
	</div>
	<br />
	<br />
	<span id="dropDownClient" onchange="javascript:doobie()"></span>
	<br />
		<div id="tableHolder">
		<table id="invoiceTable">
			<tr>
		    	<th>Product</th>
		    	<th>Price</th>
		    	<th>Quantity</th>
		    	<th>Sub-total</th>
		    	<th>Option</th>
		  	</tr>
		  	<tr id="dynamics">
				<td>
					<span id="prodBox" class="form-control" onchange="javascript:qntyUnlock()"></span>
				</td>
		  		<td>
		  			<input type="number" id="prodCost" class="form-control" disabled="disabled" name="prodCost" value="${p.cost}">
		  		</td>  
		  		<td>
		  	 		<input type="number" id="qnty" class="form-control" disabled="disabled" name="quantity" value="0" onkeydown="javascript:changeSubTotal()" onchange="javascript:changeSubTotal()"> 
		  		</td>
		  	 	<td>
		  	 		 <input type="number" class="form-control" disabled="disabled" id="subtotal" name="subtotal"> 
		  	 	</td>
		  	 	<td>
		  	 		<input type="button" value="Add!" onclick="javascript:addProduct()"/>
		  	 	</td>
		  	 </tr>
		  	
		  	<tr></tr>
		 
		  <tr id="blanktr" class="blanktr"></tr>
		 
		  	
		</table>
		</div>
		<br />
		<br />
		<br />
		<br />
		
		<br />
		<br />
	</div>
	<div class="container footer .col-xs-12 .col-sm-6 .col-lg-8">
		<jsp:include page="/footerPage/footer.jsp"></jsp:include>
		</div>
	<br />
	</div>
	
	<br />
	<button  onclick="javascript:changeTotal()">Change Total</button>
	<div>
	<input id="cVal" name="clientType" type="hidden" value="1"/>
	<input id="pVal" name="clientType" type="hidden" value="0"/>
	</div>
</body>
</html>