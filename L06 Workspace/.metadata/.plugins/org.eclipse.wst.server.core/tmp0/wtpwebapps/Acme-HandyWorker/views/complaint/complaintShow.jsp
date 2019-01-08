<%--
 * complaintShow.jsp
 *
 * Copyright (C) 2018 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 	TODO: check labels
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<body>
<div>
<security:authorize access="hasRole('CUSTOMER')">
	<div>
	<%-- 			<script>
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
        				<a onclick="return confirmarEN('accion.html')" title="AcmeTitle" href="complaint/customer/complaintDelete.do?id=${complaint.id}"><img src="images/delete.png" alt="Delete" width="3%"/></a> 
    				</c:when>    
    				<c:otherwise>
        				<a onclick=delete. title="AcmeTitle" href="complaint/customer/complaintDelete.do?id=${complaint.id}"><img src="images/delete.png" alt="Delete" width="3%"/></a>
    				</c:otherwise>
				</c:choose> --%>
		<a href="complaint/customer/delete.do?complaintId=${complaint.id}" onclick="return confirm('<spring:message code='delete'></spring:message>');"><input name="delete" type="button" value="<spring:message code='button.delete'></spring:message>"></a>
		<a href="complaint/customer/list.do"><input type="button" value="<spring:message code='button.back'></spring:message>"></a>
		<a href="complaint/customer/edit.do?complaintId=${complaint.id}"><input type="button" value="<spring:message code='button.edit'></spring:message>"></a>
		<br />
	</div>
	<div>
		<spring:message code="complaint.details"></spring:message>
		<display:table name="complaint" id="row" requestURI="${requestURI}" class="displaytag">
			<display:column property="moment" titleKey="complaint.moment" format="{0,date,dd/MM/yyyy HH:mm}"></display:column>
			<display:column property="referee.name" titleKey="complaint.referee"></display:column>
			<display:column property="fixUp.ticker" titleKey="complaint.fixUp"></display:column>	
		</display:table>
		<br />
	</div>
	<div>
		<spring:message code="complaint.info"></spring:message>
		<display:table name="complaint" id="row" requestURI="${requestURI}" class="displaytag">
			<display:column property="description" titleKey="complaint.description"></display:column>
			<display:column property="attachment" titleKey="complaint.attachment"></display:column>	
		</display:table>
		<br />
	</div>
		<div>
		<spring:message code="complaint.reports"></spring:message>
		<display:table name="complaint.reports" id="row" requestURI="${requestURI}" class="displaytag"> 
			<display:column property="moment" titleKey="complaint.moment"></display:column><!-- complaint.X spring messages are used bcs it would say the same that report.X -->
			<display:column property="description" titleKey="complaint.description"></display:column>
			<display:column>
				<a href="report/customer/show.do?reportId=${row.id}"><spring:message code="details" /></a>
			</display:column>
		</display:table>
		<br />
	</div>
</security:authorize>
 </div>
</body>