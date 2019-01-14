<%--
 * action-2.jsp
 *
 * Copyright (C) 2018 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<div class="content">
	<img class="lupa" src="images/lupa.png" alt="Luga" width="19%" />
	<table>
			<tr>
			<td><spring:message code="actor.photo" /><br>
			<img width="95"
				class="customer_photo" src="${handyWorker.photo}"
				alt=<jstl:out value="${handyWorker.photo}"></jstl:out> /></td>
		</tr>
		<tr>
			<td><spring:message code="actor.name" /> <jstl:out
					value="${handyWorker.name}"></jstl:out></td>
		</tr>
		<tr>
			<td><spring:message code="actor.middlename" /> <jstl:out
					value="${handyWorker.middleName}"></jstl:out></td>
		</tr>
		<tr>
			<td><spring:message code="actor.surname" /> <jstl:out
					value="${handyWorker.surname}"></jstl:out></td>
		</tr>
		<tr>
			<td><spring:message code="handyWorker.make" /> <jstl:out
					value="${handyWorker.make}"></jstl:out></td>
		</tr>
		<tr>
			<td><spring:message code="actor.address" /> <jstl:out
					value="${handyWorker.address}"></jstl:out></td>
		</tr>
		<tr>
			<td><spring:message code="actor.phone" /> <jstl:out
					value="${handyWorker.phone}"></jstl:out></td>
		</tr>
		<tr>
			<td><spring:message code="actor.email" /> <jstl:out
					value="${handyWorker.email}"></jstl:out></td>
		</tr>
		<tr>
			<td><spring:message code="handyWorker.username" /> <jstl:out
					value="${handyWorker.userAccount.username}"></jstl:out></td>
		</tr>
		<tr>
			<td><spring:message code="score" /> <jstl:out
					value="${handyWorker.calification}"></jstl:out></td>
		</tr>
		<tr>
			<td>
				<p>
					<spring:message code="actor.socialProfiles" />
				</p> <display:table name="socialProfiles" id="row"
					requestURI="${requestURI}" pagesize="5" class="displaytag">
					<display:column titleKey="button.edit">
						<a
							href="socialProfile/handyWorker/edit.do?socialProfileId=${row.id}"><spring:message
								code="button.edit" /></a>
					</display:column>
					<display:column titleKey="delete">
						<a
							href="socialProfile/handyWorker/delete.do?socialProfileId=${row.id}"><spring:message
								code="delete" /></a>
					</display:column>
					<display:column property="nick" titleKey="actor.nick"></display:column>
					<display:column property="link" titleKey="actor.link"></display:column>
					<display:column property="name" titleKey="actor.name"></display:column>
				</display:table>
				<p>
					<input type="button"
						value=<spring:message code="actor.createSocialProfile" />
						id="buttonSocialProfile" name="buttonSocialProfile"
						onclick="location.href='socialProfile/handyWorker/create.do';" />
				</p>
			</td>
		</tr>
		<tr>
			<td>


				<p>
					<spring:message code="hw.applications" />
				</p> <display:table name="applications" id="row"
					requestURI="${requestURI}" pagesize="5" class="displaytag">
					<display:column property="fixUp.ticker" titleKey="app.fixUp"></display:column>
					<display:column titleKey="app.state">
						<jstl:choose>
							<jstl:when test="${row.state}">
								<div id="aceptada">
									<spring:message code="application.state.accepted" />
								</div>
							</jstl:when>
							<jstl:when test="!${!row.state}">
								<div id="rechazada">
									<spring:message code="application.state.rejected" />
								</div>
							</jstl:when>
							<jstl:otherwise>
								<div id="pendiente">
									<spring:message code="application.state.pending" />
								</div>
							</jstl:otherwise>
						</jstl:choose>
					</display:column>
				</display:table>
			</td>
		</tr>
		<tr>
			<td>
				<p>
					<spring:message code="hw.fixUps" />
				</p> <display:table name="fixUps" id="row" requestURI="${requestURI}"
					pagesize="5" class="displaytag">
					<display:column property="ticker" titleKey="hw.ticker"></display:column>
					<display:column property="customer.name" titleKey="hw.customer"></display:column>
					<display:column property="moment" titleKey="fixUp.moment"
						format="{0,date,dd/MM/yyyy HH:mm}"></display:column>
				</display:table>
			</td>
		</tr>
	</table>
</div>


<form>
	<input type="button" value=<spring:message code="back" /> name="back"
		onclick="history.back()" />
</form>
