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
  
   <display:table name="applications" id="row"  requestURI="${requestURI}"	pagesize="5" class="displaytag" >
  	
  	<display:column titleKey="handyWorker.editApplication"> 
  		<jstl:if test="${not row.state}">
  			<a href="application/handyWorker/edit.do?applicationId=${row.id}">Edit</a>
  		</jstl:if>
	</display:column>
	<display:column titleKey="application.show"> 
		<a href="application/handyWorker/show.do?applicationId=${row.id}"><spring:message code="application.show" /></a>
	</display:column>
  	<display:column property="fixUp.ticker" titleKey="application.fixUp"/>
  	<display:column titleKey="application.state">
  	<jstl:choose>
  		<jstl:when test="${row.state}">
  			<div id="aceptada"><spring:message code="application.state.accepted"/></div>
  		</jstl:when>
  		<jstl:when test="!${!row.state}">
  			<div id="rechazada"><spring:message code="application.state.rejected"/></div>
  		</jstl:when>
  		<jstl:otherwise>
  			<div id="pendiente"><spring:message code="application.state.pending"/></div>
  		</jstl:otherwise>
  	</jstl:choose>
  	</display:column>
  	<display:column property="offered.quantity" titleKey="application.offered"/>
  	<display:column property="comments" titleKey="application.comments"/>
  	<display:column property="creditCard.number" titleKey="application.creditCard"/>
  	<jstl:if test="${row.state}" >
  		<display:column titleKey="application.workplan"> 
			<a href="workplan/handyWorker/create.do?fixUpId=${row.fixUp.id}"><spring:message code="workplan.create"/></a>
		</display:column>
  	</jstl:if>
  	</display:table>
  	
  		
  		<c:choose>
    		<c:when test="${language=='English'}">
        		<form>
      				<input type="button" value="Back" name="volver atrás2" onclick="history.back()" />
	  			</form> 
    		</c:when>    
    		<c:otherwise>
		 		<form>
      				<input type="button" value="Volver" name="volver atrás2" onclick="history.back()" />
	  			</form>        		
    		</c:otherwise>
		</c:choose>
		
</body>