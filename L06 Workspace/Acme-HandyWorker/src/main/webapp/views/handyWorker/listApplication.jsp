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

<p><spring:message code="handyWorker.listApplication" /></p>
<body>
  
   <display:table name ="applications" id="row" 
  	requestURI="application/customer/listApplication.do"
  	pagesize="5" class="displaytag" >
  	
  	<display:column titleKey="edit"> 
		<a href="application/handyWorker/editApplication.do?applicationId=${row.id}">handyWorker.editApplication</a>
	</display:column>
  	<display:column property="fixUp" titleKey="application.fixUp"/>
  	<display:column property="state" titleKey="application.state"/>
  	<display:column property="offered" titleKey="application.offered"/>
  	<display:column property="comments" titleKey="application.comments"/>
  	<display:column property="creditCard" titleKey="application.creditCard"/>
  	
  	</display:table>
  	
  	  	<p class="create"><input type="button" value=<spring:message code="application.create" /> id="buttonCreateApplication" name="buttonCreateApplication"  onclick="location.href='application/handyWorker/createApplication.do?create=false';"/></p>
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