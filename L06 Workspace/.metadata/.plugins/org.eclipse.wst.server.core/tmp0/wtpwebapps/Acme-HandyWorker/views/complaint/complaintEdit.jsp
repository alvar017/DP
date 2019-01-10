<%--
 * complaintEdit.jsp
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

<form:form class="formularioEdicion" method="POST" modelAttribute="complaint" action="complaint/customer/edit.do">
	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="moment"/>
	<form:hidden path="ticker"/>
	<form:hidden path="referee"/>
	<div>
		<a href="complaint/customer/edit.do?complaintId=${complaint.id}"><input type="button" value="<spring:message code='button.reset'></spring:message>"></a>
		<a href="complaint/customer/list.do"><input type="button" value="<spring:message code='button.back'></spring:message>"></a>
		<input type="submit" name="save" value="<spring:message code='complaint.save' />" onclick="return confirm('<spring:message code='create'></spring:message>')"/>
		<br />
	</div>
	<div>
		<br />
		<form:label path="fixUp"><spring:message code="complaint.fixUp" /></form:label>
		<form:select  id="fixUps" path="fixUp">
			<form:options items="${fixUps}" itemLabel="ticker" itemValue="id"/>
		<%-- 	<form:option value="${complaint.fixUp}" label="${complaint.fixUp.ticker}"></form:option>
		 --%></form:select>
		<form:errors path="fixUp"></form:errors>
		<br />
	</div>
	<div>
		<form:label path="description"><spring:message code="complaint.description" /></form:label>
		<form:textarea path="description"/>
		<form:errors path="description"></form:errors>
		<br />
		<form:label path="attachment"><spring:message code="complaint.attachment" /></form:label>
		<form:textarea path="attachment"/>
		<form:errors path="attachment"></form:errors>
		<br />
	</div>
</form:form>