<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
    <head>
    	<title>Spring Security App</title>
    	 <br/><br/>
    </head>
    <body>
      <div align="center">
        <h2>Welcome to the Simple CRUD Application</h2>
        <h2>using Spring MVC with Spring Security!</h2>
        <h1><c:out value="${password}"></c:out></h1>
        Choose the Entity you want to modify:<br/>
          <a href="person/list">1. Person</a><br/>
          <a href="role/list">2. Role</a><br/>
          <a href="contact/list">3. Contact Information</a><br/>
      </div>
    	
    </body>
</html>