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
        	<spring:message code="handyWorker.edit" />
        	<jstl:out value="${fixUp.ticker}"></jstl:out>
        </h2>
      </header>
      
      <div class="content">
          <form:form class="formularioEdicion" method="POST" modelAttribute="fixUp" action="handyWorker/action-2.do">
          	<form:hidden path="id"/>
          	<form:label path="fixUps"><spring:message code="handyWorker.showing.fixUps" /></form:label>
			<form:textarea path="fixUps" /><br>
			<form:label path="keyword"><spring:message code="handyWorker.showing.keyword" /></form:label>
			<form:input path="keyword" /><br>
			<form:label path="minPrice"><spring:message code="handyWorker.showing.minPrice" /></form:label>
			<form:input path="minPrice" /><br>
			<form:label path="maxPrice"><spring:message code="handyWorker.showing.maxPrice" /></form:label>
			<form:input path="maxPrice" /><br>
			<form:label path="startDate"><spring:message code="handyWorker.showing.startDate" /></form:label>
			<form:input path="startDate" /><br>
			<form:label path="endDate"><spring:message code="handyWorker.showing.endDate" /></form:label>
			<form:input path="endDate" /><br>
			<form:label path="date"><spring:message code="handyWorker.showing.date" /></form:label>
			<form:input path="date" /><br>
			<form:label path="category"><spring:message code="handyWorker.showing.category" /></form:label>
			<form:input path="category" /><br>
			<form:label path="warranty"><spring:message code="handyWorker.showing.warranty" /></form:label>
			<form:input path="warranty" /><br>
			<input type="submit" name="name" value=<spring:message code="hw.send" />/>
			
		</form:form>
      </div>
      
    </article> <!-- /article -->
  
  </section> <!-- / #main-content -->
  
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