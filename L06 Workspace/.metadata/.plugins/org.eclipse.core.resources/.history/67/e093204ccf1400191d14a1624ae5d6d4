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
        	<spring:message code="actor.edit" />
        </h2>
      </header>
      
      <div class="content">
    		
    	<form:form class="formularioEdicion" method="POST" modelAttribute="actor" action="actor/edit.do">
          	<form:hidden path="id"/>
          	<form:hidden path="version"/>
          	<form:hidden path="socialProfiles"/>
          	<form:hidden path="isBanned"/>
          	<form:hidden path="isSuspicious"/>
          	<form:hidden path="mailBoxes"/>
          	
          	<form:label path="name"><spring:message code="actor.name" /></form:label>
			<form:input path="name" required="required"/>
			<form:errors cssClass="error" path="name"/><br>
			
			<form:label path="address"><spring:message code="actor.address" /></form:label>
			<form:input path="address" required="required"/>
			<form:errors cssClass="error" path="address"/><br>
			
			<form:label path="surname"><spring:message code="actor.surname" /></form:label>
			<form:input path="surname" required="required"/>
			<form:errors cssClass="error" path="surname"/><br>
			
			<form:label path="phone"><spring:message code="actor.phone" /></form:label>(+${phone})
			<form:input path="phone"/>
			<form:errors cssClass="error" path="phone"/><br>
			
			<form:label path="middleName"><spring:message code="actor.middleName" /></form:label>
			<form:input path="middleName"/>
			<form:errors cssClass="error" path="middleName"/><br>
			
			<form:label path="email"><spring:message code="actor.email" /></form:label>
			<form:input path="email" required="required"/>
			<form:errors cssClass="error" path="email"/><br>
			
			<form:label path="photo"><spring:message code="actor.photo" /></form:label>
			<form:input path="photo"/>
			<form:errors cssClass="error" path="photo"/><br>
			<!--
			
			<form:label path="userAccount.username"><spring:message code="actor.username" /></form:label>
			<form:input path="userAccount.username" required="required"/>
			<form:errors cssClass="error" path="userAccount.username"/><br>
			
			<form:label path="userAccount.password"><spring:message code="actor.password" /></form:label>
			<form:password path="userAccount.password" required="required"/>
			<form:errors cssClass="error" path="userAccount.password"/><br>
			
			-->
			<form:hidden path="userAccount"/>
			<!-- 
			<form:hidden path="userAccount.username"/>
			<form:hidden path="userAccount.password"/>
			<form:hidden path="userAccount.id"/>
			<form:hidden path="userAccount.version"/>
			<form:hidden path="userAccount.authorities"/>
			<form:hidden path="userAccount.isBanned"/>
			<form:hidden path="userAccount.isSuspicious"/>
			 -->
			<input type="submit" name="save" value=<spring:message code="send" />/>
		</form:form>
			<form method="get" action=" ">
    			<button type="submit"><spring:message code="button.back" /></button>
			</form>
      </div>
      
 	</article>

  
  </section>
