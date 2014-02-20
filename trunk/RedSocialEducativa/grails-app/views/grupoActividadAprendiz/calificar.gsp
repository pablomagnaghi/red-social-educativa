<%@ page import="com.fiuba.GrupoActividadAprendiz" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'grupoActividadAprendiz.label', default: 'GrupoActividadAprendiz')}" />
		<title><g:message code="default.edit.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#edit-grupoActividadAprendiz" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
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
		<h2>GRupo Actividad Id: ${grupoActividadAprendizInstance.grupo.id}</h2>
		<h2>Aprendiz Id: ${grupoActividadAprendizInstance.aprendiz.id}</h2>
		<h2>GRupo Actividad aprendiz Id: ${params.id}</h2>
		<div id="edit-grupoActividadAprendiz" class="content scaffold-edit" role="main">
			<h1><g:message code="default.edit.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:hasErrors bean="${grupoActividadAprendizInstance}">
			<ul class="errors" role="alert">
				<g:eachError bean="${grupoActividadAprendizInstance}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>
			<g:form action="guardarCalificacion" id="${params.id}" params="['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 
					'actividadId': params.actividadId, 'grupoActividadId': params.grupoActividadId]">
				<g:hiddenField name="version" value="${grupoActividadAprendizInstance?.version}" />
				<div class="fieldcontain ${hasErrors(bean: grupoActividadAprendizInstance, field: 'nota', 'error')} ">
					<label for="nota">
						<g:message code="grupoActividadAprendiz.nota.label" default="Nota" />
					</label>
					<g:field name="nota" type="number" min="1" value="${grupoActividadAprendizInstance.nota}"/>
				</div>
				<div><g:hiddenField name="grupo.id" value="${grupoActividadAprendizInstance.grupo.id}"/></div>
				<div><g:hiddenField name="aprendiz.id" value="${grupoActividadAprendizInstance.aprendiz.id}"/></div>	
				<fieldset class="buttons">
					<g:actionSubmit class="save" action="guardarCalificacion" value="${message(code: 'default.button.update.label', default: 'Update')}" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
