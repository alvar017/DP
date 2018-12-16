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
        	<spring:message code="customer.welcome.edit" />
        	<jstl:out value="${fixUp.ticker}"></jstl:out>
        </h2>
      </header>
      
      <div class="content">
          
          <form:form class="formularioEdicion" method="POST" modelAttribute="Actor" action="actor/edit.do">
          	<form:hidden path="id"/>
          	
          	<form:label path="name"><spring:message code="actor.name" /></form:label>
			<form:input path="name" /><br>
			
			<form:label path="surname"><spring:message code="actor.surname" /></form:label>
			<form:input path="surname" /><br>
			
			<form:label path="middlename"><spring:message code="actor.middlename" /></form:label>
			<form:input path="middlename" /><br>
			
			<form:label path="address"><spring:message code="actor.address" /></form:label>
			<form:input path="address" /><br>
			
			<form:label path="email"><spring:message code="actor.email" /></form:label>
			<form:input path="email" /><br>

			<form:label path="email"><spring:message code="actor.email" /></form:label>
			<form:input path="email" /><br>
			
<!--  		TENGO QUE PREGUNTARLE SI EL USERNAME Y LA CONTRA SE EDITAN, Y SI SE MUESTRAN  -->
			<form:label path="username"><spring:message code="actor.username" /></form:label>
			<form:input path="username" /><br>
			
			<form:label path="password"><spring:message code="actor.password" /></form:label>
			<form:input path="password" /><br>	
			
			<form:label path="image"><spring:message code="actor.image" /></form:label>
			<form:input path="image" /><br>
			
			<security:authorize access= "hasRole('HANDYWORKER')" >
			<form:label path="make"><spring:message code="handyWorker" /></form:label>
			<form:input path="make" /><br>
			</security:authorize>					
	
			<input type="submit" name="name" value=<spring:message code="send" />/>
		</form:form>
      </div>
      
    </article> <!-- /article -->
    
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
  
  </section> <!-- / #main-content -->
  
