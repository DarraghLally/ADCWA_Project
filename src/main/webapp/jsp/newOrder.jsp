<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>New Order</title>
</head>
<body>
	<form:form modelAttribute="order">
		<table>
			<tr>
				<td>Customer:</td>
				<td><form:select path="cust" items="${customerNames}"/></td>
				<td><form:errors path="cust.cId"></form:errors></td>
			</tr>
			<tr>
				<td>Product:</td>
				<td><form:select path="prod" items="${productNames}"/></td>
				<td><form:errors path="prod.pId"></form:errors></td>
			</tr>
			<tr>
				<td>Quantity:</td>
				<td><form:input path="qty"></form:input></td>
				<td><form:errors path="qty"></form:errors></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Add" /></td>
			</tr>
		</table>
	</form:form>
	<a href="index.html">Home</a>
	<a href="showProducts.html">List Products</a>
	<a href="showOrders.html">List Orders</a>
	
</body>
</html>