<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
		<title>No Persons</title>
	</head>
	<body>
		<div align="center">
			<h3>The database is empty.</h3>
			<h4>Add a new Person first:</h4>
			<form action="/springApp/person/add">
				<button type="submit">Add Person</button>
			</form><br/><br/>
			<a href="/springApp/">Back to Homepage</a>
		</div>
	</body>
</html>