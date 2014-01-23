<%@ page import="com.fiuba.ForoGeneral" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'foroGeneral.label', default: 'ForoGeneral')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-foroGeneral" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
			</ul>
		</div>
		<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
		</g:if>
		<div>
		
			<h2>Foro general: <g:link controller="publicacionGeneral" action="nueva">(Nueva publicacion)</g:link></h2>
			<br>
			<ol>
				<g:each in="${publicaciones}">
	       				<li>
		       				<p>Publicacion: <g:link action="publicaciones" id="${it.id}">${it.titulo}</g:link>
		       			 	- Autor: ${it.responsable} - Fecha: ${it.fecha} - Hora: ${it.hora} </p>
	    				</li>
	    				<br>
				</g:each>
			</ol>
			<div class="pagination">
				<g:paginate total="${publicacionesCant ?: 0}" />
			</div>
		</div>
	</body>
</html>
