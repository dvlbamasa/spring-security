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
       <style>
            .error {
                color: #ff0000;
                font-weight: bold;
            }
            .success {
                color: green;
                font-weight: bold;
            }
        </style>
    </head>
    <body>
      <div align="center">
        <h2>Spring Security App</h2>
        <c:if test="${param.error != null}">
          <p class="error">Invalid username or password.</p>
        </c:if>
        <c:if test="${param.logout != null}">
          <p class="success">You have been logged out successfully.</p>
        </c:if>
        <h4>Login Form</h4>
        <form action='<spring:url value="/login"/>' method="POST">
          <table>
            <tr>
              <td>Username:</td>
              <td><input type="text" name="username" required="required"></td>
            </tr>
            <tr>
              <td>Password:</td>
              <td><input type="password" name="password" required="required"></td>
            </tr>
          </table>
          <input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
          <button type="submit">Login</button>
        </form>
      </div>
    	
    </body>
</html>