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
          <form:form class="formularioEdicion" method="POST" modelAttribute="quolet" action="quolet/customer/edit.do">
          	<form:hidden path="id"/>
            <form:hidden path="version"/>
          	<form:hidden path="customer"/>
          	<form:hidden path="ticker"/>
          	<form:hidden path="moment"/>
          	
          	<form:label path="body"><spring:message code="body" /></form:label>
			<form:textarea path="body" required="required"/>
			<form:errors cssClass="error" path="body"/><br>
			
			<form:label path="picture"><spring:message code="picture" /></form:label>
			<form:textarea path="picture" />
			<form:errors cssClass="error" path="picture"/><br>
			
			<form:label path="isFinal"><spring:message code="isFinal" /></form:label>
			<form:select path="isFinal" >
				<form:option value="true"><spring:message code="isFinal.true"/></form:option>
				<form:option value="false"><spring:message code="isFinal.false"/></form:option>
			</form:select>
			<form:errors cssClass="error" path="isFinal" /><br />
			
			<form:label path="fixUp"><spring:message code="fixUps" /></form:label>
			<form:select path="fixUp" required="required">
				<form:options items="${fixUps}" itemLabel="ticker" itemValue="id"/>
			</form:select>
			<form:errors cssClass="error" path="fixUp"/><br>
			
			<input type="submit" name="save" value=<spring:message code="send" />/>
		</form:form>
      </div>
      
    </article> <!-- /article -->
  
  </section> <!-- / #main-content -->
  
			<form method="get" action="quolet/customer/list.do">
    			<button type="submit"><spring:message code="button.back" /></button>
			</form>
