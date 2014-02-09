<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'noticiaCurso.label', default: 'NoticiaCurso')}" />
		<title><g:message code="default.create.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#create-noticiaCurso" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		
		<h2>curso id : ${cursoId}</h2>
		<h2>params curso id: ${params.cursoId}</h2>
		
		<h2>cuatri id : ${cuatrimestreId}</h2>
		<h2>params cuatri id: ${params.cuatrimestreId}</h2>
		
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}">
					<g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="menuMediador" controller="curso" params="['cursoId': params.cursoId]">
					<g:message code="Tareas mediador" /></g:link></li>
				<li><g:link class="list" action="index" params="['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId]">
					<g:message code="default.list.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="create-noticiaCurso" class="content scaffold-create" role="main">
			<h1><g:message code="default.create.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:hasErrors bean="${noticiaCursoInstance}">
			<ul class="errors" role="alert">
				<g:eachError bean="${noticiaCursoInstance}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>
			<g:form action="save" params="['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId]">
				<fieldset class="form">
					<g:render template="form"/>
						<div class="fieldcontain ${hasErrors(bean: noticiaCursoInstance, field: 'titulo', 'error')} ">
							<label for="titulo">
								<g:message code="noticiaCurso.titulo.label" default="Titulo" />
		
							</label>
							<g:textField name="titulo" value="${noticiaCursoInstance?.titulo}"/>
						</div>
						<div> <g:hiddenField name="mediador.id" value="${mediadorId}"/></div>
				</fieldset>
				<fieldset class="buttons">
					<g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
