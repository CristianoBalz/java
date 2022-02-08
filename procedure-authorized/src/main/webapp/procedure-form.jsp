<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Procedure Form</title>
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
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
				<c:if test="${not empty procedure.id}">
					<form action="procedure?action=update" method="post">
				</c:if>
				<c:if test="${empty procedure.id}">
					<form action="procedure?action=insert" method="post">
				</c:if>
				<caption>
					<h2>
						<c:if test="${not empty procedure.id}">
            			Edit Procedure
            		</c:if>
						<c:if test="${empty procedure.id}">
            			Add New Procedure
            		</c:if>
					</h2>
				</caption>		
				
				<c:if test="${procedure.id != null}">
					<input type="hidden" name="id" value="<c:out value='${procedure.id}' />" />
				</c:if>					
				
				<fieldset class="form-group">
					<label>Number</label> <input type="text"
						value="<c:out value='${procedure.number}' />" class="form-control"
						name="number" required="required">
				</fieldset>				

				<fieldset class="form-group">
					<label>Age</label> <input type="text"
						value="<c:out value='${procedure.age}' />" class="form-control"
						name="age" required="required">
				</fieldset>

				<fieldset class="form-group">
					<label>Sex</label> <input type="text"
						value="<c:out value='${procedure.sex}' />" class="form-control"
						name="sex" required="required">
				</fieldset>

				<fieldset class="form-group">
					<label>Permitted</label> <input type="text"
						value="<c:out value='${procedure.permitted}' />"
						class="form-control" name="permitted" required="required">
				</fieldset>
				<div>
					<c:if test="${not empty message}">
						<c:out value='${message}' /> 
					</c:if>
				</div>
				
				<button type="submit" class="btn btn-success">Save</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>