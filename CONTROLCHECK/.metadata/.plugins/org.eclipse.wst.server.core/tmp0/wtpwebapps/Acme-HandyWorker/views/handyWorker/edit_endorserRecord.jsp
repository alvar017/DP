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
        	<spring:message code="handyWorker.edit.endorserRecord" />
        </h2>
      </header>
      
      <div class="content">
    		
    	<form:form class="formularioEdicion" method="POST" modelAttribute="endorserRecord" action="endorserRecord/handyWorker/edit.do">
          	<form:hidden path="id"/>
          	<form:hidden path="version"/>
          	
          	<form:label path="title"><spring:message code="curriculum.title" /></form:label>
			<form:input path="title" required="required"/>
			<form:errors cssClass="error" path="title"/><br>
			
			<form:label path="email"><spring:message code="handyWorker.email" /></form:label>
			<form:input path="email" required="required"/>
			<form:errors cssClass="error" path="email"/><br>
			
			<form:label path="phone"><spring:message code="handyworker.phone" /></form:label>
			<form:input path="phone" required="required"/>
			<form:errors cssClass="error" path="phone"/><br>
			
			<form:label path="linkedIn"><spring:message code="curriculum.linkedIn" /></form:label>
			<form:input path="linkedIn" required="required"/>
			<form:errors cssClass="error" path="linkedIn"/><br>

			<form:label path="comments"><spring:message code="curriculum.comments" /></form:label>
			<form:input path="comments" required="required"/>
			<form:errors cssClass="error" path="comments"/><br>
					
			<input type="submit" name="save" value=<spring:message code="send" />/>
		</form:form>
      </div>
      
 	</article>

  
  </section>
