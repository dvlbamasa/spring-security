<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
   <%@ taglib prefix = "fn" uri = "http://java.sun.com/jsp/jstl/functions"%>
   <%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
   <head>
      <title><c:out value="${title}"></c:out></title> 
      <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
   </head>
   <body>
   	<h1><c:out value="${title}"></c:out></h1><br/>
	   	<h2>
	   		<c:out value="${subTitle}" />
	   	</h2>
	   	<c:if test="${orderType ne 'contacts'}"> 
		   	Pick Order:<br/> 
	        <form action="list" method="GET"> 
	           <select id="order_type" name="order_type"> 
	              <option value="GWA">GWA</option> 
	              <option value="Date Hired">Date Hired</option> 
	              <option value="Last Name">Last Name</option> 
	           </select> 
	           <button type="submit">Select</button><br/><br/>
	        </form>
	    </c:if>
        <c:if test="${prompt ne null}">
         	<h2 align="center"><font color=green><c:out value="${prompt}"></c:out></font></h2>
     	</c:if>
        <table id="t01">
             <tr>
                <th>Operation</th>
                <th>Id</th>
                <th>First Name</th>                         
                <th>Middle Name</th>
                <th>Last Name</th>
                <c:if test="${orderType ne 'contacts'}"> 
                	<th>Gender</th>
                	<th>Birthday</th>
                	<th>GWA</th>
                	<th>Currently Employed</th>
                	<th>Date Hired</th>
                	<th>Street Number</th>
                	<th>Barangay</th>
                	<th>Municipal</th>
                	<th>Zip Code</th>
                </c:if>	
                <th>Landline</th>
                <th>Mobile Number</th>
                <th>Email</th>
                <c:if test="${orderType ne 'contacts'}"> 
                	<th>Roles</th>
                </c:if>
			</tr>
			<c:forEach items="${requestScope.persons}" var="person">
				<tr>
					<c:if test="${orderType eq 'contacts'}"> 
						<c:if test="${person.contactInformation eq null}"> 
						    <td>
						       <a href="add?personId=${person.id}" >Add_Contact</a><br/> 
						    </td>
						</c:if>
						<c:if test="${person.contactInformation ne null}"> 
						    <td>
						       <a href="update?personId=${person.id}" >Update_Contact</a><br/>
						       <a href="delete?personId=${person.id}" >Delete_Contact</a><br/> 
						    </td>
						</c:if>
					</c:if>
					<c:if test="${orderType ne 'contacts'}"> 
						<td>
							<a href="update?personId=${person.id}">Update</a><br/> 
						    <a href="delete?personId=${person.id}">Delete</a> 
						</td>
					</c:if>
					<td><c:out value='${person.id}'/> </td>
					<td><c:out value="${person.name.firstName}"/></td>
					<td><c:out value="${person.name.middleName}"/></td>
					<td><c:out value="${person.name.lastName}"/></td>
					<c:if test="${orderType ne 'contacts'}"> 
						<td><c:out value="${person.gender}"/></td>
						<td><c:out value="${fn:substring(person.birthday,0,10)}"/></td>
						<td><c:out value="${person.gwa}"/></td>
						<td><c:out value="${person.currentlyEmployed}"/></td>
						<td><c:out value="${fn:substring(person.dateHired,0,10)}"/></td>
						<td><c:out value="${person.address.streetNo}"/></td>
						<td><c:out value="${person.address.barangay}"/></td>
						<td><c:out value="${person.address.municipality}"/></td>
						<td><c:out value="${person.address.zipCode}"/></td>
					</c:if>
					<td> 
						<c:if test="${person.contactInformation ne null}"> 
							<c:out value="${person.contactInformation.landline}"/>
						</c:if>
					</td>
					<td> 
						<c:if test="${person.contactInformation ne null}"> 
							<c:out value="${person.contactInformation.mobileNumber}"/>
						</c:if>
					</td>
					<td> 
						<c:if test="${person.contactInformation ne null}"> 
							<c:out value="${person.contactInformation.email}"/>
						</c:if>
					</td>
					<c:if test="${orderType ne 'contacts'}"> 
						<td>
							<c:forEach items="${person.roles}" var="role">
								<c:out value="${role.name }"/>
							</c:forEach>
						</td>
					</c:if>
	           	</tr>
	        </c:forEach>
	  	</table><br/><br/>
	  	<c:if test="${orderType ne 'contacts'}"> 
		   	<form action="add">
		   		<button type="submit">Add Person</button>
		   	</form><br/><br/>
		</c:if>
	   <a href="/springApp/">Back to Homepage</a>
   </body>
</html>