
<%@ page import="com.fiuba.MaterialTema" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'materialTema.label', default: 'MaterialTema')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-materialTema" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}">
					<g:message code="default.home.label"/></a></li>
				<li><g:link controller="tema" action="index" id="${temaId}" params="['cursoId': cursoId]">
					<g:message code="Lista de temas del curso ${com.fiuba.Curso.get(cursoId)}" args="[entityName]" /></g:link></li>
				<li><g:link controller="tema" action="show" id="${temaId}" params="['cursoId': cursoId, 'temaId': temaId]">
					<g:message code="Tema: ${com.fiuba.Tema.get(temaId)}" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create" params="['cursoId': cursoId, 'temaId': temaId]">
					<g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-materialTema" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list materialTema">
			
				<g:if test="${materialTemaInstance?.titulo}">
				<li class="fieldcontain">
					<span id="titulo-label" class="property-label">
						<g:message code="materialTema.titulo.label" default="Titulo" /></span>
					<span class="property-value" aria-labelledby="titulo-label">
						<g:fieldValue bean="${materialTemaInstance}" field="titulo"/></span>
				</li>
				</g:if>
				
				<g:if test="${materialTemaInstance?.categoria}">
				<li class="fieldcontain">
					<span id="categoria-label" class="property-label">
						<g:message code="materialTema.categoria.label" default="Categoria" /></span>
					<span class="property-value" aria-labelledby="categoria-label">
						${materialTemaInstance?.categoria?.encodeAsHTML()}</span>
				</li>
				</g:if>			
				<g:if test="${materialTemaInstance?.autor}">
				<li class="fieldcontain">
					<span id="autor-label" class="property-label">
						<g:message code="materialTema.autor.label" default="Autor" /></span>
					<span class="property-value" aria-labelledby="autor-label">
						<g:fieldValue bean="${materialTemaInstance}" field="autor"/></span>
				</li>
				</g:if>
				<g:if test="${materialTemaInstance?.descripcion}">
				<li class="fieldcontain">
					<span id="descripcion-label" class="property-label">
						<g:message code="materialTema.descripcion.label" default="Descripcion" /></span>
					<span class="property-value" aria-labelledby="descripcion-label">
						<g:fieldValue bean="${materialTemaInstance}" field="descripcion"/></span>
				</li>
				</g:if>
				<g:if test="${materialTemaInstance?.fecha}">
				<li class="fieldcontain">
					<span id="fecha-label" class="property-label">
						<g:message code="materialTema.fecha.label" default="Fecha" /></span>
					<span class="property-value" aria-labelledby="fecha-label">
						<g:fieldValue bean="${materialTemaInstance}" field="fecha"/></span>
				</li>
				</g:if>
			
				<g:if test="${materialTemaInstance?.responsable}">
				<li class="fieldcontain">
					<span id="responsable-label" class="property-label">
						<g:message code="materialTema.responsable.label" default="Responsable" /></span>
					<span class="property-value" aria-labelledby="responsable-label">
						<g:fieldValue bean="${materialTemaInstance}" field="responsable"/></span>
				</li>
				</g:if>
			
				<g:if test="${materialTemaInstance?.tema}">
				<li class="fieldcontain">
					<span id="tema-label" class="property-label">
						<g:message code="materialTema.tema.label" default="Tema" /></span>
					<span class="property-value" aria-labelledby="tema-label">
						<g:link controller="tema" action="show" id="${materialTemaInstance?.tema?.id}"
							params="['cursoId': cursoId, 'temaId': temaId]">
							${materialTemaInstance?.tema?.encodeAsHTML()}</g:link></span>
				</li>
				</g:if>
			</ol>
			<g:form action="delete" method="DELETE" id="${materialTemaInstance.id}" params="['cursoId': cursoId, 'temaId': temaId]">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${materialTemaInstance}"
						id="${materialTemaInstance.id}" params="['cursoId': cursoId, 'temaId': temaId]">
						<g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
