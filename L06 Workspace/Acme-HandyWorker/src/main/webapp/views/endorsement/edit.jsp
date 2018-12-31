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
          <form:form class="formularioEdicion" method="POST" modelAttribute="endorsement" action="endorsement/customer/edit.do">
          	<form:hidden path="moment"/>
          	<form:hidden path="endorsableSender"/>  
          	<form:hidden path="endorsableReceiver"/>           	

          	<form:label path="comments"><spring:message code="endorsement.comments" /></form:label>
			<form:textarea path="comments" required="required"/>
			<form:errors cssClass="comments" path="comments"/><br>

			<input type="submit" name="save" value=<spring:message code="send" />/>
		</form:form>
      </div>
      
    </article> <!-- /article -->
  
  </section> <!-- / #main-content -->
  
				<form>
					<input type="button" value=<spring:message code="back" /> name="back" onclick="history.back()" />
				</form>
