<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false" isErrorPage="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Error</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>

	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: tomato">
			<div>
				<a href="/procedure-authorized" class="navbar-brand">
					Procedure Authorized Management App </a>
			</div>

			<ul class="navbar-nav">
				<li><a href="procedure?action=list" class="nav-link">Procedures</a></li>
				<li><a href="authorized?action=list" class="nav-link">Authorization</a></li>
			</ul>
		</nav>
	</header>
	<br>
	<br>
	<center>
		<h2>Error</h2>
		<h3><%=exception.getMessage() %><br/> </h3>
	</center>
</body>



</html>