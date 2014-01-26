<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'curso.label', default: 'Curso')}" />
		<title><g:message code="default.create.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#create-curso" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}">
					<g:message code="default.home.label"/></a></li>
				<li><g:link class="list" controller="administrador" action="general">
					<g:message code="Tareas administrativas"/></g:link></li>
				<li><g:link class="list" action="index">
					<g:message code="default.list.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="create-curso" class="content scaffold-create" role="main">
			<h1><g:message code="default.create.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:hasErrors bean="${cursoInstance}">
			<ul class="errors" role="alert">
				<g:eachError bean="${cursoInstance}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>
			<g:form action="save" >
				<fieldset class="form">
					<g:render template="form"/>
					<div class="fieldcontain ${hasErrors(bean: cursoInstance, field: 'nroRelativo', 'error')} required">
						<label for="nroRelativo">
							<g:message code="curso.nroRelativo.label" default="Nro Relativo" />
							<span class="required-indicator">*</span>
						</label>
						<g:field name="nroRelativo" type="number" value="${cursoInstance.nroRelativo}" required=""/>
					</div>
					<div class="fieldcontain ${hasErrors(bean: cursoInstance, field: 'materia', 'error')} required">
						<label for="materia">
							<g:message code="curso.materia.label" default="Materia" />
							<span class="required-indicator">*</span>
						</label>
						<g:select id="materia" name="materia.id" from="${com.fiuba.Materia.list()}" optionKey="id" required="" value="${cursoInstance?.materia?.id}" class="many-to-one"/>
					</div>
				</fieldset>
				<fieldset class="buttons">
					<g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" />
				</fieldset>
			</g:form>
		</div>
		
		
	</body>
</html>
