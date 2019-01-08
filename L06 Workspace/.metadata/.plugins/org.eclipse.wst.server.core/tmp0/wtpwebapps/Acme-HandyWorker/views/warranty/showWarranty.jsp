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

<body>

<div class="content">
				<table>
					<tr><td><strong><spring:message code="warranty.show" /></strong></td></tr>
    						
    				<tr><td><spring:message code="warranty.show.title" /><jstl:out value="${warranty.title}"></jstl:out></td></tr>
    							
    				<tr><td><spring:message code="warranty.show.terms" /><jstl:out value="${warranty.terms}"></jstl:out></td></tr>
    				  								
					<tr><td><spring:message code="warranty.show.laws" /><jstl:out value="${warranty.laws}"></jstl:out></td></tr>
										          			
          			<tr><td><spring:message code="warranty.show.isFinal" /><jstl:out value="${warranty.isFinal}"></jstl:out></td></tr>
          			</table>
          			          			
		<c:choose>
   			<c:when test="${warranty.isFinal != true}">
    
			<a onclick="return confirmar('accion.html')" title="AcmeTitle" href="warranty/administrator/delete.do?id=${warranty.id}"><img src="images/delete.png" alt="Delete" width="3%"/></a>	
    		</c:when>    
    		<c:otherwise>
        	
    		</c:otherwise>
		</c:choose>
      			</div>