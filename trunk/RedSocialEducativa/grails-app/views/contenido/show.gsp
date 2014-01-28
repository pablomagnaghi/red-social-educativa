
<%@ page import="com.fiuba.Contenido" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'contenido.label', default: 'Contenido')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-contenido" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
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
		<div id="show-contenido" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list contenido">
	
				<g:if test="${contenidoInstance?.tema}">
				<li class="fieldcontain">
					<span id="tema-label" class="property-label"><g:message code="contenido.tema.label" default="Tema" /></span>
					
						<span class="property-value" aria-labelledby="tema-label">
							<g:link controller="tema" action="show" id="${contenidoInstance?.tema?.id}" >
							${contenidoInstance?.tema?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${contenidoInstance?.titulo}">
				<li class="fieldcontain">
					<span id="titulo-label" class="property-label"><g:message code="contenido.titulo.label" default="Titulo" /></span>
					
						<span class="property-value" aria-labelledby="titulo-label"><g:fieldValue bean="${contenidoInstance}" field="titulo"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${contenidoInstance?.materiales}">
				<li class="fieldcontain">
					<span id="materiales-label" class="property-label">
						<g:message code="contenido.materiales.label" default="Materiales" /></span>
					
						<g:each in="${contenidoInstance.materiales}" var="m">
						<span class="property-value" aria-labelledby="materiales-label">
							<g:link controller="materialContenido" action="show" id="${m.id}"
								params="['cursoId': cursoId, 'temaId': temaId, 'contenidoId': contenidoInstance.id]">${m?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
			</ol>
			<g:form action="delete" method="DELETE" id="${contenidoInstance.id}" params="['cursoId': cursoId, 'temaId': temaId]">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${contenidoInstance}" 
						id="${contenidoInstance.id}" params="['cursoId': cursoId, 'temaId': temaId]">
						<g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
