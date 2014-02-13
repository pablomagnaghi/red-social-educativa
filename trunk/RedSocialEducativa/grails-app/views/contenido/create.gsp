<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'contenido.label', default: 'Contenido')}" />
		<title><g:message code="default.create.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#create-contenido" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}">
					<g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="mediador" controller="curso" params="['cursoId': params.cursoId]">
					<g:message code="Pagina principal de mediador"/></g:link></li>			
				<li><g:link class="create" action="menuMediador" controller="curso" params="['cursoId': params.cursoId]">
					<g:message code="Tareas mediador" /></g:link></li>	
				<li><g:link controller="tema" action="index" id="${temaId}" params="['cursoId': params.cursoId]">
					<g:message code="Lista de temas del curso ${com.fiuba.Curso.get(params.cursoId)}" args="[entityName]" /></g:link></li>
				<li><g:link controller="tema" action="edit" id="${params.temaId}" params="['cursoId': params.cursoId]">
					<g:message code="Tema: ${com.fiuba.Tema.get(params.temaId)}" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<h2>cursoId: ${params.cursoId}</h2>
		<h2>temaId: ${params.temaId}</h2>
		<div id="create-contenido" class="content scaffold-create" role="main">
			<h1><g:message code="default.create.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:hasErrors bean="${contenidoInstance}">
			<ul class="errors" role="alert">
				<g:eachError bean="${contenidoInstance}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>
			<g:form action="save" params="['cursoId': params.cursoId, 'temaId': params.temaId]" >
				<fieldset class="form">
					<g:render template="form"/>
						<div class="fieldcontain ${hasErrors(bean: contenidoInstance, field: 'titulo', 'error')} ">
							<label for="titulo">
								<g:message code="contenido.titulo.label" default="Titulo" />
							</label>
							<g:textField name="titulo" value="${contenidoInstance?.titulo}"/>
						</div>
				</fieldset>
				<fieldset class="buttons">
					<g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
