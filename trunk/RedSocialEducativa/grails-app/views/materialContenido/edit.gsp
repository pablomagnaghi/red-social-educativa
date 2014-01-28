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
				<li><g:link controller="tema" action="index" id="${temaId}" params="['cursoId': cursoId]">
					<g:message code="Lista de temas del curso ${com.fiuba.Curso.get(cursoId)}" args="[entityName]" /></g:link></li>
				<li><g:link controller="tema" action="show" id="${temaId}" params="['cursoId': cursoId, 'temaId': temaId]">
					<g:message code="Tema: ${com.fiuba.Tema.get(temaId)}" args="[entityName]" /></g:link></li>
				<li><g:link controller="contenido" action="show" id="${contenidoId}" params="['cursoId': cursoId, 'temaId': temaId]">
					<g:message code="Tema: ${com.fiuba.Tema.get(temaId)}" args="[entityName]" /></g:link></li>	
				<li><g:link class="create" action="create" params="['cursoId': cursoId, 'temaId': temaId, 'contenidoId': contenidoId]">
					<g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
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
				params="['cursoId': params.cursoId, 'temaId': params.temaId, 'contenidoId': contenidoId]" >
				<g:hiddenField name="version" value="${materialContenidoInstance?.version}" />
				<fieldset class="form">
					<g:render template="form"/>
						<div> <g:hiddenField name="titulo" value="${materialContenidoInstance.titulo}"/></div>
						<div> <g:hiddenField name="responsable" value="${materialContenidoInstance.responsable}"/></div>
				</fieldset>
				<fieldset class="buttons">
					<g:actionSubmit class="save" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>

