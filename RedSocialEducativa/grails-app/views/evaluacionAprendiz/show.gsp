
<%@ page import="com.fiuba.EvaluacionAprendiz" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'evaluacionAprendiz.label', default: 'EvaluacionAprendiz')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-evaluacionAprendiz" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
			<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link controller="evaluacion" action="index" id="${params.evaluacionId}" params="['cursoId': params.cursoId]">
					<g:message code="Lista de evaluaciones del curso ${com.fiuba.Curso.get(params.cursoId)}" args="[entityName]" /></g:link></li>
				<li><g:link controller="evaluacion" action="show" id="${params.evaluacionId}" params="['cursoId': params.cursoId]">
					<g:message code="evaluacion: ${com.fiuba.Evaluacion.get(params.evaluacionId)}" args="[entityName]" /></g:link></li>
			</ul>
		</div>
				<h3>curso: ${params.cursoId}</h3>
		<h3>evaluacion: ${params.evaluacionId}</h3>
		<div id="show-evaluacionAprendiz" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list evaluacionAprendiz">
			
				<g:if test="${evaluacionAprendizInstance?.nota}">
				<li class="fieldcontain">
					<span id="nota-label" class="property-label"><g:message code="evaluacionAprendiz.nota.label" default="Nota" /></span>
					
						<span class="property-value" aria-labelledby="nota-label"><g:fieldValue bean="${evaluacionAprendizInstance}" field="nota"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${evaluacionAprendizInstance?.aprendiz}">
				<li class="fieldcontain">
					<span id="aprendiz-label" class="property-label"><g:message code="evaluacionAprendiz.aprendiz.label" default="Aprendiz" /></span>
					
						<span class="property-value" aria-labelledby="aprendiz-label"><g:link controller="aprendiz" action="show" id="${evaluacionAprendizInstance?.aprendiz?.id}">${evaluacionAprendizInstance?.aprendiz?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${evaluacionAprendizInstance?.evaluacion}">
				<li class="fieldcontain">
					<span id="evaluacion-label" class="property-label"><g:message code="evaluacionAprendiz.evaluacion.label" default="Evaluacion" /></span>
					
						<span class="property-value" aria-labelledby="evaluacion-label"><g:link controller="evaluacion" action="show" id="${evaluacionAprendizInstance?.evaluacion?.id}">${evaluacionAprendizInstance?.evaluacion?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form action="delete" method="DELETE" id="${evaluacionAprendizInstance.id}" params="['cursoId': params.cursoId, 
					'evaluacionId': params.evaluacionId]">
				<fieldset class="buttons">
					<g:link class="edit" action="calificar" resource="${evaluacionAprendizInstance}"  id="${evaluacionAprendizInstance.id}" 
						params="['cursoId': params.cursoId, 'evaluacionId': params.evaluacionId]">
						<g:message code="Calificar" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>	
		</div>
	</body>
</html>
