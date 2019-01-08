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
        	<spring:message code="phase.welcome.edit" />
        </h2>
      </header>
      
      <div class="content">
          <form:form class="formularioEdicion" method="POST" modelAttribute="phase" action="workplan/handyWorker/edit.do">
          	<form:hidden path="id"/>
          	<form:hidden path="version"/>
          	<form:hidden path="fixUp"/>
          	
          	<form:label path="title"><spring:message code="phase.title" /></form:label>
			<form:textarea path="title" />
			<form:errors path="title"/><br>
			<form:label path="description"><spring:message code="phase.description" /></form:label>
			<form:textarea path="description" />
			<form:errors path="description"/><br>
			<form:label path="startDate"><spring:message code="phase.startDate" /></form:label>
			<form:input type="date" path="startDate" placeholder="yyyy/MM/dd HH:mm"/>
			<form:errors path="startDate"/><br>
			<form:label path="endDate"><spring:message code="phase.endDate" /></form:label>
			<form:input type="date" path="endDate" placeholder="yyyy/MM/dd HH:mm"/>
			<form:errors path="endDate"/><br>
			<input type="submit" name="save" value="<spring:message code="submit"/>" />
			<input type="button" name="cancel" onclick="javascript:relativeRedir('fixUp/handyWorker/list.do')" value="<spring:message code="cancel"/>"/>
		</form:form>
      </div>
      
    </article> <!-- /article -->
  
  </section> <!-- / #main-content -->
