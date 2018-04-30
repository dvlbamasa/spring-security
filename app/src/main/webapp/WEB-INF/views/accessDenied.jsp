<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
		<title>Access Denied</title>
		<style>
            .error {
                color: #ff0000;
                font-weight: bold;
            }
        </style>
	</head>
	<body>
		<div style="float:right;">
          <a href='<spring:url value="/logout"/>'>Logout</a>
        </div>  
		<div align="center">
			<h1 class="error">You are not authorized to access this page!</h1><br/><br/>
			<a href="/springApp2/">Back to Homepage</a>
		</div>
	</body>
</html>