<%@ page import="com.fiuba.MaterialContenido" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'materialContenido.label', default: 'MaterialContenido')}" />
		<title><g:message code="default.edit.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#edit-materialContenido" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}">
					<g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="mediador" controller="curso" params="['cursoId': params.cursoId]">
					<g:message code="Pagina principal de mediador"/></g:link></li>			
				<li><g:link class="create" action="menuMediador" controller="curso" params="['cursoId': params.cursoId]">
					<g:message code="Tareas mediador" /></g:link></li>		
				<li><g:link controller="tema" action="index" id="${params.temaId}" params="['cursoId': params.cursoId]">
					<g:message code="Lista de temas del curso ${com.fiuba.Curso.get(cursoId)}" args="[entityName]" /></g:link></li>
				<li><g:link controller="tema" action="edit" id="${params.temaId}" params="['cursoId': params.cursoId]">
					<g:message code="Tema: ${com.fiuba.Tema.get(params.temaId)}" args="[entityName]" /></g:link></li>
				<li><g:link controller="contenido" action="show" id="${params.contenidoId}" params="['cursoId': params.cursoId, 'temaId': params.temaId]">
					<g:message code="Contenido: ${com.fiuba.Contenido.get(params.contenidoId)}" args="[entityName]" /></g:link></li>		
				<li><g:link class="create" action="create" params="['cursoId': params.cursoId, 'temaId': params.temaId, 'contenidoId': params.contenidoId]">
					<g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<h2>Curso: ${com.fiuba.Curso.get(params.cursoId)}</h2>
		<h2>Curso Id: ${params.cursoId}</h2>
		<h2>Tema: ${params.temaId}</h2>
		<h2>Contenido: ${params.contenidoId}</h2>
		<div id="edit-materialContenido" class="content scaffold-edit" role="main">
			<h1><g:message code="default.edit.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:hasErrors bean="${materialContenidoInstance}">
			<ul class="errors" role="alert">
				<g:eachError bean="${materialContenidoInstance}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>
			<g:form action="update" method="PUT" id="${materialContenidoInstance.id}" 
				params="['cursoId': params.cursoId, 'temaId': params.temaId, 'contenidoId': params.contenidoId]" >
				<g:hiddenField name="version" value="${materialContenidoInstance?.version}" />
				<fieldset class="form">
					<g:render template="form"/>
						<div><g:hiddenField name="titulo" value="${materialContenidoInstance.titulo}"/></div>
						<div><g:hiddenField name="responsable" value="${materialContenidoInstance.responsable}"/></div>
				</fieldset>
				<fieldset class="buttons">
					<g:actionSubmit class="save" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>

