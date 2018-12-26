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
          	<form:hidden path="handyWorker"/>
          	<form:hidden path="version"/>
          	<form:hidden path="id"/>
          	
          	<form:hidden path="moment"/>
          	
          	<form:hidden path="applications"/>
          	<form:hidden path="complaints"/>
          	<form:label path="description"><spring:message code="customer.showing.description" /></form:label>
			<form:textarea path="description" required="required"/>
			<form:errors cssClass="error" path="description"/><br>
			<form:label path="address"><spring:message code="customer.showing.address" /></form:label>
			<form:input path="address" required="required"/>
			<form:errors cssClass="error" path="address"/><br>
			<form:label path="maxPrice.quantity"><spring:message code="customer.showing.price" /></form:label>
			<form:input type="number" path="maxPrice.quantity" required="required"/>
			<form:errors cssClass="error" path="maxPrice.quantity"/><br>
			<form:label path="maxPrice.currency"><spring:message code="customer.showing.currency" /></form:label>
			<!--  
			<form:input path="maxPrice.currency" /><br>
			-->
			<form:select path="maxPrice.currency" >
				<form:option value="EUR"></form:option>
				<form:option value="DOL"></form:option>
			</form:select>
			<form:errors cssClass="error" path="maxPrice.currency"/><br>
			<form:label path="startDate"><spring:message code="customer.showing.startDate" /></form:label>
			<form:input type="date" path="startDate" required="required"/>
			<form:errors cssClass="error" path="startDate"/><br>
			<form:label path="endDate"><spring:message code="customer.showing.endDate" /></form:label>
			<form:input path="endDate" required="required"/>
			<form:errors cssClass="error" path="endDate"/><br>
			<form:label path="warranty"><spring:message code="customer.showing.warranty" /></form:label>
			<form:select path="warranty" >
				<form:options items="${warranties}" itemLabel="title" itemValue="id"/>
			</form:select>
			<form:errors cssClass="error" path="warranty"/><br>
			<form:label path="category"><spring:message code="customer.showing.category" /></form:label>
			<form:select path="category" >
				<form:options items="${categories}" itemLabel="nameES" itemValue="id"/>
			</form:select>
			<form:errors cssClass="error" path="category"/><br>
			<input type="submit" name="save" value=<spring:message code="send" />/>
		</form:form>
      </div>
      
    </article> <!-- /article -->
  
  </section> <!-- / #main-content -->
  
				<form>
					<input type="button" value=<spring:message code="back" /> name="back" onclick="history.back()" />
				</form>
