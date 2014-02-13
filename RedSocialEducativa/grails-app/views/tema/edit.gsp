<%@ page import="com.fiuba.Tema" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'tema.label', default: 'Tema')}" />
		<title><g:message code="default.edit.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#edit-tema" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		
		<h2>params curso id: ${params.cursoId}</h2>
		
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}">
					<g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="mediador" controller="curso" params="['cursoId': params.cursoId]">
					<g:message code="Pagina principal de mediador"/></g:link></li>		
				<li><g:link class="list" action="index" params="['cursoId': params.cursoId]">
					<g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create" params="['cursoId': params.cursoId]">
					<g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
			<h2>Params: ${params}</h2>
		<h2>Curso: ${com.fiuba.Curso.get(params.cursoId)}</h2>
		<h2>Curso Id: ${params.cursoId}</h2>
		<div id="edit-tema" class="content scaffold-edit" role="main">
			<h1><g:message code="default.edit.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:hasErrors bean="${temaInstance}">
			<ul class="errors" role="alert">
				<g:eachError bean="${temaInstance}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>
		<div
			class="fieldcontain ${hasErrors(bean: temaInstance, field: 'contenidos', 'error')} ">
			<label for="contenidos"> <g:message
					code="tema.contenidos.label" default="Contenidos" />
			</label>

			<ul class="one-to-many">
				<g:each in="${temaInstance?.contenidos?}" var="c">
					<li><g:link controller="contenido" action="show" id="${c.id}"
							params="['cursoId': params.cursoId, 'temaId': temaInstance.id]">
							${c?.encodeAsHTML()}
						</g:link></li>
				</g:each>
				<li class="add"><g:link controller="contenido" action="create"
						params="['cursoId': params.cursoId, 'temaId': temaInstance.id]">
						${message(code: 'default.add.label', args: [message(code: 'contenido.label', default: 'Contenido')])}
					</g:link></li>
			</ul>
		</div>
		<div
			class="fieldcontain ${hasErrors(bean: temaInstance, field: 'materiales', 'error')} ">
			<label for="materiales"> <g:message
					code="tema.materiales.label" default="Materiales" />

			</label>
			<ul class="one-to-many">
				<g:each in="${temaInstance?.materiales?}" var="m">
					<li><g:link controller="materialTema" action="show"
							id="${m.id}"
							params="['cursoId': params.cursoId, 'temaId': temaInstance.id]">
							${m?.encodeAsHTML()}
						</g:link></li>
				</g:each>
				<li class="add"><g:link controller="materialTema"
						action="create"
						params="['cursoId': params.cursoId, 'temaId': temaInstance?.id]">
						${message(code: 'default.add.label', args: [message(code: 'materialTema.label', default: 'MaterialTema')])}
					</g:link></li>
			</ul>
		</div>
	</div>
	</body>
</html>
