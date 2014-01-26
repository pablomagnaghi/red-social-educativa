<%@ page import="com.fiuba.PublicacionTema" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'publicacionTema.label', default: 'PublicacionTema')}" />
		<title><g:message code="default.edit.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#edit-publicacionTema" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}">
					<g:message code="default.home.label"/></a></li>
				<li><g:link class="create" controller="foroTema" action="general" params="['cursoId': cursoId, 'temaId': temaId]">
					<g:message code="Foro Tema" /></g:link></li>
				<li><g:link class="list" controller="foroTema" action="publicaciones" 
					id="${publicacionId}" params="['pubInicialId': pubInicialId, 'cursoId': cursoId, 'temaId': temaId]">
					<g:message code="Tema actual" /></g:link></li>
			</ul>
		</div>
		<div id="edit-publicacionCurso" class="content scaffold-edit" role="main">
			<h1><g:message code="default.edit.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:hasErrors bean="${publicacionTemaInstance}">
			<ul class="errors" role="alert">
				<g:eachError bean="${publicacionTemaInstance}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>
			<g:form action="actualizar" method="PUT" id="${publicacionTemaInstance.id}" 
				params="['pubInicialId': pubInicialId, 'cursoId': cursoId, 'temaId': temaId]">
				<g:hiddenField name="version" value="${publicacionTemaInstance?.version}" />
				<fieldset class="form">
					<g:render template="form"/>
				</fieldset>
				<fieldset class="buttons">
					<g:actionSubmit class="save" action="actualizar" value="${message(code: 'default.button.update.label', default: 'Update')}" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>

