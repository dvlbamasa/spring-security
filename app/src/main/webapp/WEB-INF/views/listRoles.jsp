<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
   <head>
      <title><c:out value="${title}"></c:out></title> 
      <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
   </head>
   <body>
      <h1><c:out value="${title}"></c:out></h1><br/>
      <h2>List of Roles</h2>
      <c:if test="${prompt ne null}">
         <h2 align="center"><font color=green><c:out value="${prompt}"></c:out></font></h2>
      </c:if>
      <table id="t01">
         <tr>
            <th>Operation</th>
            <th>Id</th>
            <th>Role Name</th> 
            <th>Persons</th>  
         </tr>
         <c:forEach items="${requestScope.roles}" var="role">
            <tr>
               <td> 
                  <a href="update?roleId=${role.id}">Update</a><br/>
               </td>
               <td><c:out value="${role.id}"></c:out></td>
               <td><c:out value="${role.name}"></c:out></td>
               <td>
                  <c:forEach items="${role.persons}" var="person">
                     <c:out value="${person.name.firstName} "></c:out> <c:out value="${person.name.lastName}"></c:out>
                  </c:forEach>
               </td>
            </tr>
         </c:forEach>
      </table>
      <br/>
      <form action="add">
         <button type="submit">Add Role</button>
      </form><br/><br/>
      <a href="/springApp/">Back to Homepage</a>
   </body>
</html>