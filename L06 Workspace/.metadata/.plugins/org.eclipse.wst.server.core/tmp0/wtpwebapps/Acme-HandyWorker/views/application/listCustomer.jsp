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

<p><spring:message code="customer.listCustomer" /></p>
<body>

  <display:table name ="applications" id="row" 
  	requestURI="${requestURI}"
  	pagesize="5" class="displaytag" >
  	
  	<display:column titleKey="customer.editApplication"> 
		<a href="application/customer/edit.do?applicationId=${row.id}">Edit</a>
	</display:column>
  	<display:column property="fixUp.ticker" titleKey="application.fixUp"/>
  	<display:column property="applier.name" titleKey="application.applier"/>
  	<display:column property="state" titleKey="application.state"/>
  	<display:column property="offered.quantity" titleKey="application.offered"/>
  	<display:column property="comments" titleKey="application.comments"/>
  	<display:column property="creditCard.number" titleKey="application.creditCard"/>
  		
  	</display:table>
  	
  	<c:choose>
    		<c:when test="${language=='English'}">
        		<form>
      				<input type="button" value="Back" name="volver atr�s2" onclick="history.back()" />
	  			</form> 
    		</c:when>    
    		<c:otherwise>
		 		<form>
      				<input type="button" value="Volver" name="volver atr�s2" onclick="history.back()" />
	  			</form>        		
    		</c:otherwise>
		</c:choose>
  	  	
</body>