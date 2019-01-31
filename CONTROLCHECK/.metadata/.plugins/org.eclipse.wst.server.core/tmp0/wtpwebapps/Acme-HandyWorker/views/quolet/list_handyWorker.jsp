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

<body>
<div>
<security:authorize access="hasRole('HANDYWORKER')">
<display:table name="quolets" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag">
	<display:column titleKey="Show" >
		<a href="quolet/handyWorker/show.do?quoletId=${row.id}"><spring:message code="Show" /></a>
	</display:column>
	<display:column property="ticker" titleKey="ticker"></display:column>
</display:table>
</security:authorize>
</div>

<form method="get" action=" ">
	<button type="submit">
		<spring:message code="button.back" />
	</button>
</form>

</body>