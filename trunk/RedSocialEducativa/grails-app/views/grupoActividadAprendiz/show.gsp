
<%@ page import="com.fiuba.GrupoActividadAprendiz" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'grupoActividadAprendiz.label', default: 'GrupoActividadAprendiz')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-grupoActividadAprendiz" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}">
					<g:message code="default.home.label"/></a></li>
				<li><g:link class="create" controller="curso" action="menuMediador" params="['cursoId': params.cursoId]">
					<g:message code="Tareas mediador" args="[entityName]" /></g:link></li>
				<li><g:link class="list" controller="grupoActividad" action="menuMed" params="['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 'actividadId': params.actividadId]">
					<g:message code="Lista de grupos" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<h2>Params: ${params}</h2>
		<h2>Curso: ${com.fiuba.Curso.get(params.cursoId)}</h2>
		<h2>Curso Id: ${params.cursoId}</h2>
		<h2>Cuatrimestre Id: ${params.cuatrimestreId}</h2>
		<h2>Actividad Id: ${params.actividadId}</h2>
			<h2>GRupo Actividad Id: ${params.grupoActividadId}</h2>
		<div id="show-grupoActividadAprendiz" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list grupoActividadAprendiz">
			
				<g:if test="${grupoActividadAprendizInstance?.nota}">
				<li class="fieldcontain">
					<span id="nota-label" class="property-label"><g:message code="grupoActividadAprendiz.nota.label" default="Nota" /></span>
					
						<span class="property-value" aria-labelledby="nota-label"><g:fieldValue bean="${grupoActividadAprendizInstance}" field="nota"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${grupoActividadAprendizInstance?.aprendiz}">
				<li class="fieldcontain">
					<span id="aprendiz-label" class="property-label"><g:message code="grupoActividadAprendiz.aprendiz.label" default="Aprendiz" /></span>
					
						<span class="property-value" aria-labelledby="aprendiz-label"><g:link controller="aprendiz" action="show" id="${grupoActividadAprendizInstance?.aprendiz?.id}">${grupoActividadAprendizInstance?.aprendiz?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${grupoActividadAprendizInstance?.grupo}">
				<li class="fieldcontain">
					<span id="grupo-label" class="property-label"><g:message code="grupoActividadAprendiz.grupo.label" default="Grupo" /></span>
					
						<span class="property-value" aria-labelledby="grupo-label"><g:link controller="grupoActividad" action="show" id="${grupoActividadAprendizInstance?.grupo?.id}">${grupoActividadAprendizInstance?.grupo?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
		</div>
	</body>
</html>
