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
	<c:forEach items="${customers}" var="customer">
		<h1>${customer.cId} ${customer.cName}</h1>
		<table>
			<tr>
				<th>Order ID</th>
				<th>Quantity</th>
				<th>Product ID</th>
				<th>Description</th>
			</tr>
			<c:forEach items="${customer.orders}" var="order">
				<tr>
					<td>${order.oId}</td>
					<td>${order.qty}</td>
					<td>${order.prod.pId}</td>
					<td>${order.prod.pDesc}</td>
				</tr>
			</c:forEach>
		</table>
	</c:forEach>
		<a href="index.html">Home</a>
		<a href="addProduct.html">Add Product</a>
		<a href="showProducts.html">List Products</a>
		<a href="showOrders.html">List Orders</a>
		<a href="/logout">Logout</a>
</body>
</html>