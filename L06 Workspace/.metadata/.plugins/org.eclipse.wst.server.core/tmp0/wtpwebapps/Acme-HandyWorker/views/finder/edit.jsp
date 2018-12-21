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




<section id="main-content">

	<article>
		<header>
			<h2>
				<spring:message code="handyWorker.edit" />
			</h2>
		</header>

		<div class="content">
			<img class="lupa" src="images/edit.png" alt="Edit" width="19%" />
			<form:form class="formularioEdicion" method="POST"
				modelAttribute="finder" action="finder/handyWorker/edit.do">

				<form:hidden path="id" />
				<form:hidden path="version" />				
				
				<form:label path="keyword"><spring:message code="handyWorker.showing.keyword" /></form:label>
				<form:input path="keyword" /><br>
				</br>
				
				<form:label path="category"><spring:message code="handyWorker.showing.category" /></form:label>
				<form:select path="category" >
					<form:options items="${category}" itemLabel="nameES" itemValue="id"/>
				</form:select>
				<br>
				
				<form:label path="warranty"><spring:message code="handyWorker.showing.warranty" /></form:label>
				<form:select path="warranty" >
					<form:options items="${warranty}" itemLabel="title" itemValue="id"/>
				</form:select>
				<br>			
				
							
				<form:label path="minPrice">
					<spring:message code="handyWorker.showing.minPrice" />
				</form:label>
				<form:input type="number" path="minPrice" />
				<br>

				<form:label path="maxPrice">
					<spring:message code="handyWorker.showing.maxPrice" />
				</form:label>
				<form:input type="number" path="maxPrice" />
				<br>

				<form:label path="startDate">
					<spring:message code="handyWorker.showing.startDate" />
				</form:label>
				<form:input type="date" path="startDate" />

				<form:label path="endDate">
					<spring:message code="handyWorker.showing.endDate" />
				</form:label>
				<form:input type="date" path="endDate" />

				<form:label path="date">
					<spring:message code="handyWorker.showing.date" />
				</form:label>
				<form:input type="date" path="date" />


				<input type="submit" name="save"
					value=<spring:message code="hw.send" /> />

			</form:form>
		</div>

	</article>
	<!-- /article -->

</section>
<!-- / #main-content -->

<c:choose>
	<c:when test="${language=='English'}">
		<form>
			<input type="button" value="Back" name="volver atr�s2"
				onclick="history.back()" />
		</form>
	</c:when>
	<c:otherwise>
		<form>
			<input type="button" value="Volver" name="volver atr�s2"
				onclick="history.back()" />
		</form>
	</c:otherwise>
</c:choose>