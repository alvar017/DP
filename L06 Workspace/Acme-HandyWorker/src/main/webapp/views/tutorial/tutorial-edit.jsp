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
        	<spring:message code="tutorial.welcome.edit" />
        </h2>
      </header>
      
      <div class="content">
          <form:form class="formularioEdicion" method="POST" modelAttribute="tutorial" action="tutorial/handyWorker/edit.do">
          	<form:hidden path="id"/>
          	<form:hidden path="version"/>
          	<form:hidden path="sponsorships"/>
          	<form:hidden path="handyWorker"/>
          	<form:hidden path="sections"/>
          	<form:hidden path="moment"/>
          	
          	<form:label path="title"><spring:message code="tutorial.title" /></form:label>
			<form:textarea path="title" />
			<form:errors path="title"/><br>
			<form:label path="summary"><spring:message code="tutorial.summary" /></form:label>
			<form:textarea path="summary" />
			<form:errors path="summary"/><br>
			<form:label path="picture"><spring:message code="tutorial.image" /></form:label>
			<form:input path="picture" />
			<form:errors path="picture"/><br>
			<input type="submit" name="save" value="<spring:message code="submit"/>" />
			<input type="button" name="cancel" onclick="javascript:relativeRedir('tutorial/handyWorker/list.do')" value="<spring:message code="cancel"/>"/>
		</form:form>
      </div>
      
    </article> <!-- /article -->
  
  </section> <!-- / #main-content -->
