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


<p><spring:message code="customer.welcome.complaintList" /></p>
<body>
<div>
<security:authorize access="hasRole('CUSTOMER')">
<div>
<display:table name="complaints" id="row" requestURI="complaint/customer/list.do" pagesize="5" class="displaytag">
	<%-- <display:column property="<spring:message code='complaint.ticker'/>" titleKey="complaint.ticker" sortable="false" /> --%>
	<display:column><a href="complaint/customer/edit.do?complaintId=${row.id}"><spring:message code="complaint.ticker" /></a></display:column>
	<display:column property="<spring:message code='complaint.moment'/>" titleKey="complaint.moment" sortable="false" format="{0,date,dd/MM/yyyy HH:mm}" />
	<display:column property="<spring:message code='complaint.description'/>" titleKey="complaint.description" sortable="false" />	
</display:table>
</div>
<div>
	<a href="complaint/customer/create.do"><input type="button" value="<spring:message code='button.create'></spring:message>"></a>
</div>
</security:authorize>
 </div>
</body>