<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'materialActividad.label', default: 'MaterialActividad')}" />
		<title><g:message code="default.create.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#create-materialActividad" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}">
					<g:message code="default.home.label"/></a></li>
				<li><g:link controller="actividad" action="index" id="${actividadId}" params="['cursoId': cursoId, 'cuatrimestreId': cuatrimestreId]">
					<g:message code="Lista de actividades del curso ${com.fiuba.Curso.get(cursoId)}" args="[entityName]" /></g:link></li>
				<li><g:link controller="actividad" action="show" id="${actividadId}" params="['cursoId': cursoId, 'cuatrimestreId': cuatrimestreId, 'actividadId': actividadId]">
					<g:message code="Actividad: ${com.fiuba.Actividad.get(actividadId)}" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="create-materialActividad" class="content scaffold-create" role="main">
			<h1><g:message code="default.create.label" args="[entityName]" /></h1>
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
			<g:form action="save" params="['cursoId': params.cursoId, 'cuatrimestreId': cuatrimestreId, 'actividadId': params.actividadId]" >
				<fieldset class="form">
					<g:render template="form"/>
						<div
							class="fieldcontain ${hasErrors(bean: materialActividadInstance, field: 'titulo', 'error')} ">
							<label for="titulo"> <g:message
									code="materialActividad.titulo.label" default="Titulo" />
							</label>
							<g:textField name="titulo"
								value="${materialActividadInstance?.titulo}" />
						</div>
						<div> <g:hiddenField name="responsable" value="${mediador}-${mediador?.jerarquia}"/></div>
				</fieldset>
				<fieldset class="buttons">
					<g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>