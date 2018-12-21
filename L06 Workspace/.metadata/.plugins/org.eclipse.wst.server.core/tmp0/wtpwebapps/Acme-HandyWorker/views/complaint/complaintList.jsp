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

<%-- format="{0,date,dd/MM/yyyy HH:mm}" --%>

<p><spring:message code="customer.welcome.complaintList" /></p>
<body>
<div>
<security:authorize access="hasRole('CUSTOMER')">
<div>
<display:table name="complaints" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag">
	<display:column titleKey="complaint.ticker">
		<a href="complaint/customer/show.do?complaintId=${row.id}">${row.ticker}</a>
	</display:column>
	<display:column property="moment" titleKey="complaint.moment"  format="{0,date,dd/MM/yyyy HH:mm}" />
	<display:column property="description" titleKey="complaint.description" />	
</display:table>
</div>
<div>
	<a href="complaint/customer/create.do"><input type="button" value="<spring:message code='button.create'/>"></a>
</div>
</security:authorize>
 </div>
</body>