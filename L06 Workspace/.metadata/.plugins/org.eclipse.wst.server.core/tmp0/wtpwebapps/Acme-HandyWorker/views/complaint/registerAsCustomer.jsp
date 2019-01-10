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
        	<spring:message code="register.customer" />
        </h2>
      </header>
      
      <div class="content">
		<form action="customer/create.do?create=true" method="post">
    		<div>
        		<label for="name"><spring:message code="customer.name" /></label>
        		<input type="text" id="name" />
    		</div>
    		
    		<div>
        		<label for="middlename"><spring:message code="customer.middlename" /></label>
        		<input type="text" id="middlename" />
    		</div>
    		
    		<div>
        		<label for="surname"><spring:message code="customer.surname" /></label>
        		<input type="text" id="surname" />
    		</div>
    		    		
    		<div>
        		<label for="address"><spring:message code="customer.Address" /></label>
        		<input type="text" id="address2" />
    		</div>
    		
    		<div>
        		<label for="email"><spring:message code="customer.email" /></label>
        		<input type="text" id="email" />
    		</div>
    		
    		<div>
    		    <label for="username"><spring:message code="customer.username" /></label>
        		<input type="text" id="username" />
    		</div>
    		
    		<div>
    		    <label for="password"><spring:message code="customer.password" /></label>
        		<input type="text" id="password" />
    		</div>
    		
    		<div>
    		    <label for="photo"><spring:message code="customer.photo" /></label>
        		<input type="text" id="make" />
    		</div>
    		
    		<div class="button">
        		<button type="submit"><spring:message code="customer.create" /></button>
    		</div>
    		
    		</form>
      </div>
      
 	</article> <!-- /article -->
      		<c:choose>
    		<c:when test="${language=='English'}">
        		<form>
      				<input type="button" value="Back" name="volver atrás2" onclick="history.back()" />
	  			</form> 
    		</c:when>    
    		<c:otherwise>
		 		<form>
      				<input type="button" value="Volver" name="volver atrás2" onclick="history.back()" />
	  			</form>        		
    		</c:otherwise>
		</c:choose>
  
  </section> <!-- / #main-content -->
