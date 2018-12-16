<%--
 * action-1.jsp
 *
 * Copyright (C) 2018 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 LISTING DE LAS FIXUPSTASKS (TODAS)
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<p><spring:message code="tutorial.listing.welcome"/></p>
<body>
<div>
	<security:authorize access="hasRole('HANDYWORKER')">
	<display:table name="tutorials" id="row" requestURI="tutorial/handyWorker/listing.do" pagesize="5" class="displaytag">
		
		<display:column property="<spring:message code= 'tutorial.title'/>" titleKey="fixUp.ticker" sortable="true"/>
		<display:column property="<spring:message code= 'tutorial.moment'/>" titleKey="tutorial.moment" sortable="true" format="{0,date,dd/MM/yyyy HH:mm}"/>
		<display:column property="<spring:message code= 'tutorial.summary'/>" titleKey="tutorial.summary" sortable="true"/>
		<display:column property="<spring:message code= 'tutorial.sponsor'/>" titleKey="tutorial.sponsor" sortable="false"/>
		<display:column> <a href="tutorial/handyWorker/edit.do?tutorialId=${row.id}"><spring:message code="tutorial.edit"/></a></display:column>
			
	</display:table>
	</security:authorize>
</div>
</body>