<%--
 * action-2.jsp
 *
 * Copyright (C) 2018 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page import="java.util.ArrayList"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- ACTORES SUSPENDIDOS -->
<h3><i><spring:message code="ad.actor.Susp" /></i></h3>
					<tr><td>
					<div>
						<security:authorize access="hasRole('ADMIN')">
							<display:table name="actor" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag">
							
								<display:column titleKey="actor.sus" >
										<a href="administrator/editA.do?actorId=${row.id}"><spring:message code="actor.sus" /></a>
								</display:column>
								
								<display:column property="name" titleKey="actor.nameS" ></display:column>
								<display:column property="surname" titleKey="actor.surnameS" ></display:column>
								
								
								<display:column property="email" titleKey="actor.emailS" ></display:column>
								<display:column property="address" titleKey="actor.addressS"></display:column>
							</display:table>
						</security:authorize>
					</div>
					</td></tr>
		
<tr><td><i><spring:message code="ad.actor.B" /></i></td></tr>
					<tr><td>
					<div>
						<security:authorize access="hasRole('ADMIN')">
							<display:table name="actorB" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag">
							
								
								<display:column property="name" titleKey="actor.nameS" ></display:column>
								<display:column property="surname" titleKey="actor.surnameS" ></display:column>
								
								
								<display:column property="email" titleKey="actor.emailS" ></display:column>
								<display:column property="address" titleKey="actor.addressS"></display:column>
							</display:table>
						</security:authorize>
					</div>
					</td></tr>
<!--ACTORES SUSPENDIDOS-->	

<br>

<!--SPAM WORDS-->	
<h3><i><spring:message code="spamWords" /></i></h3>
<p>${spamWords}</p>

<security:authorize access="hasRole('ADMIN')">
	<form:form class="formularioEdicion" method="GET"  action="administrator/newSpamWord.do?newSpamWord='${newSpamWord}'.do">		
		<spring:message code="newSpamWord" />
		<input type=text name="newSpamWord" required="required"/>
		<input type="submit" value=<spring:message code="saveNewSpamWord"/> />
	</form:form>
</security:authorize>
<!--SPAM WORDS-->	

<br>

<!--Welcome page-->	
<h3><i><spring:message code="welcomePage" /></i></h3>
<spring:message code="welcomePageS" /><p>${spanish}</p>
<spring:message code="welcomePageE" /><p>${ingles}</p>

	<form:form class="formularioEdicion" method="GET" action="administrator/newWelcome.do?newIngles='${newIngles}'&&newSpanish='${newSpanish}'.do">		
		<spring:message code="ingles" />
		<input type=text name="newIngles" required="required"/>
		<br>
		<spring:message code="spanish" />
		<input type=text name="newSpanish" required="required"/>
		<br>
		<input type="submit" value=<spring:message code="saveNewSpamWord" /> />
	</form:form>
<!--Welcome page-->

<br>

<!-- IVA -->
<h3><i><spring:message code="IVA" /></i>${iva}%</h3>

	<form:form class="formularioEdicion" method="GET" action="administrator/newIVA.do?newIVA='${newIVA}'.do">		
		<spring:message code="ivaM" />
		<input type="number" name="newIVA" required="required"/>
		<form:errors cssClass="error" path="${newIVA}"/>
		<input type="submit" value=<spring:message code="saveNewSpamWord" /> />
	</form:form>
<!-- IVA -->

<br>

<!-- Logo -->
<h3><i><spring:message code="nameLogo" /></i>${logo}</h3>

	<form:form class="formularioEdicion" method="GET" action="administrator/newLogo.do?newLogo='${newLogo}'.do">		
		<spring:message code="logoM" />
		<input type="url" name="newLogo" required="required"/>
		<form:errors cssClass="error" path="${newLogo}"/><br> 
		<input type="submit" value=<spring:message code="saveNewSpamWord" /> />
	</form:form>
<!-- Logo -->

<br>

<!-- Name of system -->
<h3><i><spring:message code="nameSistem" /></i>${system}</h3>

	<form:form class="formularioEdicion" method="GET" action="administrator/newSystem.do?newSystem='${newSystem}'.do">		
		<spring:message code="systemM" />
		<input type="text" name="newSystem" required="required"/>
		<input type="submit" value=<spring:message code="saveNewSpamWord" /> />
	</form:form>
<!-- Name of system -->

<br>

<!-- Credit card -->
<h3><i><spring:message code="creditCard" /></i></h3>
<p>${brand}</p>

<form:form class="formularioEdicion" method="GET" action="administrator/newBrand.do?newBrand='${newBrand}'.do">		
		<spring:message code="cardM" />
		<input type="text" name="newBrand" required="required"/>
		<input type="submit" value=<spring:message code="saveNewSpamWord" /> />
</form:form>
<!-- Credit card -->

<br>

<!-- TIME -->
<h3><i><spring:message code="time" /></i>${time}h</h3>

	<form:form class="formularioEdicion" method="GET" action="administrator/newTime.do?newTime='${newTime}'.do">		
		<spring:message code="timeM" />
		<input type="number" name="newTime" required="required"/>
		<form:errors cssClass="error" path="${newTime}"/><br> 
		<input type="submit" value=<spring:message code="saveNewSpamWord" /> />
	</form:form>
<!-- TIME -->				
				
<br>

<!-- RESULT -->
<h3><i><spring:message code="result" /></i>${resultF}</h3>

	<form:form class="formularioEdicion" method="GET"  action="administrator/newResult.do?newResult='${newResult}'.do">		
		<spring:message code="resultM" />
		<input type="number" name="newResult" required="required"/>
		<form:errors cssClass="error" path="${newResult}"/><br> 
		<input type="submit" value=<spring:message code="saveNewSpamWord" /> />
	</form:form>
<!-- RESULT -->	

<br>

<!-- PHONE -->
<h3><i><spring:message code="phone" /></i>+${phone}</h3>
	<form:form class="formularioEdicion" method="GET"  action="administrator/newPhone.do?newPhone='${newPhone}'.do">		
		<spring:message code="phoneM" />
		<input type="number" name="newPhone" required="required"/>
		<input type="submit" value=<spring:message code="saveNewSpamWord" /> />
	</form:form>
<!-- PHONE -->						
					
			<!-- 		<a href="welcome/edit.do"><spring:message code="ad.welcom" /></a>
					<br>
					<a href="administrator/editIVA.do"><spring:message code="ad.welcom" /></a>  -->
<!-- 					
<form:form action="welcome/edit?es=$request->get('esp').do" >					
<spring:message code="ad.welcom" /> 
<input type="text" name="esp" />
<input type="submit" name="save" />
</form:form>



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
 -->
 
 <!-- PHONE -->
<h3><i><spring:message code="phoneCountry" /></i>${phoneCountry}</h3>
	<form:form class="formularioEdicion" method="GET"  action="administrator/newPhoneCountry.do?newPhoneCountry='${newPhoneCountry}'.do">		
		<spring:message code="phoneCountryM" />
		<input type="text" name="newPhoneCountry" required="required"/>
		<input type="submit" value=<spring:message code="saveNewSpamWord" /> />
</form:form>
<!-- PHONE -->
