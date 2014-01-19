
<%@ page import="com.fiuba.Curso" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'curso.label', default: 'Curso')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-curso" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
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
		<div id="show-curso" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list curso">
			
				<g:if test="${cursoInstance?.aprendices}">
				<li class="fieldcontain">
					<span id="aprendices-label" class="property-label"><g:message code="curso.aprendices.label" default="Aprendices" /></span>
					
						<g:each in="${cursoInstance.aprendices}" var="a">
						<span class="property-value" aria-labelledby="aprendices-label">
							<g:link controller="usuario" action="show" id="${a.usuario.id}">${a?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${cursoInstance?.cuatDict}">
				<li class="fieldcontain">
					<span id="cuatDict-label" class="property-label"><g:message code="curso.cuatDict.label" default="Cuat Dict" /></span>
					
						<span class="property-value" aria-labelledby="cuatDict-label"><g:fieldValue bean="${cursoInstance}" field="cuatDict"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${cursoInstance?.materia}">
				<li class="fieldcontain">
					<span id="materia-label" class="property-label"><g:message code="curso.materia.label" default="Materia" /></span>
					
						<span class="property-value" aria-labelledby="materia-label"><g:link controller="materia" action="show" id="${cursoInstance?.materia?.id}">${cursoInstance?.materia?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${cursoInstance?.mediadores}">
				<li class="fieldcontain">
					<span id="mediadores-label" class="property-label"><g:message code="curso.mediadores.label" default="Mediadores" /></span>
					
						<g:each in="${cursoInstance.mediadores}" var="m">
						<span class="property-value" aria-labelledby="mediadores-label"><g:link controller="mediador" action="show" id="${m.id}">${m?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${cursoInstance?.nroRelativo}">
				<li class="fieldcontain">
					<span id="nroRelativo-label" class="property-label"><g:message code="curso.nroRelativo.label" default="Nro Relativo" /></span>
					
						<span class="property-value" aria-labelledby="nroRelativo-label"><g:fieldValue bean="${cursoInstance}" field="nroRelativo"/></span>
					
				</li>
				</g:if>
				
				<g:if test="${cursoInstance?.foro}">
				<li class="fieldcontain">
					<span id="foro-label" class="property-label"><g:message code="curso.foro.label" default="Foro" /></span>
					
						<span class="property-value" aria-labelledby="foro-label"><g:fieldValue bean="${cursoInstance}" field="foro"/></span>
					
				</li>
				</g:if>
				
				<g:if test="${cursoInstance?.nombre}">
				<li class="fieldcontain">
					<span id="nombre-label" class="property-label"><g:message code="curso.nombre.label" default="Nombre" /></span>
					
						<span class="property-value" aria-labelledby="nombre-label"><g:fieldValue bean="${cursoInstance}" field="nombre"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form action="delete" method="DELETE" id="${cursoInstance.id}" >
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${cursoInstance}" id="${cursoInstance.id}">
						<g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" 
						onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
