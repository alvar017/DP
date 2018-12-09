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

<p><spring:message code="customer.action.1" /></p>
<body>
<div>
<table>
  <tr>
    <th>Ticker</th>
    <th><spring:message code="customer.price" /></th>
    <th><spring:message code="customer.description" /></th>
  </tr>
  <jstl:forEach var="fixUp" items="${fixUps}">
  	<tr>
  		<td><jstl:out value="${fixUp.ticker}"></jstl:out></td>
  		<td><jstl:out value="${fixUp.maxPrice.quantity}"></jstl:out><jstl:out value="${fixUp.maxPrice.currency}"></jstl:out></td>
  		<td><jstl:out value="${fixUp.description}"></jstl:out></td>
  	</tr>
  </jstl:forEach> 
</table>
</div>
</body>