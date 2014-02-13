<%@ page import="com.fiuba.Contenido" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'contenido.label', default: 'Contenido')}" />
		<title><g:message code="default.edit.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#edit-contenido" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}">
					<g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="mediador" controller="curso" params="['cursoId': params.cursoId]">
					<g:message code="Pagina principal de mediador"/></g:link></li>			
				<li><g:link class="create" action="menuMediador" controller="curso" params="['cursoId': params.cursoId]">
					<g:message code="Tareas mediador" /></g:link></li>	
				<li><g:link controller="tema" action="index" id="${params.temaId}" params="['cursoId': params.cursoId]">
					<g:message code="Lista de temas del curso ${com.fiuba.Curso.get(params.cursoId)}" args="[entityName]" /></g:link></li>
				<li><g:link controller="tema" action="edit" id="${params.temaId}" params="['cursoId': params.cursoId]">
					<g:message code="Tema: ${com.fiuba.Tema.get(params.temaId)}" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create" params="['cursoId': params.cursoId, 'temaId': params.temaId]">
					<g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
				<h2>cursoId: ${params.cursoId}</h2>
		<h2>temaId: ${params.temaId}</h2>
		<div id="edit-contenido" class="content scaffold-edit" role="main">
			<h1><g:message code="default.edit.label" args="[entityName]" /></h1>
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
		<div
			class="fieldcontain ${hasErrors(bean: contenidoInstance, field: 'materiales', 'error')} ">
			<label for="materiales"> <g:message
					code="contenido.materiales.label" default="Materiales" />
			</label>
			<ul class="one-to-many">
				<g:each in="${contenidoInstance?.materiales?}" var="m">
					<li><g:link controller="materialContenido" action="show"
							id="${m.id}"
							params="['cursoId': params.cursoId, 'temaId': params.temaId, 'contenidoId': contenidoInstance.id]">
							${m?.encodeAsHTML()}
						</g:link></li>
				</g:each>
				<li class="add"><g:link controller="materialContenido"
						action="create"
						params="['cursoId': params.cursoId, 
									'temaId': params.temaId, 'contenidoId': contenidoInstance.id]">
						${message(code: 'default.add.label', args: [message(code: 'materialContenido.label', default: 'MaterialContenido')])}
					</g:link></li>
			</ul>
		</div>

	</div>
	</body>
</html>
