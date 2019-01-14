<%--
 * action-1.jsp
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

<p><spring:message code="administrator.listCategories"/></p>
<body>
  
   <display:table name="categories" id="row"  requestURI="${requestURI}"	pagesize="5" class="displaytag" >
  	
  	<display:column titleKey="administrator.showCategory"> 
		<a href="category/administrator/show.do?categoryId=${row.id}"><spring:message code="administrator.showCategory" /></a>
	</display:column>
  	<display:column titleKey="administrator.editCategory"> 
		<a href="category/administrator/edit.do?categoryId=${row.id}"><spring:message code="administrator.editCategory" /></a>
	</display:column>
  	
  	
  	<c:choose>
    	<c:when test="${language=='English'}">
			<display:column property="nameEN" titleKey="category.nameEN"/>    	</c:when>    
    	<c:otherwise>
  			<display:column property="nameES" titleKey="category.nameES"/>
    	</c:otherwise>
	</c:choose>
  
  	<display:column property="subCategories" titleKey="category.subcategories"/>
  	
  	</display:table>
  	
	<p class="create"><input type="button" value=<spring:message code="administrator.createCategory" /> id="buttonCategory" name="buttonCategory"  onclick="location.href='category/administrator/create.do';"/></p>
  		
			<form method="get" action=" ">
				<button type="submit">
					<spring:message code="button.back" />
				</button>
			</form>
		
</body>