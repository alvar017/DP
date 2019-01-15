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

<form:form modelAttribute="note" action="note/handyWorker/create.do" method="POST">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="moment"/>
	<form:hidden path="customer"/>
	<form:hidden path="handyWorker"/>
	<form:hidden path="report"/>
	<form:hidden path="commentReferee"/>
	<form:hidden path="commentCustomer"/>

	<div>
		<form:label path="commentHandyWorker"><spring:message code="commentHandyWorker" /></form:label>
		<form:textarea path="commentHandyWorker" required="required"/>
		<form:errors path="commentHandyWorker"></form:errors>
		<br />
	</div>
		<div>
		<input type="submit" name="save" value="<spring:message code='button.edit' />" />
		<a href="complaint/handyWorker/list.do"><input type="button" value="<spring:message code='button.back'></spring:message>"></a>
	</div>
</form:form>