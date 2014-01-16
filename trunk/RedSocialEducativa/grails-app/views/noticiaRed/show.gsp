
<%@ page import="com.fiuba.NoticiaRed" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'noticiaRed.label', default: 'NoticiaRed')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-noticiaRed" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}">
					<g:message code="default.home.label"/></a></li>
				<li><g:link class="list" controller="administrador" action="general">
					<g:message code="Volver a tareas administrativas"/></g:link></li>
				<li><g:link class="list" action="index">
					<g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create">
					<g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-noticiaRed" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list noticiaRed">
			
				<g:if test="${noticiaRedInstance?.administrador}">
				<li class="fieldcontain">
					<span id="administrador-label" class="property-label"><g:message code="noticiaRed.administrador.label" default="Administrador" /></span>
					
						<span class="property-value" aria-labelledby="administrador-label">
							<g:link controller="usuario" action="show" id="${noticiaRedInstance?.administrador?.usuario?.id}">
								${noticiaRedInstance?.administrador?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${noticiaRedInstance?.fecha}">
				<li class="fieldcontain">
					<span id="fecha-label" class="property-label"><g:message code="noticiaRed.fecha.label" default="Fecha" /></span>
					
						<span class="property-value" aria-labelledby="fecha-label"><g:formatDate date="${noticiaRedInstance?.fecha}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${noticiaRedInstance?.texto}">
				<li class="fieldcontain">
					<span id="texto-label" class="property-label"><g:message code="noticiaRed.texto.label" default="Texto" /></span>
					
						<span class="property-value" aria-labelledby="texto-label"><g:fieldValue bean="${noticiaRedInstance}" field="texto"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${noticiaRedInstance?.titulo}">
				<li class="fieldcontain">
					<span id="titulo-label" class="property-label"><g:message code="noticiaRed.titulo.label" default="Titulo" /></span>
					
						<span class="property-value" aria-labelledby="titulo-label"><g:fieldValue bean="${noticiaRedInstance}" field="titulo"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${noticiaRedInstance?.visibilidad}">
				<li class="fieldcontain">
					<span id="visibilidad-label" class="property-label"><g:message code="noticiaRed.visibilidad.label" default="Visibilidad" /></span>
					
						<span class="property-value" aria-labelledby="visibilidad-label"><g:formatBoolean boolean="${noticiaRedInstance?.visibilidad}" /></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form action="delete" method="DELETE" id="${noticiaRedInstance.id}">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${noticiaRedInstance}" id="${noticiaRedInstance.id}"> 
						<g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete"  
						value="${message(code: 'default.button.delete.label', default: 'Delete')}" 
						onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
