<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<!-- BootStrap -->
	<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/bootstrap.css">
	<script src="${pageContext.servletContext.contextPath}/javascript/bootstrap.js"></script>
	<script src="${pageContext.servletContext.contextPath}/javascript/bootstrap.min.js"></script>
	
	<!-- jQuery -->
	<script src="${pageContext.servletContext.contextPath}/javascript/jquery.min.js"></script>
	
	<!-- CSS Google Text -->
	<link href="https://fonts.googleapis.com/css?family=Roboto:400,900" rel="stylesheet" type="text/css">
	
	<!-- Custom CSS -->
	<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/index.css">
<body>
	<ul id="list">
		<li><a href="${pageContext.servletContext.contextPath}/index.jsp">Home</a></li>
		<li><a href="${pageContext.servletContext.contextPath}/invoicesPage.jsp">Create Invoice</a></li>
		<li><a href="${pageContext.servletContext.contextPath}/productPage.do">Products</a></li>
		<li><a href="${pageContext.servletContext.contextPath}/clientsPage.do">Clients</a></li>
		<li><a href="${pageContext.servletContext.contextPath}/reportPage.do">Generate Report</a></li>
	</ul>
</body>
</html>