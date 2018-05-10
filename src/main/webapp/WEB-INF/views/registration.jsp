<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>New Country Registration Form</title>

<style>
	.error {
		color: #ff0000;
	}
</style>
</head>

<body>

	<h2>Country Registration Form</h2>

	<form:form method="POST" modelAttribute="newcountry">
	    <%-- exists in case of editing --%>
		<form:input type="hidden" path="id" id="id"/>
		<table>
			<tr>
				<td><label for="name">Name: </label> </td>
				<td><form:input path="name" id="name"/></td>
				<td><form:errors path="name" cssClass="error"/></td>
		    </tr>

			<tr>
				<td><label for="capital">Capital: </label> </td>
				<td><form:input path="capital" id="capital"/></td>
				<td><form:errors path="capital" cssClass="error"/></td>
		    </tr>

			<tr>
				<td><label for="population">Population: </label> </td>
				<td><form:input path="population" id="population"/></td>
				<td><form:errors path="population" cssClass="error"/></td>
		    </tr>

			<tr>
				<td colspan="3">
					<c:choose>
					    <%-- tests value of model attribute edit --%>
						<c:when test="${edit}">
							<input type="submit" value="Update"/>
						</c:when>
						<c:otherwise>
							<input type="submit" value="Register"/>
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
		</table>
	</form:form>
	<br/>
	<br/>
	Return to <a href="<c:url value='/listAll' />">List of countries</a>
</body>
</html>