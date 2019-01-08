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
      
      		<div class="content">
				<img class="lupa" src="images/lupa.png" alt="Luga" width="19%"/>
				<table>
					<tr><td><strong><spring:message code="hw.showing.details" /></strong></td></tr>
    						
    				<tr><td><spring:message code="hw.showing.hw" /><jstl:out value="${note.handyWorker.name}"></jstl:out></td></tr>
    							
    				<tr><td><spring:message code="hw.showing.moment" /><jstl:out value="${note.moment}"></jstl:out></td></tr>
    							    							
    				<tr><td><spring:message code="hw.showing.comment" /><jstl:out value="${note.commentHandyWorker}"></jstl:out><jstl:out value="${fixUp.maxPrice.currency}"></jstl:out></td></tr>
    							
    			</table>
    			</div>	
				<form>
					<input type="button" value=<spring:message code="back" /> name="back" onclick="history.back()" />
				</form>
				
    	</article> <!-- /article -->
	</section> <!-- / #main-content -->
