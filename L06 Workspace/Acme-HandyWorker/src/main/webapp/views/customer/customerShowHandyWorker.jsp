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
        			Customer: <jstl:out value="${customer.name}"></jstl:out>  <jstl:out value="${customer.surname}"></jstl:out>
        		</h2>
      		</header>
      
      		<div class="content">
				<table>
					<tr><td><img class="customer_photo" src="${customer.photo}" alt="Photo" width="5%"/></td></tr>
					<tr><td><strong><spring:message code="customer.details" /> <jstl:out value="${customer.name}"></jstl:out>: </strong></td></tr>
    						
    				<tr><td><spring:message code="customer.name" /> <jstl:out value="${customer.name}"></jstl:out></td></tr>
    							
    				<tr><td><spring:message code="customer.middlename" /> <jstl:out value="${customer.middleName}"></jstl:out></td></tr>
    							
    				<tr><td><spring:message code="customer.surname" /> <jstl:out value="${customer.surname}"></jstl:out></td></tr>			
    							
    				<tr><td><spring:message code="customer.address" /> <jstl:out value="${cutomer.address}"></jstl:out></td></tr>

    				<tr><td><spring:message code="customer.email" /> <jstl:out value="${customer.email}"></jstl:out></td></tr>
    				
    				<tr><td><spring:message code="customer.calification" /><jstl:out value="${customer.calification}"></jstl:out></td></tr>
  
    							
    				
    					<tr><td>
    				    	<div>
    				    	<br></br>
								<p><strong><spring:message code="handyWorker.customer.fixUps" /> <jstl:out value="${customer.name}"></jstl:out></strong></p>
								<security:authorize access="hasRole('HANDYWORKER')">
								<display:table name="fixUps" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag">
									<display:column titleKey="customer.ticker"> 
										<a href="fixUp/handyWorker/show.do?fixUpId=${row.id}">${row.ticker}</a>
									</display:column>
									<display:column property="description" titleKey="customer.description"></display:column>
									<display:column property="maxPrice.quantity" titleKey="customer.price"></display:column>
									<display:column property="handyWorker.name" titleKey="handyWorker.nameHW"></display:column>
								</display:table>
								</security:authorize>
							</div>
  							
  						</td></tr>
								
  								
          			</table>
      			</div>
      
				<form>
					<input type="button" value=<spring:message code="back" /> name="back" onclick="history.back()" />
				</form>
				
    	</article> <!-- /article -->
	</section> <!-- / #main-content -->
