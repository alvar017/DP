<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<body>
<div>
<security:authorize access="hasRole('ADMIN')">
<div>
<h2><spring:message code="customers"></spring:message></h2>
<display:table name="customers" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag">
	<display:column property="name" titleKey="name" />
	<display:column property="calification" titleKey="calification" />
</display:table>
<br />
</div>
<div>
<h2><spring:message code="hws"></spring:message></h2>
<display:table name="handyWorkers" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag">
	<display:column property="name" titleKey="name" />
	<display:column property="calification" titleKey="calification" />
</display:table>
<br />
</div>
<%-- <div>
	<a href="endorsement/administrator/wordList.do"><input type="button" value="<spring:message code='words'/>"></a>
</div> --%>
</security:authorize>
 </div>
</body>