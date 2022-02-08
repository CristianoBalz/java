<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Procedure List</title>
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
				<a href="/procedure-authorized" class="navbar-brand"> Procedure Authorized Management
					App </a>
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
			<h3 class="text-center">List of Procedures</h3>
			<hr>
			<div class="container text-left">

				<a href="procedure?action=new" class="btn btn-success">Add New
					Procedure</a>
			</div>
			<br>			
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>Id</th>
						<th>Number</th>
						<th>Age</th>
						<th>Sex</th>
						<th>Permitted</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="procedure" items="${listProcedure}">
						<tr>
							<td><c:out value="${procedure.id}" /></td>
							<td><c:out value="${procedure.number}" /></td>
							<td><c:out value="${procedure.age}" /></td>
							<td><c:out value="${procedure.sex}" /></td>
							<td><c:out value="${procedure.permitted}" /></td>
							<td><a
								href="procedure?action=edit&id=<c:out value='${procedure.id}' />">Edit</a>
								&nbsp;&nbsp;&nbsp;&nbsp; <a
								href="procedure?action=delete&id=<c:out value='${procedure.id}' />">Delete</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>