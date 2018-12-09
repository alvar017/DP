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
        <h1><spring:message code="customer.welcome.listing" /><jstl:out value="${fixUp.ticker}"></jstl:out></h1>
      </header>
      
      <div class="content">
          <table width="50%" border="1" align="center">
  				<TR>
    					<TD ROWSPAN=2><i><strong><spring:message code="customer.showing.details" /></strong></i>
    						<p>
    							<spring:message code="customer.showing.createDate" /><jstl:out value="${fixUp.moment}"></jstl:out>
    							<br>
    							<spring:message code="customer.showing.address" /><jstl:out value="${fixUp.address}"></jstl:out>
    							<br>
    							<spring:message code="customer.showing.price" /><jstl:out value="${fixUp.maxPrice.quantity}"></jstl:out><jstl:out value="${fixUp.maxPrice.currency}"></jstl:out>
    							<br>
    							<spring:message code="customer.showing.startDate" /><jstl:out value="${fixUp.startDate}"></jstl:out>
    							<br>
    							<spring:message code="customer.showing.endDate" /><jstl:out value="${fixUp.endDate}"></jstl:out>
    							<br>
    							<spring:message code="customer.showing.handyWorker" /><jstl:out value="${fixUp.handyWorker.name}"></jstl:out>
    							<br>
    							<c:choose>
    								<c:when test="${language=='English'}">
        								<spring:message code="customer.showing.category" /><jstl:out value="${category.nameEN}"></jstl:out> 
        								<br />
    								</c:when>    
    								<c:otherwise>
        								<spring:message code="customer.showing.category" /><jstl:out value="${category.nameES}"></jstl:out>
        								<br />
    								</c:otherwise>
								</c:choose>
    						</p>
    					</TD>
        				<TD><i><strong><spring:message code="customer.showing.description" /></strong></i>
        					<p><jstl:out value="${fixUp.description}"></jstl:out></p>
        				</TD>
  				</TR>
  				<TR>
    				    <TD><i><strong><spring:message code="customer.showing.attachment" /></strong></i>
        					<p>

        					</p>
        				</TD>
  				</TR>
          </table>
      </div>
      
    </article> <!-- /article -->
  
  </section> <!-- / #main-content -->