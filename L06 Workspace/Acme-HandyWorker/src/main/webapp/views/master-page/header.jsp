<%--
 * header.jsp
 *
 * Copyright (C) 2018 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>


<header id="main-header">
    
    <a title="AcmeTitle" href=""><img src="images/logo.png" alt="Logo" /></a>
 
    <nav>
		<ul id="jMenu">
		<!-- Do not forget the "fNiv" class for the first level links !! -->
		<security:authorize access="hasRole('ADMIN')">
			<li><a class="fNiv"><spring:message	code="master.page.administrator" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="actor/administrator/list.do"><spring:message code="master.page.administrator.action.1" /></a></li>
					<li><a href="administrator/action-2.do"><spring:message code="master.page.administrator.action.2" /></a></li>					
				</ul>
			</li>
		</security:authorize>
		
		<security:authorize access="hasRole('CUSTOMER')">
			<li><a class="fNiv"><spring:message	code="master.page.customer" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="fixUp/customer/list.do"><spring:message code="master.page.customer.action.1" /></a></li>
					<li><a href="application/customer/list.do"><spring:message code="master.page.customer.list.application" /></a></li>
					<li><a href="complaint/customer/list.do"><spring:message code="master.page.customer.complaint" /></a></li>					
				</ul>
			</li>
		</security:authorize>
		
		<security:authorize access="hasRole('HANDYWORKER')">
			<li><a class="fNiv"><spring:message	code="master.page.handyWorker" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="fixUp/handyWorker/list.do"><spring:message code="master.page.customer.action.1" /></a></li>
					<li><a href="finder/handyWorker/list.do"><spring:message code="master.page.handyWorker.finder" /></a></li>
					<li><a href="application/handyWorker/list.do"><spring:message code="master.page.handyWorker.list.application" /></a></li>
					<li><a href="tutorial/handyWorker/list.do"><spring:message code="master.page.handyWorker.tutorial" /></a></li>	
					<li><a href="complaint/handyWorker/list.do"><spring:message code="master.page.handyWorker.complaint" /></a></li>									
										
				</ul>
			</li>
		</security:authorize>
		
		<security:authorize access="isAnonymous()">
			<li><a class="fNiv" href="security/login.do"><spring:message code="master.page.login" /></a></li>
		</security:authorize>
		
		<security:authorize access="isAuthenticated()">
			<li>
				<a class="fNiv"> 
					<spring:message code="master.page.profile" /> 
			        (<security:authentication property="principal.username" />)
				</a>
				<ul>
					<li class="arrow"></li>					
					<li><a href="j_spring_security_logout"><spring:message code="master.page.logout" /> </a></li>
				</ul>
			</li>
		</security:authorize>
	</ul>
    </nav><!-- / nav -->
 
  </header><!-- / #main-header -->

<div class=language>
	<a href="?language=en"><img width="20px" src="images/uk.png" alt="en" /></a><a href="?language=es"><img width="20px" src="images/spain.png" alt="en" /></a>
</div>
