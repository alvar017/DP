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
		<spring:message code="complaint.details"></spring:message>
		<display:table name="complaint" id="row" requestURI="${requestURI}" class="displaytag">
			<display:column property="moment" titleKey="complaint.moment" format="{0,date,dd/MM/yyyy HH:mm}"></display:column>
			<display:column property="fixUp.ticker" titleKey="complaint.fixUp"></display:column>	
		</display:table>
		<br />
	</div>
	<div>
		<spring:message code="complaint.info"></spring:message>
		<display:table name="complaint" id="row" requestURI="${requestURI}" class="displaytag" >
			<display:column property="description" titleKey="complaint.description"></display:column>
			<display:column property="attachment" titleKey="complaint.attachment"></display:column>	
		</display:table>
		<br />
	</div>
	<div>
		<spring:message code="complaint.reports"></spring:message>
		<display:table name="complaint.reports" id="row" requestURI="${requestURI}" class="displaytag" pagesize="5"> 
			<display:column property="moment" titleKey="complaint.moment"></display:column><!-- complaint.X spring messages are used bcs it would say the same that report.X -->
			<display:column property="description" titleKey="complaint.description"></display:column>
			<display:column>
				<a href="report/referee/show.do?reportId=${row.id}"><spring:message code="details" /></a>
			</display:column>
		</display:table>
		<br />
	</div>
	<div>
		<a href="report/referee/create.do?complaintId=${complaint.id}"><input type="button" value="<spring:message code='button.create'></spring:message>"></a>
		<br />	
	</div>
</security:authorize>
 </div>
</body>