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

      		<div class="content">
				<img class="lupa" src="images/lupa.png" alt="Luga" width="19%"/>
				<table>
				
					<tr><td><strong><spring:message code="handyWorker.welcome.show" /></strong></td></tr>
    						
    				<tr><td><spring:message code="handyWorker.showing.attachment" /><jstl:out value="${report.attachment}"></jstl:out></td></tr>
    						   							
    				<tr><td><spring:message code="handyWorker.showing.complaint" /><jstl:out value="${report.complaint.ticker}"></jstl:out></td></tr>
    							
    				<tr><td><spring:message code="handyWorker.showing.description" /><jstl:out value="${report.description}"></jstl:out></td></tr>			
    							
    				<tr><td><spring:message code="handyWorker.showing.isFinal" /><jstl:out value="${report.isFinal}"></jstl:out></td></tr>
    				
    				 <tr><td><spring:message code="handyWorker.showing.moment" /><jstl:out value="${report.moment}"></jstl:out></td></tr>
    							    							
					<tr><td><i><spring:message code="handyWorker.showing.notes" /></i></td></tr>
  								
  						<tr><td>

    				   		<div>
								<security:authorize access="hasRole('HANDYWORKER')">
									<display:table name="notes" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag">
									
									<display:column titleKey="note.information" >
										<a href="note/handyWorker/show.do?noteId=${row.id}"><spring:message code="note.information" /></a>
									</display:column>
									
										<display:column property="moment" titleKey="note.moment"></display:column>
										<display:column property="customer" titleKey="note.customer"></display:column>
									</display:table>
								</security:authorize>
							</div>
  								
  						</td></tr>
          			</table>
      			</div>
      <a title="AcmeTitle" href="report/handyWorker/create.do"><img src="images/edit.png" alt="Create" width="3%"/></a>
      
				<form>
					<input type="button" value=<spring:message code="back" /> name="back" onclick="history.back()" />
				</form>

