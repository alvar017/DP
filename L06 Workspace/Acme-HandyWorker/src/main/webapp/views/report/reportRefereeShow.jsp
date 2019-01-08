<%--
 * complaintShow.jsp
 *
 * Copyright (C) 2018 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 	TODO: check labels
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
<security:authorize access="hasRole('REFEREE')">
	<div>
		<a href="complaint/referee/list.do"><input type="button" value="<spring:message code='button.back'></spring:message>"></a>
		<br />
	</div>
	<div>
		<h2><spring:message code="report.details"></spring:message></h2>
		<display:table name="report" id="row" requestURI="${requestURI}" class="displaytag">
			<display:column property="moment" titleKey="report.moment" format="{0,date,dd/MM/yyyy HH:mm}"></display:column>
			<display:column property="complaint.ticker" titleKey="report.complaint.ticker"></display:column>	
		</display:table>
		<br />
	</div>
	<div>
		<h2><spring:message code="report.info"></spring:message></h2>
		<display:table name="report" id="row" requestURI="${requestURI}" class="displaytag">
			<display:column property="description" titleKey="report.description"></display:column>
			<display:column property="attachment" titleKey="report.attachment"></display:column>	
		</display:table>
		<br />
	</div>
	<div>
		<h2><spring:message code="report.notes"></spring:message></h2>
		<display:table name="report.notes" id="row" requestURI="${requestURI}" class="displaytag" pagesize="5"> 
			<display:column property="moment" titleKey="report.moment" format="{0,date,dd/MM/yyyy HH:mm}"></display:column>
			<display:column property="handyWorker.name" titleKey="report.note.hw.name"></display:column>
			<display:column property="report.complaint.fixUp.customer.name" titleKey="report.complaint.customer.name"></display:column>
			<display:column>
				<a href="note/referee/show.do?noteId=${row.id}"><spring:message code="details" /></a>
			</display:column>
		</display:table>
	</div>
	<div>
		<a href="note/referee/create.do?id=${report.id}"><input type="button" value="<spring:message code='button.create'></spring:message>"></a>
	</div>
</security:authorize>
 </div>
</body>