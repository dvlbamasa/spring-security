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
        <h2>Spring Security App</h2>
        <h4>Login Form</h4>
        <form action='<spring:url value="/login"/>' method="post">
          <table>
            <tr>
              <td>Username</td>
              <td><input type="text" name="username"></td>
            </tr>
            <tr>
              <td>Password</td>
              <td><input type="password" name="password"></td>
            </tr>
          </table>
          <button type="submit">Login</button>
        </form>
      </div>
    	
    </body>
</html>