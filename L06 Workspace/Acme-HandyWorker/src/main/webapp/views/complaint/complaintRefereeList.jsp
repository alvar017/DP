<%--
 * complaintList.jsp
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
<security:authorize access="hasRole('REFEREE')">
<div>
	<h2><spring:message code="complaint.referee.list"></spring:message></h2>
	<display:table name="complaints" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag">
		<display:column property="ticker" titleKey="complaint.ticker"></display:column>
		<display:column property="moment" titleKey="complaint.moment" format="{0,date,dd/MM/yyyy HH:mm}"></display:column>
		<display:column property="description" titleKey="complaint.description"></display:column>
		<display:column property="fixUp.ticker" titleKey="complaint.fixUp"></display:column>
		<display:column>
			<a href="complaint/referee/add.do?complaintId=${row.id}"><spring:message code="button.add" /></a>
		</display:column>
	</display:table>
	<br />
 </div>
 <div>
 	<h2><spring:message code="complaint.referee.myList"></spring:message></h2>
	<display:table name="myComplaints" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag">
		<display:column titleKey="customer.ticker"> 
			<a href="complaint/referee/show.do?complaintId=${row.id}">${row.ticker}</a>
		</display:column>
		<display:column property="moment" titleKey="complaint.moment" format="{0,date,dd/MM/yyyy HH:mm}"></display:column>
		<display:column property="description" titleKey="complaint.description"></display:column>
		<display:column property="fixUp.ticker" titleKey="complaint.fixUp"></display:column>
	</display:table>
	<br />
</div>
</security:authorize>
 </div>
 			<form method="get" action=" ">
				<button type="submit">
					<spring:message code="button.back" />
				</button>
			</form>
</body>