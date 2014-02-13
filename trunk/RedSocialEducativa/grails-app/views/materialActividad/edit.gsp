<%@ page import="com.fiuba.MaterialActividad" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'materialActividad.label', default: 'MaterialActividad')}" />
		<title><g:message code="default.edit.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#edit-materialActividad" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
			
				<li><a class="home" href="${createLink(uri: '/')}">
					<g:message code="default.home.label"/></a></li>
				<li><g:link controller="actividad" action="index" params="['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId]">
					<g:message code="Lista de actividades del curso ${com.fiuba.Curso.get(params.cursoId)}" args="[entityName]" /></g:link></li>
				<li><g:link controller="actividad" action="show" id="${params.actividadId}" 
						params="['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId]">
					<g:message code="Actividad: ${com.fiuba.Actividad.get(params.actividadId)}" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create" params="['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 
						'actividadId': params.actividadId]">
					<g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<h3>curso: ${params.cursoId}</h3>
		<h3>cuatri: ${params.cuatrimestreId}</h3>
		<h3>act: ${params.actividadId}</h3>
		<div id="edit-materialActividad" class="content scaffold-edit" role="main">
			<h1><g:message code="default.edit.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:hasErrors bean="${materialActividadInstance}">
			<ul class="errors" role="alert">
				<g:eachError bean="${materialActividadInstance}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>
			<g:form action="update" method="PUT" id="${materialActividadInstance.id}" 
				params="['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 'actividadId': params.actividadId]" >
				<g:hiddenField name="version" value="${materialActividadInstance?.version}" />
				<fieldset class="form">
					<g:render template="form"/>
						<div><g:hiddenField name="titulo" value="${materialActividadInstance.titulo}"/></div>
						<div><g:hiddenField name="responsable" value="${materialActividadInstance.responsable}"/></div>
			</fieldset>
				<fieldset class="buttons">
					<g:actionSubmit class="save" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
