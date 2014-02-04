<%@ page import="com.fiuba.GrupoCurso" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'grupoCurso.label', default: 'GrupoCurso')}" />
		<title><g:message code="default.edit.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#edit-grupoCurso" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}">
					<g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="general" params="['cursoId': params.cursoId]">
					<g:message code="default.list.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="edit-grupoCurso" class="content scaffold-edit" role="main">
			<h1><g:message code="default.edit.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:hasErrors bean="${grupoCursoInstance}">
			<ul class="errors" role="alert">
				<g:eachError bean="${grupoCursoInstance}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>
			<g:form action="editarNombre" method="PUT" id="${grupoCursoInstance.id}" params="['cursoId': params.cursoId]">
				<g:hiddenField name="version" value="${grupoCursoInstance?.version}" />
				
				<div><g:hiddenField name="numero" type="number" value="${grupoCursoInstance.numero}"/></div>
				<div><g:hiddenField name="curso.id" value="${cursoId}"/></div>
				<div class="fieldcontain ${hasErrors(bean: grupoCursoInstance, field: 'nombre', 'error')} ">
					<label for="nombre">
						<g:message code="grupoCurso.nombre.label" default="Nombre" />				
					</label>
					<g:textField name="nombre" value="${grupoCursoInstance?.nombre}"/>
				</div>
				<fieldset class="buttons">
					<g:actionSubmit class="save" action="editarNombre" value="${message(code: 'default.button.update.label', default: 'Update')}" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
