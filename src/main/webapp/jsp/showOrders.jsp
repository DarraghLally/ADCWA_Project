<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="/css/style.css">
<title>Show Orders</title>
</head>
<body>
	<h1>List of Orders</h1>
	<c:forEach items="${orders}" var="order">
		<h1>${order.oId}</h1>
		<table>
			<tr>
				<th>Quantity</th>
				<th>Order Date</th>
				<th>Customer ID</th>
				<th>Customer Name</th>
				<th>Product ID</th>
				<th>Product Description</th>
			</tr>

			<tr>
				<td>${order.qty}</td>
				<td>${order.orderDate}</td>
				<td>${order.cust.cId}</td>
				<td>${order.cust.cName}</td>
				<td>${order.prod.pId}</td>
				<td>${order.prod.pDesc}</td>
			</tr>
		</table>
	</c:forEach>
	<a href="index.html">Home</a>
	<a href="addProduct.html">Add Product</a>
	<a href="showProducts.html">List Products</a>
	<a href="showOrders.html">List Orders</a>
</body>
</html>