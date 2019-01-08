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

<p><spring:message code="listMailBox"/></p>
<body>
  
   <display:table name="mailBoxes" id="row"  requestURI="${requestURI}"	pagesize="5" class="displaytag" >
  	
  	<display:column titleKey="administrator.showMailBox"> 
          			<a href="message/list.do?mailBoxId=${row.id}"><spring:message code="seeMessages" /></a>
	</display:column>
  	<c:choose>
   		<c:when test="${row.isDefault != true}">
    <display:column titleKey="administrator.editMailBox"> 
		<a href="mailBox/edit.do?mailBoxId=${row.id}"><spring:message code="editMailBox" /></a>
	</display:column>
    	</c:when>    
    	<c:otherwise>
        	<display:column titleKey="administrator.editMailBox"/>
    	</c:otherwise>
	</c:choose>
  	
  	<display:column property="name" titleKey="mailBox.name"/>
  	
  	<c:choose>
   		<c:when test="${row.isDefault != true}">
    <display:column titleKey="administrator.deleteMailBox"> 
		<a href="mailBox/delete.do?mailBoxId=${row.id}"><spring:message code="deleteMailBox" /></a>
	</display:column>
    	</c:when>    
    	<c:otherwise>
        	<display:column titleKey="administrator.deleteMailBox"/>
    	</c:otherwise>
	</c:choose>
  	
  	</display:table>
  	
	<p class="create"><input type="button" value=<spring:message code="createMailBox" /> id="buttonMailBox" name="buttonMailBox"  onclick="location.href='mailBox/create.do';"/></p>
  	
  	<br>
  	<a href="message/create.do"><spring:message code="send.msg" /></a>
  	
  	<br>
  	
  	<security:authorize access="hasRole('ADMIN')">
  		<a href="message/createBroadcast.do"><spring:message code="send.msg.broadcast" /></a>
	</security:authorize>

  			
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