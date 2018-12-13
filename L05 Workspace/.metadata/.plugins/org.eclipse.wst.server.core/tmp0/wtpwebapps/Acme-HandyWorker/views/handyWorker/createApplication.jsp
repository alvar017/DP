<%--
 * action-1.jsp
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

<p><spring:message code="handyWorker.createApplication" /></p>
<body>
	<form:form action="application/handyWorker/create.do"
		modelAttribute="Application">
		
		<form:hidden path="id" />
		<form:hidden path="version" />

		<form:label path="moment">
			<spring:message code="application.moment" />:
		</form:label>
		<form:input path="moment" />
		<form:errors cssClass="error" path="moment" />
		
		<br />
		
		<form:label path="offered">
			<spring:message code="application.offered" />:
		</form:label>
		<form:input path="offered" />
		<form:errors cssClass="error" path="offered" />
		
		<br />
			
		<form:label path="comments">
			<spring:message code="application.comments" />:
		</form:label>
		<form:input path="comments" />
		<form:errors cssClass="error" path="comments" />
		
		<br />
		
		<form:label path="creditCard">
			<spring:message code="application.creditCard" />:
		</form:label>
		<form:select path="creditCard">
   		 	<form:options items="${creditCards}" />
		</form:select>
		<form:errors cssClass="error" path="creditCard" />
		
		<br />
		
		</form:form>
		
		<input type="button" name="save" value="<spring:message code="application.save" />" />
			
		<input type="button" name="cancel" onclick="location.href='application/handyWorker/listApplication.do" value="<spring:message code="application.cancel" />" />


</body>