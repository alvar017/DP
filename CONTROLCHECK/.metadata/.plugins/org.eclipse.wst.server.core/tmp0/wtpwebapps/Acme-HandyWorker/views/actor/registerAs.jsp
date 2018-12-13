<%--
 * action-1.jsp
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

<p><spring:message code="register.registerAs" /></p>

      <header>
        <h2>
        	<spring:message code="actor.registerAs" />
        </h2>
      </header>

     <article>

 
			
      		<a href="customer/create.do"><input type="button" value="<spring:message code='actor.register.customer'></spring:message>"></a>
	  		<a href="handyworker/create.do"><input type="button" value="<spring:message code='actor.register.handyworker'></spring:message>"></a> 
	  	
	  	</article>	


</body>