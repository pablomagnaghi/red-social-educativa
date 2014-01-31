
<%@ page import="com.fiuba.Actividad" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'actividad.label', default: 'actividad')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-actividad" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}">
					<g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="menuMediador" controller="curso" params="['cursoId': cursoId]">
					<g:message code="Tareas mediador" /></g:link></li>
			</ul>
		</div>
		<div>
			<h1><g:message code="Actividad: ${actividad.titulo}" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
		</div>
		<div>
			<p>Categoria": ${actividad.categoria}</p>	
		</div>
	</body>
</html>
