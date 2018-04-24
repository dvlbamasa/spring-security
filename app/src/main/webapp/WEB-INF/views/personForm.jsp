<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
	<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
	<%@ taglib prefix="fn" uri = "http://java.sun.com/jsp/jstl/functions"%>
	<head>
      	<title><c:out value="${title}"></c:out></title> 
      	<style>
      		.error {
				color: #ff0000;
				font-weight: bold;
			}
		</style>
   	</head>
    <body>
    	<h1 align="center"><c:out value="${title}"></c:out></h1><br/>
    	<c:if test="${prompt ne null}">
         	<font color=red><c:out value="${prompt}"></c:out></font><br/><br/>
     	</c:if>
     	<spring:url value="save" var="action" />
     	<div align="center">
	    	Please Fill out the Person Information Form: <br/><br/>
	      	<form:form action="${action}" method="post" modelAttribute="person">
	      		<table>
	      			<form:hidden path="id"/>
	      			<tr><td colspan="2"><form:errors path="name.firstName" cssClass="error" /></td></tr>
	      			<tr>
	      				<td>First Name: </td>
	      				<td><form:input path="name.firstName" maxlength="20" required="required" /></td>
	      			</tr>
	      			<tr><td colspan="2"><form:errors path="name.middleName" cssClass="error" /></td></tr>
	      			<tr>
	      				<td>Middle Name: </td>
	      				<td><form:input path="name.middleName" maxlength="20" required="required"/></td>
	      			</tr>
	      			<tr><td colspan="2"><form:errors path="name.lastName" cssClass="error" /></td></tr>
	      			<tr>
	      				<td>Last Name: </td>
	      				<td><form:input path="name.lastName" maxlength="20" required="required"/></td>
	      			</tr>
	      			<tr><td colspan="2"><form:errors path="gender" cssClass="error" /></td></tr>
	      			<tr>
	      				<td>Gender: </td>
	      				<td>
	      					<form:radiobutton path="gender" value="MALE" label="Male" required="required"/>
	      					<form:radiobutton path="gender" value="FEMALE" label="Female" required="required"/>
	      				</td>
	      			</tr>
	      			<tr><td colspan="2"><form:errors path="birthday" cssClass="error" /></td></tr>
	      			<tr>
	      				<td>Birthday (YYYY-MM-DD): </td>
	      				<td><form:input path="birthday" value="${fn:substring(person.birthday,0,10)}" maxlength="10" required="required"/>
	      				</td>
	      			</tr>
	      			<tr><td colspan="2"><form:errors path="gwa" cssClass="error" /></td></tr>
	      			<tr>
	      				<td>GWA: </td>
	      				<td><form:input path="gwa"  min="1" max="3" step = "0.01" required="required"/></td>
	      			</tr>
	      			<tr><td colspan="2"><form:errors path="currentlyEmployed" cssClass="error" /></td></tr>
	      			<tr>
	      				<td>Currently Employed: </td>
	      				<td>
	      					<form:radiobutton path="currentlyEmployed" value="true" label="Yes" required="required"/>
	      					<form:radiobutton path="currentlyEmployed" value="false" label="No" required="required"/></td>
	      			</tr>
	      			<tr><td colspan="2"><form:errors path="dateHired" cssClass="error" /></td></tr>
	      			<tr>
	      				<td>Date Hired (YYYY-MM-DD): </td>
	      				<td><form:input path="dateHired" value="${fn:substring(person.dateHired,0,10)}" maxlength="10" required="required"/></td>
	      			</tr>
	      			<tr><td colspan="2"><form:errors path="address.streetNo" cssClass="error" /></td></tr>
	      			<tr>
	      				<td>Street Number: </td>
	      				<td><form:input path="address.streetNo" required="required"/></td>
	      			</tr>
	      			<tr><td colspan="2"><form:errors path="address.barangay" cssClass="error" /></td></tr>
	      			<tr>
	      				<td>Barangay: </td>
	      				<td><form:input path="address.barangay" maxlength="20" required="required"/></td>
	      			</tr>
	      			<tr><td colspan="2"><form:errors path="address.municipality" cssClass="error" /></td></tr>
	      			<tr>
	      				<td>Municipality: </td>
	      				<td><form:input path="address.municipality" maxlength="20" required="required"/></td>
	      			</tr>
	      			<tr><td colspan="2"><form:errors path="address.zipCode" cssClass="error" /></td></tr>
	      			<tr>
	      				<td>Zip Code: </td>
	      				<td><form:input path="address.zipCode" required="required" /></td>
	      			</tr>
	      			<tr><td colspan="2"><form:errors path="contactInformation.landline" cssClass="error" /></td></tr>
	      			<tr>
	      				<td>Landline: </td>
	      				<td><form:input path="contactInformation.landline" maxlength="20" required="required"/></td>
	      			</tr>
	      			<tr><td colspan="2"><form:errors path="contactInformation.mobileNumber" cssClass="error" /></td></tr>
	      			<tr>
	      				<td>Mobile Number: </td>
	      				<td><form:input path="contactInformation.mobileNumber" maxlength="20" required="required"/>
	      					</td>
	      			</tr>
	      			<tr><td colspan="2"><form:errors path="contactInformation.email" cssClass="error" /></td></tr>
	      			<tr>
	      				<td>Email: </td>
	      				<td><form:input path="contactInformation.email" maxlength="30" required="required"/></td>
	      			</tr>
	      		</table><br/>
	      		<div>
	      			Check the Roles you want to set to this person:<br/>
	      			<form:checkboxes path="roles" element="table" items="${roles}" itemValue="id" itemLabel="name"/>
	      		</div><br/>
	      		<input type="submit" value="${title}"/>
		    </form:form>
		</div>
    </body>
</html>