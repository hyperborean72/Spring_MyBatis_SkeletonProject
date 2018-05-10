<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>List of the countries</title>

	<style>
		tr:first-child{
			font-weight: bold;
			background-color: #C6C9C4;
		}
	</style>

</head>

<body>
	<h2>All Countries</h2>
	<table>
		<tr>
			<td>NAME</td><td>CAPITAL</td><td>POPULATION</td><td></td><td></td>
		</tr>
		<c:forEach items="${countries}" var="country">
			<tr>
			<td>${country.name}</td>
			<td>${country.capital}</td>
			<td>${country.population}</td>
			<td><a href="<c:url value='/updateCountry/${country.id}' />">Edit</a></td>
			<td><a href="<c:url value='/deleteCountry/${country.id}' />">Delete</a></td>
			</tr>
		</c:forEach>
	</table>
	<a href="<c:url value='/addCountry' />">Add New Country</a>
	<br/>
    <a href="<c:url value='/listAll' />">List Countries</a>
</body>
</html>