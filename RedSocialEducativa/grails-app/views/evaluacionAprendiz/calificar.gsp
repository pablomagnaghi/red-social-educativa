<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'evaluacionAprendiz.label', default: 'EvaluacionAprendiz')}" />
		<title><g:message code="default.create.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#create-evaluacionAprendiz" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
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
		<div id="create-evaluacionAprendiz" class="content scaffold-create" role="main">
			<h1><g:message code="default.create.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:hasErrors bean="${evaluacionAprendizInstance}">
			<ul class="errors" role="alert">
				<g:eachError bean="${evaluacionAprendizInstance}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>
			<g:form action="guardarCalificacion" id="${params.id}" params="['cursoId': params.cursoId, 'evaluacionId': params.evaluacionId]">
				<div class="fieldcontain ${hasErrors(bean: evaluacionAprendizInstance, field: 'nota', 'error')} ">
					<label for="nota">
						<g:message code="evaluacionAprendiz.nota.label" default="Nota" />
					</label>
					<g:field name="nota" type="number" min="0" value="${evaluacionAprendizInstance.nota}"/>
					<div><g:hiddenField name="evaluacion.id" value="${evaluacionAprendizInstance.evaluacion.id}"/></div>
					<div><g:hiddenField name="aprendiz.id" value="${evaluacionAprendizInstance.aprendiz.id}"/></div>
				</div>
				<fieldset class="buttons">
					<g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
