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
      <!-- 
        <h2>
        	<spring:message code="customer.welcome.edit" />
        	<jstl:out value="${fixUp.ticker}"></jstl:out>
        </h2>
	  -->
      </header>
      
      <div class="content">
          <img class="lupa" src="images/edit.png" alt="Edit" width="19%"/>
          <form:form class="formularioEdicion" method="POST" modelAttribute="fixUp" action="fixUp/customer/edit.do">
          	<form:hidden path="customer"/>
          	<form:hidden path="ticker"/>
          	<form:label path="description"><spring:message code="customer.showing.description" /></form:label>
			<form:textarea path="description" /><br>
			<form:label path="address"><spring:message code="customer.showing.address" /></form:label>
			<form:input path="address" /><br>
			<!--
			<form:label path="maxPrice.quantity"><spring:message code="customer.showing.price" /></form:label>
			<form:input path="maxPrice.quantity" /><br>
			<form:label path="startDate"><spring:message code="customer.showing.startDate" /></form:label>
			<form:input path="startDate" /><br>
			<form:label path="endDate"><spring:message code="customer.showing.endDate" /></form:label>
			<form:input path="endDate" /><br>
			<form:label path="warranty"><spring:message code="customer.showing.warranty" /></form:label>
			<form:select path="warranty" >
				<form:options items="${warranties}" itemLabel="title" itemValue="id"/>
			</form:select><br>
			<form:label path="category"><spring:message code="customer.showing.category" /></form:label>
			<form:select path="category" >
				<form:options items="${categories}" itemLabel="nameES" itemValue="id"/>
			</form:select><br>
			-->
			<input type="submit" name="save" value=<spring:message code="send" />/>
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
