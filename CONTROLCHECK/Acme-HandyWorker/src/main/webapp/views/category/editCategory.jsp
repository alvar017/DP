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

<p><spring:message code="administrator.editCategory" /></p>
<body>
	<form:form action="category/administrator/edit.do" method="POST"	modelAttribute="category">
		
		<form:hidden path="id" />
		<form:hidden path="version" />
				
		<form:label path="nameES">
			<spring:message code="category.nameES" />:
		</form:label>
		<form:input path="nameES" />
		<form:errors cssClass="error" path="nameES" />
		
		<br />
		
		<form:label path="nameEN">
			<spring:message code="category.nameEN" />:
		</form:label>
		<form:input path="nameEN" />
		<form:errors cssClass="error" path="nameEN" />
		
		<br />
		
		<form:label path="parentCategory">
			<spring:message code="category.parentCategory" />:
		</form:label>
		<form:select path="parentCategory">
   		 	<form:options items="${nameCategories}" />
		</form:select>
		<form:errors cssClass="error" path="parentCategory" />
		
		<br />
	
		<input type="submit" name="save" value="<spring:message code="administrator.editCategory" />" />
		
		</form:form>
		
		<input type="button" name="cancel" value="<spring:message code="category.cancel"/>" onclick="javascript:relativeRedir('category/administrator/list.do');"/>
</body>