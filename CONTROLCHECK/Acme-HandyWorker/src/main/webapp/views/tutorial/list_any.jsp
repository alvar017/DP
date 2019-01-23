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
	<display:table name="tutorials" id="row" requestURI="${requesURI }" pagesize="5" class="displaytag">
		<display:column titleKey="tutorial.title"> <a href="tutorial/show.do?tutorialId=${row.id}">${row.title }</a></display:column>
		<display:column property="moment" titleKey="tutorial.moment" format="{0,date,dd/MM/yyyy HH:mm}"/>
		<display:column property="summary" titleKey="tutorial.summary"/>
		<display:column> <a href="handyWorker/showG.do?handyWorkerId=${row.handyWorker.id}">"${row.handyWorker.name }"</a></display:column>
		
			
	</display:table>
	
</div>
</body>