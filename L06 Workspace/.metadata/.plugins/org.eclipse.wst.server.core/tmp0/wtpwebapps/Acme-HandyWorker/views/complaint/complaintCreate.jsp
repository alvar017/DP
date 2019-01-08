<%--
 * complaintEdit.jsp
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

<form:form modelAttribute="complaint" action="complaint/customer/create.do">
	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="referee" />
	<div>
		<a href="complaint/customer/list.do"><input type="button" value="<spring:message code='button.cancel'></spring:message>"></a>
		<input type="submit" name="save" value="<spring:message code='complaint.save' />" />
	</div>
	<div>
		<form:label path="date"><spring:message code="complaint.moment" /></form:label>
		<form:input path="moment" placeholder = "dd/MM/yyyy"/>
		<form:select id="fixUps" path="fixUp">
			<form:options items="${fixUps}" itemLabel="ticker" itemValue="id"/>
			<form:option value="0" label="----"></form:option>
		</form:select>
	</div>
	<div>
		<form:label path="text"><spring:message code="complaint.description" /></form:label>
		<form:textarea path="description"/>
		<form:label path="text"><spring:message code="complaint.attachment" /></form:label>
		<form:textarea path="attachment"/>
	</div>
</form:form>