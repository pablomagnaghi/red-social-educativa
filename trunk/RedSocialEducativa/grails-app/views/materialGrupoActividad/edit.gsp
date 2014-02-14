<%@ page import="com.fiuba.MaterialGrupoActividad" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'materialGrupoActividad.label', default: 'MaterialGrupoActividad')}" />
		<title><g:message code="default.edit.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#edit-materialGrupoActividad" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}">
					<g:message code="default.home.label"/></a></li>
				<li><g:link controller="grupoActividad" action="menuAprendiz" params="['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 'actividadId': params.actividadId]">
					<g:message code="Lista de grupos del curso ${com.fiuba.Curso.get(params.cursoId)}" args="[entityName]" /></g:link></li>
				<li><g:link controller="grupoActividad" action="muestraAprendiz" id="${params.grupoActividadId}" 
					params="['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 'actividadId': params.actividadId]">
					<g:message code="Grupo: ${com.fiuba.GrupoActividad.get(params.grupoActividadId)}" args="[entityName]" /></g:link></li>
			</ul>
		</div>
					<h2>Params: ${params}</h2>
		<h2>Curso: ${com.fiuba.Curso.get(params.cursoId)}</h2>
		<h2>Curso Id: ${params.cursoId}</h2>
		<h2>Cuatrimestre Id: ${params.cuatrimestreId}</h2>
		<h2>Actividad Id: ${params.actividadId}</h2>
		<div id="edit-materialGrupoActividad" class="content scaffold-edit" role="main">
			<h1><g:message code="default.edit.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:hasErrors bean="${materialGrupoActividadInstance}">
			<ul class="errors" role="alert">
				<g:eachError bean="${materialGrupoActividadInstance}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>
			<g:form action="update" method="PUT" id="${materialGrupoActividadInstance.id}" 
				params="['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 'actividadId': params.actividadId, 'grupoActividadId': params.grupoActividadId]" >
				<g:hiddenField name="version" value="${materialGrupoActividadInstance?.version}" />
				<fieldset class="form">
					<g:render template="form"/>
						<div> <g:hiddenField name="titulo" value="${materialGrupoActividadInstance.titulo}"/></div>
						<div> <g:hiddenField name="responsable" value="${materialGrupoActividadInstance.responsable}"/></div>
				</fieldset>
				<fieldset class="buttons">
					<g:actionSubmit class="save" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
