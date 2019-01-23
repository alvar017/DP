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

<p><spring:message code="editMessage" /></p>
<body>
	<form:form action="message/editBroadcast.do" method="POST"	modelAttribute="msg">
		
		<form:hidden path="id" />
		<form:hidden path="version" />
		<form:hidden path="moment" />
		<form:hidden path="mailBoxes" />
		<form:hidden path="emailReceiver" />
		
		
				
		<form:label path="subject">
			<spring:message code="msg.subject" />:
		</form:label>
		<form:input path="subject" />
		<form:errors cssClass="error" path="subject" />
		
		<br />
		
		<form:label path="body">
			<spring:message code="msg.body" />:
		</form:label>
		<form:input path="body" />
		<form:errors cssClass="error" path="body" />
		
		<br />
		
		<form:label path="priority.value">
			<spring:message code="msg.priority" />:
		</form:label>
		<form:select path="priority.value" >
			<form:option value="HIGH"></form:option>
			<form:option value="NEUTRAL"></form:option>
			<form:option value="LOW"></form:option>
		</form:select>		
		<form:errors cssClass="error" path="priority.value" />
		
		<br />
		

		
		
		<input type="submit" name="save" value="<spring:message code="editMessage" />" />
		
		</form:form>
		
		<input type="button" name="cancel" value="<spring:message code="msg.cancel"/>" onclick="javascript:relativeRedir('mailBox/list.do');"/>
</body>