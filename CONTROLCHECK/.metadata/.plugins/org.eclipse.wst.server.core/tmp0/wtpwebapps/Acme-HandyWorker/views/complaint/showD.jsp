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

<table>
	<tr>
		<th><spring:message code="administrator.indicator"/></th>
		<th><spring:message code="administrator.value"/></th>	
	</tr>
	
	<tr>
		<td><spring:message code="administrator.min.complaint.fx"/></td>
		<td><jstl:out value="${statistics.get('min.complaint.fx')}"/></td>	
	</tr>
	
	<tr>
		<td><spring:message code="administrator.max.complaint.fx"/></td>
		<td><jstl:out value="${statistics.get('max.complaint.fx')}"/></td>	
	</tr>
	
	<tr>
		<td><spring:message code="administrator.av.complaint.fx"/></td>
		<td><jstl:out value="${statistics.get('av.complaint.fx')}"/></td>	
	</tr>
	
	<tr>
		<td><spring:message code="administrator.sd.complaint.fx"/></td>
		<td><jstl:out value="${statistics.get('sd.complaint.fx')}"/></td>	
	</tr>
		
</table>


<a href="note/administrator/showD.do"><input type="button" value="<spring:message code='button.next'></spring:message>"></a>


		<c:choose>
    		<c:when test="${language=='English'}">
        		<form>
      				<input type="button" value="Back" name="volver atr�s2" onclick="history.back()" />
	  			</form> 
    		</c:when>    
    		<c:otherwise>
		 		<form>
      				<input type="button" value="Volver" name="volver atr�s2" onclick="history.back()" />
	  			</form>        		
    		</c:otherwise>
		</c:choose>