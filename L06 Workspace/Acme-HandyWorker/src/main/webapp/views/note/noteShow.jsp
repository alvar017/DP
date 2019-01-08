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
<security:authorize access="hasRole('CUSTOMER')">
	<div>
		<a href="complaint/customer/list.do"><input type="button" value="<spring:message code='button.back'></spring:message>"></a>
		<br />
	</div>
	<div>
		<spring:message code="details"></spring:message>
		<display:table name="note" id="row" requestURI="${requestURI}" class="displaytag">
			<display:column property="moment" titleKey="moment" format="{0,date,dd/MM/yyyy HH:mm}"></display:column>
			<display:column property="report.description" titleKey="description"></display:column>	
		</display:table>
		<br />
	</div>
	<div>
		<spring:message code="note.info"></spring:message>
		 <display:table name="note" id="row" requestURI="${requestURI}" class="displaytag">
			<display:column property="handyWorker.name" titleKey="handyWorker"></display:column>
			<display:column property="report.complaint.referee.name" titleKey="referee"></display:column>	
		</display:table>
		<br />
	</div>
	<div>
		 <spring:message code="comments"></spring:message>
		 <display:table name="note" id="row" requestURI="${requestURI}" class="displaytag"> 
			<display:column property="commentReferee" titleKey="commentReferee"></display:column>
			<display:column property="commentCustomer" titleKey="commentCustomer"></display:column>
			<display:column property="commentHandyWorker" titleKey="commentHandyWorker"></display:column>
		</display:table>
	</div>
	<div>
		<c:if test="${empty note.commentCustomer}">
			<a href="note/customer/edit.do?noteId=${note.id}"><input type="button" value="<spring:message code='button.edit'></spring:message>"></a>
		</c:if>
	</div>
</security:authorize>
 </div>
</body>