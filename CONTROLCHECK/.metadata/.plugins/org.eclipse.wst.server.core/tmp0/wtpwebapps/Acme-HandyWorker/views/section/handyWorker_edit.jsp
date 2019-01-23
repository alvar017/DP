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
          <form:form class="formularioEdicion" method="POST" modelAttribute="section" action="section/handyWorker/edit.do">
          	<form:hidden path="id"/>
          	<form:hidden path="version"/>
          	<form:hidden path="tutorial"/>
          	<form:hidden path="number"/>
          	
          	<form:label path="title"><spring:message code="phase.title" /></form:label>
			<form:textarea path="title" />
			<form:errors cssClass="error"  path="title"/><br>
			<form:label path="text"><spring:message code="section.text" /></form:label>
			<form:textarea path="text" />
			<form:errors cssClass="error"  path="text"/><br>
			<form:label path="picture"><spring:message code="section.picture" /></form:label>
			<form:textarea path="picture" />
			<form:errors cssClass="error"  path="picture"/><br>
			
			<input type="submit" name="save" value="<spring:message code="submit"/>" />
			<input type="button" name="cancel" onclick="javascript:relativeRedir('tutorial/handyWorker/list.do')" value="<spring:message code="cancel"/>"/>
		</form:form>
      </div>
      
    </article> <!-- /article -->
  
  </section> <!-- / #main-content -->
