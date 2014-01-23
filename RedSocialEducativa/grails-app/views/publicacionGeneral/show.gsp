
<%@ page import="com.fiuba.PublicacionGeneral" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'publicacionGeneral.label', default: 'PublicacionGeneral')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-publicacionGeneral" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-publicacionGeneral" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list publicacionGeneral">
			
				<g:if test="${publicacionGeneralInstance?.publicacionPadre}">
				<li class="fieldcontain">
					<span id="publicacionPadre-label" class="property-label"><g:message code="publicacionGeneral.publicacionPadre.label" default="Publicacion Padre" /></span>
					
						<span class="property-value" aria-labelledby="publicacionPadre-label"><g:link controller="publicacionGeneral" action="show" id="${publicacionGeneralInstance?.publicacionPadre?.id}">${publicacionGeneralInstance?.publicacionPadre?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${publicacionGeneralInstance?.contenido}">
				<li class="fieldcontain">
					<span id="contenido-label" class="property-label"><g:message code="publicacionGeneral.contenido.label" default="Contenido" /></span>
					
						<span class="property-value" aria-labelledby="contenido-label"><g:fieldValue bean="${publicacionGeneralInstance}" field="contenido"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${publicacionGeneralInstance?.fecha}">
				<li class="fieldcontain">
					<span id="fecha-label" class="property-label"><g:message code="publicacionGeneral.fecha.label" default="Fecha" /></span>
					
						<span class="property-value" aria-labelledby="fecha-label"><g:fieldValue bean="${publicacionGeneralInstance}" field="fecha"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${publicacionGeneralInstance?.foro}">
				<li class="fieldcontain">
					<span id="foro-label" class="property-label"><g:message code="publicacionGeneral.foro.label" default="Foro" /></span>
					
						<span class="property-value" aria-labelledby="foro-label"><g:link controller="foroGeneral" action="show" id="${publicacionGeneralInstance?.foro?.id}">${publicacionGeneralInstance?.foro?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${publicacionGeneralInstance?.hora}">
				<li class="fieldcontain">
					<span id="hora-label" class="property-label"><g:message code="publicacionGeneral.hora.label" default="Hora" /></span>
					
						<span class="property-value" aria-labelledby="hora-label"><g:fieldValue bean="${publicacionGeneralInstance}" field="hora"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${publicacionGeneralInstance?.responsable}">
				<li class="fieldcontain">
					<span id="responsable-label" class="property-label"><g:message code="publicacionGeneral.responsable.label" default="Responsable" /></span>
					
						<span class="property-value" aria-labelledby="responsable-label"><g:fieldValue bean="${publicacionGeneralInstance}" field="responsable"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${publicacionGeneralInstance?.respuesta}">
				<li class="fieldcontain">
					<span id="respuesta-label" class="property-label"><g:message code="publicacionGeneral.respuesta.label" default="Respuesta" /></span>
					
						<span class="property-value" aria-labelledby="respuesta-label"><g:link controller="publicacionGeneral" action="show" id="${publicacionGeneralInstance?.respuesta?.id}">${publicacionGeneralInstance?.respuesta?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${publicacionGeneralInstance?.titulo}">
				<li class="fieldcontain">
					<span id="titulo-label" class="property-label"><g:message code="publicacionGeneral.titulo.label" default="Titulo" /></span>
					
						<span class="property-value" aria-labelledby="titulo-label"><g:fieldValue bean="${publicacionGeneralInstance}" field="titulo"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:publicacionGeneralInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${publicacionGeneralInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
