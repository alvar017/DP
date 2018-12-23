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

<div class="content">
	<img class="lupa" src="images/lupa.png" alt="Luga" width="19%"/>
		<table>
			<tr><td><strong><spring:message code="handyWorker.action.1" /></strong></td></tr>
    						
    	<!--  		<tr><td><spring:message code="finder.maxPrice" /><jstl:out value=" ${finder.maxPrice}"></jstl:out></td></tr>
    							
    				<tr><td><spring:message code="finder.minPrice" /><jstl:out value=" ${finder.minPrice}"></jstl:out></td></tr>
    							
    				<tr><td><spring:message code="finder.keyword" /><jstl:out value=" ${finder.keyword}"></jstl:out>
    				    							
    				<tr><td><spring:message code="finder.startDate" /><jstl:out value=" ${finder.startDate}"></jstl:out></td></tr>
    							
    				<tr><td><spring:message code="finder.date" /><jstl:out value=" ${finder.date}"></jstl:out></td></tr>
    							
    				<tr><td><spring:message code="finder.endDate" /><jstl:out value=" ${finder.endDate}"></jstl:out></td></tr>
    							
    				<tr><td><spring:message code="finder.warranty" /><jstl:out value=" ${finder.warranty.title}"></jstl:out></td></tr>
    		
    				<c:choose>
    					<c:when test="${language=='English'}">
        					<tr><td><spring:message code="finder.category" /><jstl:out value="${ finder.category.nameEN}"></jstl:out></td></tr> 
    					</c:when>    
    					<c:otherwise>
        					<tr><td><spring:message code="finder.category" /><jstl:out value="${ finder.category.nameES}"></jstl:out></td></tr>
    					</c:otherwise>
					</c:choose> -->
    		
    				<tr><td><i><spring:message code="finder.fixUps" /></i></td></tr>
					<tr><td>
					<div>
						<security:authorize access="hasRole('HANDYWORKER')">
							<display:table name="finder.fixUps" id="row" requestURI="${requestURI}" pagesize="3" class="displaytag">
								<display:column property="ticker" titleKey="finder.fixUps.ticker" ></display:column>
								<display:column property="description" titleKey="finder.fixUps.description"></display:column>
							</display:table>
						</security:authorize>
					</div>
					</td></tr>
		
</table>
</div>

<a title="AcmeTitle" href="finder/handyWorker/edit.do?finderId=${finder.id}"><img src="images/edit.png" alt="Edit" width="3%"/></a>

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

