<%--
 * action-2.jsp
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
	<jstl:if test="${requestScope.sponsorships.size()}>0" ><jstl:if test="${requestScope.randomSponsorship.banner}"> </jstl:if></jstl:if>
	
</div>
<div>
	<security:authorize access="hasRole('HANDYWORKER')">
	<div>
	<p><spring:message code="phase.show" /></p>
	<display:table name="phase" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag">
		<jstl:set var="tutorialId" value="${row.id}"/>
		<display:column property="title" titleKey="phase.title"/>
		<display:column property="startDate" titleKey="phase.startDate" format="{0,date,dd/MM/yyyy HH:mm}"/>
		<display:column property="endDate" titleKey="phase.endDate" format="{0,date,dd/MM/yyyy HH:mm}"/>
		<display:column property="description" titleKey="phase.descripton"/>
	</display:table>
	</div>	
	
	
	<div>
		<a href="application/handyWorker/list.do" ><input type="button" value="<spring:message code='cancel'></spring:message>"></a>
	</div>
	</security:authorize>
</div>
</body>