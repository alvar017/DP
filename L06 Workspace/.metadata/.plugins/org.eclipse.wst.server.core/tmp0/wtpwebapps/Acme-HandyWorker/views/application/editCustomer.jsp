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

<p><spring:message code="customer.editApplication" /></p>
<body>
	<form:form action="application/customer/edit.do" method="POST"
		modelAttribute="application">
		
			<form:hidden path="id" />
		<form:hidden path="version" />
		<form:hidden path="version" />
		<form:hidden path="applier" />
		<form:hidden path="fixUp"/>
		<form:hidden path="moment"/>
			
		<form:label path="comments">
			<spring:message code="application.comments" />:
		</form:label>
		<form:input path="comments" />
		<form:errors cssClass="error" path="comments" />
	
		<form:label path="offered.quantity">
			<spring:message code="application.offered.quantity" />:
		</form:label>
		<form:input type="number" path="offered.quantity" />
		<form:errors cssClass="error" path="offered.quantity" />
		
		<br />
		
		<form:label path="offered.currency">
			<spring:message code="application.offered.currency" />:
		</form:label>
		<form:select path="offered.currency" >
			<form:option value="EUR"></form:option>
			<form:option value="DOL"></form:option>
		</form:select>	
		<form:errors cssClass="error" path="offered.currency" />
		
		<br />
		<form:label path="creditCard.name">
			<spring:message code="application.creditCard.name" />:
		</form:label>
		<form:input path="creditCard.name" />
		<form:errors cssClass="error" path="creditCard.name" />
		
		<br />
			
		<form:label path="creditCard.brand">
			<spring:message code="application.creditCard.brand" />:
		</form:label>
		<form:select path="creditCard.brand" >
			<form:option value="VISA"></form:option>
			<form:option value="MASTERCARD"></form:option>
			<form:option value="DINNERS"></form:option>
			<form:option value="AMEX"></form:option>
		</form:select>	
		<form:errors cssClass="error" path="creditCard.brand" />
		
		<br />
		
		<form:label path="creditCard.number">
			<spring:message code="application.creditCard.number" />:
		</form:label>
		<form:input path="creditCard.number"/>
		<form:errors cssClass="error" path="creditCard.number" />
		
		<br />
		
		<form:label path="creditCard.cvv">
			<spring:message code="application.creditCard.cvv" />:
		</form:label>
		<form:input type="number" path="creditCard.cvv" />
		<form:errors cssClass="error" path="creditCard.cvv" />
		
		<br />
		
		<form:label path="state">
			<spring:message code="application.state" />:
		</form:label>
		<form:select path="state" >
			<form:option value="true"></form:option>
			<form:option value="false"></form:option>
		</form:select>	
		<form:errors cssClass="error" path="state" />
		
		<br />
		<input type="submit" name="save" value="<spring:message code="customer.editApplication" />" />
		
		</form:form>
	
		<input type="button" name="cancel" value="<spring:message code="application.cancel"/>" onclick="javascript:relativeRedir('application/customer/list.do');"/>


</body>