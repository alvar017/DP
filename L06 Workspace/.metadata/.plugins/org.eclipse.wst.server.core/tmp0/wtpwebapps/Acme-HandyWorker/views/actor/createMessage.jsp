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
        	<spring:message code="customer.newMessage" />
        </h2>
      </header>
      
      <div class="content">
		<form action="Message/actor/createMessage.do?create=true" method="post">
		
		
		    <div>
        		<label for="receiver"><spring:message code="actor.message.receiver" /></label>
        		<input type="text" id="received" />
    		</div>
    		
    		<div>
        		<label for="subject"><spring:message code="actor.message.subject" /></label>
        		<input type="text" id="subject" />
    		</div>
    		
    		<div>
    		    <label for="make"><spring:message code="handyWorker.make" /></label>
        		<form:select path="priority" >
				<form:options items="${priorities}" itemLabel="nameES" itemValue="id"/>
				</form:select><br> 
    		</div>
    		    		
    		<div>
        		<label for="body"><spring:message code="actor.message.body" /></label>
        		<input type="text"  id="body" />
			</div>
			
			
    		<div class="button">
        		<button type="submit"><spring:message code="actor.message.send" /></button>
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
