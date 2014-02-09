<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'actividad.label', default: 'Actividad')}" />
		<title><g:message code="default.create.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#create-actividad" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}">
					<g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="menuMediador" controller="curso" params="['cursoId': cursoId]">
					<g:message code="Tareas mediador" /></g:link></li>
				<li><g:link class="create" action="create" params="['cursoId': cursoId, 'cuatrimestreId': cuatrimestreId]">
					<g:message code="default.new.label" args="[entityName]" /></g:link></li>	
			</ul>
		</div>
		<div id="create-actividad" class="content scaffold-create" role="main">
			<h1><g:message code="default.create.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:hasErrors bean="${actividadInstance}">
			<ul class="errors" role="alert">
				<g:eachError bean="${actividadInstance}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>
			<g:form action="save" params="['cursoId': cursoId, 'cuatrimestreId': params.cuatrimestreId]">
				<fieldset class="form">
					<g:render template="form"/>
						<div class="fieldcontain ${hasErrors(bean: actividadInstance, field: 'titulo', 'error')} ">
							<label for="titulo">
								<g:message code="actividad.titulo.label" default="Titulo" />
							</label>
							<g:textField name="titulo" value="${actividadInstance?.titulo}"/>
						</div>
				</fieldset>
				<fieldset class="buttons">
					<g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
