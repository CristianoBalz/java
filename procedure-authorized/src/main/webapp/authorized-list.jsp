<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Authorized List</title>
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

	<div class="row">
		<div class="container">
			<h3 class="text-center">List of Authorizations</h3>
			<hr>
			<div class="container text-left">

				<a href="authorized?action=new" class="btn btn-success">Add New
					Authorized</a>
			</div>
			<br>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>Id</th>
						<th>Authorized Name</th>
						<th>Number</th>
						<th>Age</th>
						<th>Sex</th>
						<th>Insert</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="authorized" items="${listAuthorized}">
						<tr>
							<td><c:out value="${authorized.id}" /></td>
							<td><c:out value="${authorized.authorizedName}" /></td>
							<td><c:out value="${authorized.number}" /></td>
							<td><c:out value="${authorized.age}" /></td>
							<td><c:out value="${authorized.sex}" /></td>
							<td><c:out value="${authorized.insertDate}" /></td>
							<td><a
								href="authorized?action=delete&id=<c:out value='${authorized.id}' />">Delete</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>