
<%@ page import="com.fiuba.Evaluacion" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'evaluacion.label', default: 'evaluacion')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-evaluacion" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}">
					<g:message code="default.home.label"/></a></li>
		
					<li><g:link class="list" controller="curso" action="aprendiz" params="['cursoId': params.cursoId]">
						<g:message code="Menu aprendiz  del curso ${com.fiuba.Curso.get(params.cursoId)}" args="[entityName]" /></g:link></li>
		
				<li><g:link class="list" controller="curso" action="evaluaciones" params="['cursoId': params.cursoId]">
						<g:message code="Evaluaciones del curso ${com.fiuba.Curso.get(params.cursoId)}" args="[entityName]" /></g:link></li>
			</ul>
		</div>
			<h2>Params: ${params}</h2>
		<h2>Curso: ${com.fiuba.Curso.get(params.cursoId)}</h2>
		<h2>Curso Id: ${params.cursoId}</h2>
		<div>
			<h1><g:message code="Evaluacion: ${evaluacion}" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
		</div>
		<div>
			<g:if test="${evaluacionAprendiz}">
				<div>La nota del aprendiz ${evaluacionAprendiz.aprendiz} es: PONER NOTA</div>
			</g:if>
			<g:else>
				<fieldset class="buttons">
					<g:link class="edit" action="inscribirme" id="${evaluacion.id}" params="['cursoId': params.cursoId]">
						<g:message code="Inscribirme" /></g:link>
				</fieldset>
			</g:else>
		</div>
	</body>
</html>
