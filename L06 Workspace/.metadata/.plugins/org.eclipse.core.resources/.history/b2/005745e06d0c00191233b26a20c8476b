<%--
 * action-2.jsp
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

	<section id="main-content">
  
    	<article>
      		<header>
        		<h2>
        			<spring:message code="customer.welcome.listing" />
        			<jstl:out value="${fixUp.ticker}"></jstl:out>
        			<a title="AcmeTitle" href="application/handyWorker/create.do?id=${fixUp.id}"><img src="images/diana.png" alt="Apply" width="3%"/></a>
        		</h2>
      		</header>
      
      		<div class="content">
				<img class="lupa" src="images/lupa.png" alt="Luga" width="19%"/>
				<table>
					<tr><td><strong><spring:message code="customer.showing.details" /></strong></td></tr>
    						
    				<tr><td><spring:message code="customer.showing.createDate" /><jstl:out value="${fixUp.moment}"></jstl:out></td></tr>
    							
    				<tr><td><spring:message code="customer.showing.address" /><jstl:out value="${fixUp.address}"></jstl:out></td></tr>
    							
    				<tr><td><spring:message code="customer.showing.price" /><jstl:out value="${fixUp.maxPrice.quantity}"></jstl:out><jstl:out value="${fixUp.maxPrice.currency}"></jstl:out></td></tr>
    							
    				<tr><td><spring:message code="customer.showing.startDate" /><jstl:out value="${fixUp.startDate}"></jstl:out></td></tr>
    							
    				<tr><td><spring:message code="customer.showing.endDate" /><jstl:out value="${fixUp.endDate}"></jstl:out></td></tr>
    							
    				<tr><td><spring:message code="customer.showing.handyWorker" /><jstl:out value="${fixUp.handyWorker.name}"></jstl:out></td></tr>
    							
    				<c:choose>
    					<c:when test="${language=='English'}">
        					<tr><td><spring:message code="customer.showing.category" /><jstl:out value="${category.nameEN}"></jstl:out></td></tr> 
    					</c:when>    
    					<c:otherwise>
        					<tr><td><spring:message code="customer.showing.category" /><jstl:out value="${category.nameES}"></jstl:out></td></tr>
    					</c:otherwise>
					</c:choose>
        			<tr><td><strong><spring:message code="customer.showing.description" /></strong></td></tr>
        			
        			<tr><td><jstl:out value="${fixUp.description}"></jstl:out></td></tr>
					
          			</table>
          			
          			<div>
	<h2><spring:message code="workplan.show" /></h2>
	<display:table list="requestScope.workplan" name="workplan" id="row" requestURI="${requestURI }" pagesize="5" class="displaytag">
		
		<display:column property="title" titleKey="phase.title"/>
		<display:column property="description" titleKey="phase.description"/>
		<display:column property="startDate" titleKey="phase.startDate" format="{0,date,dd/MM/yyyy HH:mm}"/>
		<display:column property="endDate" titleKey="phase.endDate" format="{0,date,dd/MM/yyyy HH:mm}"/>
		<jstl:if test="${checkHW}" >
  			<display:column titleKey="edit"> 
				<a href="workplan/handyWorker/edit.do?phaseId=${row.id}"><spring:message code="edit"/></a>
			</display:column>
			<display:column titleKey="delete"> 
				<a href="workplan/handyWorker/delete.do?phaseId=${row.id}" onclick="return confirm('<spring:message code='confirm.delete'></spring:message>');"><spring:message code="delete"/></a>
			</display:column>
  		</jstl:if>
			
	</display:table>
		<jstl:if test="${checkHW}" >
		
			                                <a href="workplan/handyWorker/create.do?fixUpId=${fixUp.id}"><spring:message code="phase.create"/></a>
		</jstl:if>
	</div>
      			</div>
      
				<form>
					<input type="button" value=<spring:message code="back" /> name="back" onclick="history.back()" />
				</form>
				
    	</article> <!-- /article -->
	</section> <!-- / #main-content -->
