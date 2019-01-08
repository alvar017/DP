<%--
 * index.jsp
 *
 * Copyright (C) 2018 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<form:form class="formularioEdicion" method="POST"	modelAttribute=${ messageE} action="welcome/edit.do">
	<tr>				
		<td><spring:message code="newE" /> </td>
		<td><form:input path="messageE" /></td>
	</tr>

<form:form class="formularioEdicion" method="POST"	modelAttribute=messageS action="welcome/edit.do">
	<tr>
		<td><spring:message code="newS" /> </td>
		<td><form:input path="messageS" /></td>
	</tr>
	
<tr>
<td colspan="3"><input type="submit" value="save" /></td>
</tr>

</form:form>    
</form:form>    
