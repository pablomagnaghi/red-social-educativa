<%@ page import="com.fiuba.MaterialGrupo" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'materialGrupo.label', default: 'MaterialGrupo')}" />
		<title><g:message code="default.edit.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#edit-materialGrupo" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}">
					<g:message code="default.home.label"/></a></li>
				<li><g:link controller="grupoCurso" action="general" params="['cursoId': cursoId, 'cuatrimestreId': cuatrimestreId]">
					<g:message code="Lista de grupos del curso ${com.fiuba.Curso.get(cursoId)}" args="[entityName]" /></g:link></li>
				<li><g:link controller="grupoCurso" action="mostrar" id="${grupoId}" 
					params="['cursoId': cursoId, 'cuatrimestreId': cuatrimestreId, 'grupoId': grupoId]">
					<g:message code="Grupo: ${com.fiuba.GrupoCurso.get(grupoId)}" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="edit-materialGrupo" class="content scaffold-edit" role="main">
			<h1><g:message code="default.edit.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:hasErrors bean="${materialGrupoInstance}">
			<ul class="errors" role="alert">
				<g:eachError bean="${materialGrupoInstance}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>
			<g:form action="update" method="PUT" id="${materialGrupoInstance.id}" 
				params="['cursoId': params.cursoId, 'cuatrimestreId': cuatrimestreId, 'grupoId': params.grupoId]" >
				<g:hiddenField name="version" value="${materialGrupoInstance?.version}" />
				<fieldset class="form">
					<g:render template="form"/>
						<div> <g:hiddenField name="titulo" value="${materialGrupoInstance.titulo}"/></div>
						<div> <g:hiddenField name="responsable" value="${materialGrupoInstance.responsable}"/></div>
				</fieldset>
				<fieldset class="buttons">
					<g:actionSubmit class="save" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
