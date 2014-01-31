
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
				<g:if test="${aprendiz}">
					<li><g:link class="list" controller="curso" action="aprendiz" params="['cursoId': cursoId]">
						<g:message code="Menu aprendiz  del curso ${com.fiuba.Curso.get(cursoId)}" args="[entityName]" /></g:link></li>
				</g:if>
				<g:else>
					<li><g:link class="list" controller="curso" action="mediador" params="['cursoId': cursoId]">
						<g:message code="Menu mediador  del curso ${com.fiuba.Curso.get(cursoId)}" args="[entityName]" /></g:link></li>
				</g:else>	
				<li><g:link class="list" controller="curso" action="actividades" params="['cursoId': cursoId]">
						<g:message code="Actividades  del curso ${com.fiuba.Curso.get(cursoId)}" args="[entityName]" /></g:link></li>
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
