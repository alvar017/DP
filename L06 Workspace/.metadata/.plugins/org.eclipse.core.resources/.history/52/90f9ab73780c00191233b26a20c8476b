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
								<tr><td><spring:message code="curriculum.ticker" /><jstl:out value="${curriculum.ticker}"></jstl:out></td></tr>
								<tr><td>
									<p><spring:message code="curriculum.misrec" />
									<p><input type="button" value=<spring:message code="curriculum.createMisrec" /> id="buttoncreateMisrec" name="buttoncreateMisrec"  onclick="location.href='miscellaneousRecord/handyWorker/create.do';"/></p>
									<display:table name="curriculum.misrec" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag">
										<display:column titleKey="button.edit"> 
											<a href="miscellaneousRecord/handyWorker/edit.do?miscellaneousRecordId=${row.id}"><spring:message code="button.edit" /></a>
										</display:column>
										<display:column titleKey="delete"> 
											<a href="miscellaneousRecord/handyWorker/delete.do?miscellaneousRecordId=${row.id}"><spring:message code="delete" /></a>
										</display:column>										
										<display:column property="title" titleKey="curriculum.title" ></display:column>
										<display:column property="link" titleKey="curriculum.link"></display:column>
										<display:column property="comments" titleKey="curriculum.comments" ></display:column>
									</display:table>
								</td></tr>
								<tr><td>
									<p><spring:message code="curriculum.endrec" /></p>
									<p><input type="button" value=<spring:message code="curriculum.createEndrec" /> id="buttoncreateEndrec" name="buttoncreateEndrec"  onclick="location.href='endorserRecord/handyWorker/create.do';"/></p>
									<display:table name="curriculum.endrec" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag">
										<display:column titleKey="button.edit"> 
											<a href="endorserRecord/handyWorker/edit.do?endorserRecordId=${row.id}"><spring:message code="button.edit" /></a>
										</display:column>
										<display:column titleKey="delete"> 
											<a href="endorserRecord/handyWorker/delete.do?endorserRecordId=${row.id}"><spring:message code="delete" /></a>
										</display:column>										
										<display:column property="title" titleKey="curriculum.title" ></display:column>
										<display:column property="email" titleKey="handyWorker.email"></display:column>
										<display:column property="phone" titleKey="handyworker.phone" ></display:column>
										<display:column property="linkedIn" titleKey="curriculum.linkedIn"></display:column>
										<display:column property="comments" titleKey="curriculum.comments" ></display:column>
									</display:table>
								</td></tr>
								<tr><td>
									<p><spring:message code="curriculum.perrec" /></p>
									<p><input type="button" value=<spring:message code="curriculum.createPerrec" /> id="buttoncreatePerrec" name="buttoncreatePerrec"  onclick="location.href='personalRecord/handyWorker/create.do';"/></p>
									<display:table name="curriculum.perrec" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag">
										<display:column titleKey="button.edit"> 
											<a href="personalRecord/handyWorker/edit.do?personalRecordId=${row.id}"><spring:message code="button.edit" /></a>
										</display:column>
										<display:column titleKey="delete"> 
											<a href="personalRecord/handyWorker/delete.do?personalRecordId=${row.id}"><spring:message code="delete" /></a>
										</display:column>										
										<display:column property="name" titleKey="handyWorker.name"></display:column>
										<display:column property="photo" titleKey="curriculum.photo" ></display:column>
										<display:column property="phone" titleKey="handyworker.phone" ></display:column>
										<display:column property="linkedIn" titleKey="curriculum.linkedIn"></display:column>
									</display:table>
								</td></tr>
								<tr><td>
									<p><spring:message code="curriculum.prorec" /></p>
									<p><input type="button" value=<spring:message code="curriculum.createProrec" /> id="buttoncreateProrec" name="buttoncreateProrec"  onclick="location.href='professionalRecord/handyWorker/create.do';"/></p>
									<display:table name="curriculum.prorec" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag">
										<display:column titleKey="button.edit"> 
											<a href="professionalRecord/handyWorker/edit.do?professionalRecordId=${row.id}"><spring:message code="button.edit" /></a>
										</display:column>
										<display:column titleKey="delete"> 
											<a href="professionalRecord/handyWorker/delete.do?professionalRecordId=${row.id}"><spring:message code="delete" /></a>
										</display:column>										
										<display:column property="company" titleKey="curriculum.company"></display:column>
										<display:column property="role" titleKey="curriculum.role" ></display:column>
										<display:column property="link" titleKey="curriculum.link"></display:column>
										<display:column property="comments" titleKey="curriculum.comments" ></display:column>
										<display:column property="start" titleKey="curriculum.start" ></display:column>										
										<display:column property="end" titleKey="curriculum.end" ></display:column>
									</display:table>
								</td></tr>
								<tr><td>
									<p><spring:message code="curriculum.edurec" /></p>
									<p><input type="button" value=<spring:message code="curriculum.createEdurec" /> id="buttoncreateEdurec" name="buttoncreateEdurec"  onclick="location.href='educationalRecord/handyWorker/create.do';"/></p>
									<display:table name="curriculum.edurec" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag">
										<display:column titleKey="button.edit"> 
											<a href="educationalRecord/handyWorker/edit.do?educationalRecordId=${row.id}"><spring:message code="button.edit" /></a>
										</display:column>
										<display:column titleKey="delete"> 
											<a href="educationalRecord/handyWorker/delete.do?educationalRecordId=${row.id}"><spring:message code="delete" /></a>
										</display:column>										
										<display:column property="diploma" titleKey="curriculum.diploma"></display:column>
										<display:column property="awardedBy" titleKey="curriculum.awardedBy" ></display:column>
										<display:column property="link" titleKey="curriculum.link"></display:column>
										<display:column property="comments" titleKey="curriculum.comments" ></display:column>
										<display:column property="start" titleKey="curriculum.start" ></display:column>										
										<display:column property="end" titleKey="curriculum.end" ></display:column>
									</display:table>
								</td></tr>
					</table>
			</div>


				<form>
					<input type="button" value=<spring:message code="back" /> name="back" onclick="history.back()" />
				</form>
