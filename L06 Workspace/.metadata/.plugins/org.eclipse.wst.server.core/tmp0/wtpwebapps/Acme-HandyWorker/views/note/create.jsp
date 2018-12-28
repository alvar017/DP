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
				<spring:message code="handyWorker.create" />
			</h2>
		</header>

		<div class="content">
			<img class="lupa" src="images/edit.png" alt="Edit" width="19%" />
			<form:form class="formularioEdicion" method="POST"
				modelAttribute="note" action="note/handyWorker/create.do">

				<form:hidden path="id" />
				<form:hidden path="commentCustomer" />		
				<form:hidden path="commentReferee" />	
				<form:hidden path="customer" />	
				<form:hidden path="report" />	
				<form:hidden path="handyWorker" />
				<form:hidden path="moment" />	
	
				<form:label path="commentHandyWorker"><spring:message code="handyWorker.showing.commentHandyWorker" /></form:label>
				<form:textarea path="commentHandyWorker" required="required"/>			

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
			<input type="button" value="Back" name="volver atrás2"
				onclick="history.back()" />
		</form>
	</c:when>
	<c:otherwise>
		<form>
			<input type="button" value="Volver" name="volver atrás2"
				onclick="history.back()" />
		</form>
	</c:otherwise>
</c:choose>