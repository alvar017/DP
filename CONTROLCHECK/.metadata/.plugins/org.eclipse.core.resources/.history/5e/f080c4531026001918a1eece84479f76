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

<p><spring:message code="administrator.statistics"/></p>
<body>

	<table>
   		 <tr><td><spring:message code="minFixUpPerUser" /><jstl:out value="${minFixUpPerUser}"></jstl:out></td></tr>
		 <tr><td><spring:message code="maxFixUpPerUser" /><jstl:out value="${maxFixUpPerUser}"></jstl:out></td></tr>
   		 <tr><td><spring:message code="avgFixUpPerUser" /><jstl:out value="${avgFixUpPerUser}"></jstl:out></td></tr>
   		 <tr><td><spring:message code="desviationFixUpPerUser" /><jstl:out value="${desviationFixUpPerUser}"></jstl:out></td></tr>
    </table>
    
    <table>
   		 <tr><td><spring:message code="minAppPerFixUp" /><jstl:out value="${minAppPerFixUp}"></jstl:out></td></tr>
		 <tr><td><spring:message code="maxAppUpPerFixUp" /><jstl:out value="${maxAppUpPerFixUp}"></jstl:out></td></tr>
   		 <tr><td><spring:message code="avgAppPerFixUp" /><jstl:out value="${avgAppPerFixUp}"></jstl:out></td></tr>
   		 <tr><td><spring:message code="desviationAppPerFixUp" /><jstl:out value="${desviationAppPerFixUp}"></jstl:out></td></tr>
    </table>
    
 	<table>
   		 <tr><td><spring:message code="minPriceFixUp" /><jstl:out value="${minPriceFixUp}"></jstl:out></td></tr>
		 <tr><td><spring:message code="maxPriceFixUp" /><jstl:out value="${maxPriceFixUp}"></jstl:out></td></tr>
   		 <tr><td><spring:message code="avgPriceFixUp" /><jstl:out value="${avgPriceFixUp}"></jstl:out></td></tr>
   		 <tr><td><spring:message code="desviationPriceFixUp" /><jstl:out value="${desviationPriceFixUp}"></jstl:out></td></tr>
    </table>
   
    <table>
   		 <tr><td><spring:message code="minPriceApp" /><jstl:out value="${minPriceApp}"></jstl:out></td></tr>
		 <tr><td><spring:message code="maxPriceApp" /><jstl:out value="${maxPriceApp}"></jstl:out></td></tr>
   		 <tr><td><spring:message code="avgPriceApp" /><jstl:out value="${avgPriceApp}"></jstl:out></td></tr>
   		 <tr><td><spring:message code="desviationPriceApp" /><jstl:out value="${desviationPriceApp}"></jstl:out></td></tr>
    </table>
    
     <table>
   		 <tr><td><spring:message code="pendingApplicationRatio" /><jstl:out value="${pendingApplicationRatio}"></jstl:out></td></tr>
		 <tr><td><spring:message code="acceptedApplicationRatio" /><jstl:out value="${acceptedApplicationRatio}"></jstl:out></td></tr>
   		 <tr><td><spring:message code="rejectedApplicationRatio" /><jstl:out value="${rejectedApplicationRatio}"></jstl:out></td></tr>
   		 <tr><td><spring:message code="unModificableApplicationRatio" /><jstl:out value="${unModificableApplicationRatio}"></jstl:out></td></tr>
    </table>
    
   <display:table name="betterCustomers" id="row"  requestURI="${requestURI}"	pagesize="5" class="displaytag" >
  	
  	<display:column property="fixUps" titleKey="customer.fixUps"/>
  	<display:column property="name" titleKey="customer.name"/>
  	<display:column property="surname" titleKey="customer.surname"/>
   	<display:column property="email" titleKey="customer.email"/> 	
  	
  	</display:table>
  	
  	<display:table name="betterHandyWorkers" id="row"  requestURI="${requestURI}"	pagesize="5" class="displaytag" >
  	
  	<display:column property="applications" titleKey="handyWorker.applications"/>
  	<display:column property="name" titleKey="handyWorker.name"/>
  	<display:column property="surname" titleKey="handyWorker.surname"/>
   	<display:column property="email" titleKey="handyWorker.email"/> 	
  	
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