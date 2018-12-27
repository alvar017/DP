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
								<tr><td><spring:message code="actor.name" /><jstl:out value="${actor.name}"></jstl:out></td></tr>
								<tr><td><spring:message code="actor.address" /><jstl:out value="${actor.address}"></jstl:out></td></tr>
								<tr><td><spring:message code="actor.surname" /><jstl:out value="${actor.surname}"></jstl:out></td></tr>
								<tr><td><spring:message code="actor.middleName" /><jstl:out value="${actor.middleName}"></jstl:out></td></tr>
								<tr><td><spring:message code="actor.email" /><jstl:out value="${actor.email}"></jstl:out></td></tr>
								<tr><td><spring:message code="actor.photo" /><jstl:out value="${actor.photo}"></jstl:out></td></tr>
								<tr><td><spring:message code="actor.username" /><jstl:out value="${actor.userAccount.username}"></jstl:out></td></tr>
								<tr><td><spring:message code="actor.password" /><jstl:out value="${actor.userAccount.password}"></jstl:out></td></tr>
								<tr><td><spring:message code="actor.ban" /><jstl:out value="${actor.isBanned}"></jstl:out></td></tr>
								<tr><td>
									<p><spring:message code="actor.socialProfiles" /></p>
									<display:table name="socialProfiles" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag">
										<display:column property="nick" titleKey="actor.name" ></display:column>
										<display:column property="link" titleKey="actor.address"></display:column>
										<display:column property="name" titleKey="actor.surname" ></display:column>
									</display:table>
								<tr><td>
					</table>
			</div>


				<form>
					<input type="button" value=<spring:message code="back" /> name="back" onclick="history.back()" />
				</form>
