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
        	<spring:message code="register.referee" />
        </h2>
      </header>
      
      <div class="content">
    		
    	<form:form class="formularioEdicion" method="POST" modelAttribute="referee" action="referee/administrator/create.do">
          	<form:hidden path="id"/>
          	<form:hidden path="version"/>
          	<form:hidden path="socialProfiles"/>
          	<form:hidden path="isBanned"/>
          	<form:hidden path="isSuspicious"/>
          	<form:hidden path="mailBoxes"/>
          	
          	<form:label path="name"><spring:message code="referee.name" /></form:label>
			<form:input path="name" required="required"/>
			<form:errors cssClass="error" path="name"/><br>
			
			<form:label path="address"><spring:message code="referee.address" /></form:label>
			<form:input path="address" required="required"/>
			<form:errors cssClass="error" path="address"/><br>
			
			<form:label path="surname"><spring:message code="referee.surname" /></form:label>
			<form:input path="surname" required="required"/>
			<form:errors cssClass="error" path="surname"/><br>
			
			<form:label path="phone"><spring:message code="referee.phone" /></form:label>(+${phone})
			<form:input path="phone"/>
			<form:errors cssClass="error" path="phone"/><br>
			
			
			<form:label path="middleName"><spring:message code="referee.middleName" /></form:label>
			<form:input path="middleName" required="required"/>
			<form:errors cssClass="error" path="middleName"/><br>
			
			<form:label path="email"><spring:message code="referee.email" /></form:label>
			<form:input path="email" required="required"/>
			<form:errors cssClass="error" path="email"/><br>
			
			<form:label path="photo"><spring:message code="referee.photo" /></form:label>
			<form:input path="photo" required="required"/>
			<form:errors cssClass="error" path="photo"/><br>
			
			<form:label path="userAccount.username"><spring:message code="referee.username" /></form:label>
			<form:input path="userAccount.username" required="required"/>
			<form:errors cssClass="error" path="userAccount.username"/><br>
			
			<form:label path="userAccount.password"><spring:message code="referee.password" /></form:label>
			<form:password path="userAccount.password" required="required"/>
			<form:errors cssClass="error" path="userAccount.password"/><br>
			
			<form:hidden path="userAccount.authorities"/>
			<form:hidden path="userAccount.isBanned"/>
			<form:hidden path="userAccount.isSuspicious"/>
			
			<input type="submit" name="save" value=<spring:message code="send" />/>
		</form:form>
      </div>
      
 	</article>

  
  </section>