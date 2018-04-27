<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
   <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
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
            <sec:authorize access="hasRole('ADMIN')">
               <th>Operation</th>
            </sec:authorize>
            <th>Id</th>
            <th>Role Name</th> 
            <th>Persons</th>  
         </tr>
         <c:forEach items="${requestScope.roles}" var="role">
            <tr>
               <sec:authorize access="hasRole('ADMIN')">
                  <td> 
                     <a href="update?roleId=${role.id}">Update</a><br/>
                  </td>
               </sec:authorize>
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
      <sec:authorize access="hasRole('ADMIN')">
         <form action="add">
            <button type="submit">Add Role</button>
         </form><br/><br/>
      </sec:authorize>
      <a href="/springApp2/">Back to Homepage</a>
   </body>
</html>