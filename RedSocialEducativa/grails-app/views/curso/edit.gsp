<%@ page import="com.fiuba.Curso" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'curso.label', default: 'Curso')}" />
		<title><g:message code="default.edit.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#edit-curso" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}">
					<g:message code="default.home.label"/></a></li>
				<li><g:link class="list" controller="administrador" action="general">
					<g:message code="Tareas administrativas"/></g:link></li>
				<li><g:link class="list" action="index">
					<g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create">
					<g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="edit-curso" class="content scaffold-edit" role="main">
			<h1><g:message code="default.edit.label" args="[entityName]" /></h1>
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
			<g:form url="[resource:cursoInstance, action:'update']" method="PUT" >
				<g:hiddenField name="version" value="${cursoInstance?.version}" />
				<fieldset class="form">
					<g:render template="form"/>
					<div><g:hiddenField name="nroRelativo" value="${cursoInstance?.nroRelativo}"/></div>
					<div><g:hiddenField name="materia.id" value="${cursoInstance?.materia.id}"/></div>
				</fieldset>
				<fieldset class="buttons">
					<g:actionSubmit class="save" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
