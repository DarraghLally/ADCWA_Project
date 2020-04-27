<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="/css/style.css">
<title>Show Customers</title>
</head>
<body>
	<h1>List of Customers</h1>
	<table>

		<tr>
			<c:forEach items="${customers}" var="customer">
				<tr>
					<td>${customer.cId}</td>
					<td>${customer.cName}</td>
				</tr>
			</c:forEach>
		</tr>
	</table>
	<a href="index.html">Home</a>
	<a href="addProduct.html">Add Product</a>
	<a href="showProducts.html">List Products</a>
	<a href="showOrders.html">List Orders</a>
</body>
</html>