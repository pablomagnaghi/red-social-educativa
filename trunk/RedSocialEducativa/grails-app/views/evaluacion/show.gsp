
<%@ page import="com.fiuba.Evaluacion" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'evaluacion.label', default: 'Evaluacion')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-evaluacion" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}">
					<g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index" params="['cursoId': cursoId]">
					<g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create" params="['cursoId': cursoId]">
					<g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-evaluacion" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list evaluacion">
			
				<g:if test="${evaluacionInstance?.aprendices}">
				<li class="fieldcontain">
					<span id="aprendices-label" class="property-label"><g:message code="evaluacion.aprendices.label" default="Aprendices" /></span>
					
						<g:each in="${evaluacionInstance.aprendices}" var="a">
						<span class="property-value" aria-labelledby="aprendices-label"><g:link controller="evaluacionAprendiz" action="show" id="${a.id}">${a?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${evaluacionInstance?.aula}">
				<li class="fieldcontain">
					<span id="aula-label" class="property-label"><g:message code="evaluacion.aula.label" default="Aula" /></span>
					
						<span class="property-value" aria-labelledby="aula-label"><g:fieldValue bean="${evaluacionInstance}" field="aula"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${evaluacionInstance?.descripcion}">
				<li class="fieldcontain">
					<span id="descripcion-label" class="property-label"><g:message code="evaluacion.descripcion.label" default="Descripcion" /></span>
					
						<span class="property-value" aria-labelledby="descripcion-label"><g:fieldValue bean="${evaluacionInstance}" field="descripcion"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${evaluacionInstance?.fecha}">
				<li class="fieldcontain">
					<span id="fecha-label" class="property-label"><g:message code="evaluacion.fecha.label" default="Fecha" /></span>
					
						<span class="property-value" aria-labelledby="fecha-label"><g:fieldValue bean="${evaluacionInstance}" field="fecha"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${evaluacionInstance?.habilitada}">
				<li class="fieldcontain">
					<span id="habilitada-label" class="property-label"><g:message code="evaluacion.habilitada.label" default="Habilitada" /></span>
					
						<span class="property-value" aria-labelledby="habilitada-label"><g:formatBoolean boolean="${evaluacionInstance?.habilitada}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${evaluacionInstance?.horario}">
				<li class="fieldcontain">
					<span id="horario-label" class="property-label"><g:message code="evaluacion.horario.label" default="Horario" /></span>
					
						<span class="property-value" aria-labelledby="horario-label"><g:fieldValue bean="${evaluacionInstance}" field="horario"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${evaluacionInstance?.obligatoria}">
				<li class="fieldcontain">
					<span id="obligatoria-label" class="property-label"><g:message code="evaluacion.obligatoria.label" default="Obligatoria" /></span>
					
						<span class="property-value" aria-labelledby="obligatoria-label"><g:formatBoolean boolean="${evaluacionInstance?.obligatoria}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${evaluacionInstance?.parcial}">
				<li class="fieldcontain">
					<span id="parcial-label" class="property-label"><g:message code="evaluacion.parcial.label" default="Parcial" /></span>
					
						<span class="property-value" aria-labelledby="parcial-label"><g:formatBoolean boolean="${evaluacionInstance?.parcial}" /></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form action="delete" method="DELETE" id="${evaluacionInstance.id}" params="['cursoId': cursoId]">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${evaluacionInstance}" 
						id="${evaluacionInstance.id}" params="['cursoId': cursoId]">
						<g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" 
						onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>			
		</div>
	</body>
</html>
