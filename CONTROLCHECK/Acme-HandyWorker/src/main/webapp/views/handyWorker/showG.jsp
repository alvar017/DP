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

					
      		<div class="content">
				<img class="lupa" src="images/lupa.png" alt="Luga" width="19%"/>
					<table>
								<tr><td><spring:message code="actor.name" /><jstl:out value="${handyWorker.name}"></jstl:out></td></tr>
								<tr><td><spring:message code="handyWorker.make" /><jstl:out value="${handyWorker.make}"></jstl:out></td></tr>
								<tr><td><spring:message code="actor.address" /><jstl:out value="${handyWorker.address}"></jstl:out></td></tr>
								<tr><td><spring:message code="actor.surname" /><jstl:out value="${handyWorker.surname}"></jstl:out></td></tr>
								<tr><td><spring:message code="actor.middleName" /><jstl:out value="${handyWorker.middleName}"></jstl:out></td></tr>
								<tr><td><spring:message code="actor.email" /><jstl:out value="${handyWorker.email}"></jstl:out></td></tr>
								<tr><td><spring:message code="actor.photo" /><jstl:out value="${handyWorker.photo}"></jstl:out></td></tr>
								<tr><td><spring:message code="actor.username" /><jstl:out value="${handyWorker.userAccount.username}"></jstl:out></td></tr>
								<tr><td>
									<p><spring:message code="handyWorker.tutorials" /></p>
									<display:table name="tutorials" id="row" requestURI="${requesURI }" pagesize="5" class="displaytag">
										<display:column titleKey="tutorial.title"> <a href="tutorial/show.do?tutorialId=${row.id}">${row.title }</a></display:column>
										<display:column property="moment" titleKey="tutorial.moment" format="{0,date,dd/MM/yyyy HH:mm}"/>
										<display:column property="summary" titleKey="tutorial.summary"/>
									</display:table>
								</td></tr>
					</table>
			</div>


				<form>
					<input type="button" value=<spring:message code="back" /> name="back" onclick="history.back()" />
				</form>
