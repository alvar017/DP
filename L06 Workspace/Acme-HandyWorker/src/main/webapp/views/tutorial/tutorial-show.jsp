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
	
  
<body>
<div >
	<img id="banner" alt="" src="<jstl:out value="${requestScope.randomSponsorship.banner}"> </jstl:out>">
</div>

<div>
	<security:authorize access="hasRole('HANDYWORKER')">
	<div>
	<h2><spring:message code="tutorial.show" /></h2>
	<display:table name="tutorial" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag">
		<jstl:set var="tutorialId" value="${row.id}"/>
		<display:column property="title" titleKey="tutorial.title"/>
		<display:column property="moment" titleKey="tutorial.moment" format="{0,date,dd/MM/yyyy HH:mm}"/>
		<display:column property="summary" titleKey="tutorial.summary"/>
	</display:table>
	</div>	
	<h3><spring:message code="sponsor.show" /></h3>
		<div>
	
	<display:table list="requestScope.sponsorships" name="sponsorhips" id="row" requestURI="${requestURI }" pagesize="5" class="displaytag">
		
		<display:column property="sponsor.name" titleKey="sponsor.name"/>
		
			
	</display:table>
	</div>
	
	<%--	
		<script>
				function confirmarEN(url) {
					if(confirm('Are you sure?')) {
						window.location=url;
					} else {
						return false;
					}	
				}
			</script>
						<script>
				function confirmarES(url) {
					if(confirm('¿Estas seguro?')) {
						window.location=url;
					} else {
						return false;
					}	
				}
			</script>
			
			   <c:choose>
    				<c:when test="${language=='English'}">
        				<display:column><a onclick="return confirmarEN('accion.html')" title="AcmeTitle" href="fixUp/customer/deleteFixUpTask.do?delete=y&id=${fixUp.id}"><img src="images/delete.png" alt="Delete" width="3%"/></a></display:column> 
    				</c:when>    
    				<c:otherwise>
        				<display:column><a onclick="return confirmarES('accion.html')" title="AcmeTitle" href="fixUp/customer/deleteFixUpTask.do?delete=y&id=${fixUp.id}"><img src="images/delete.png" alt="Delete" width="3%"/></a></display:column>
    				</c:otherwise>
				</c:choose>
				
		
		<display:column> <a href="tutorial/handyWorker/edit.do?tutorialId=${row.id}"><spring:message code="tutorial.edit"/></a></display:column>
			
	</display:table> --%>
	
	<div>
	<h3><spring:message code="section.show" /></h3>
	<display:table list="requestScope.sections" name="sections" id="row" requestURI="${requestURI }" pagesize="5" class="displaytag">
		
		<display:column property="title" titleKey="section.title"/>
		<display:column property="text" titleKey="section.text"/>
		<display:column property="number" titleKey="section.number"/>
		<display:column titleKey="edit"> 
				<a href="section/handyWorker/edit.do?sectionId=${row.id}"><spring:message code="edit"/></a>
			</display:column>
			<display:column titleKey="delete"> 
				<a href="section/handyWorker/delete.do?sectionId=${row.id}" onclick="return confirm('<spring:message code='confirm.delete'></spring:message>');"><spring:message code="delete"/></a>
			</display:column>
			
	</display:table>
	<a href="section/handyWorker/create.do?tutorialId=${tutorial.id}"><spring:message code="section.create"/></a>
	</div>
	<div>
	
		<a href="tutorial/handyWorker/list.do" ><input type="button" value="<spring:message code='cancel'></spring:message>"></a>
		<a href="tutorial/handyWorker/delete.do?tutorialId=${requestScope.tutorial.id }" onclick="return confirm('<spring:message code='delete'></spring:message>');"><input type="button" value="<spring:message code='delete'></spring:message>"></a>
	</div>
	</security:authorize>
</div>
</body>