
<%@ page import="com.fiuba.NoticiaCurso" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'noticiaCurso.label', default: 'NoticiaCurso')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-noticiaCurso" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}">
					<g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index" params="['cursoId': cursoId, 'cuatrimestreId': params.cuatrimestreId]">
					<g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create" params="['cursoId': cursoId, 'cuatrimestreId': params.cuatrimestreId]">
					<g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-noticiaCurso" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list noticiaCurso">
			
				<g:if test="${noticiaCursoInstance?.cuatrimestre}">
				<li class="fieldcontain">
					<span id="cuatrimestre-label" class="property-label"><g:message code="noticiaCurso.cuatrimestre.label" default="Cuatrimestre" /></span>
					
						<span class="property-value" aria-labelledby="cuatrimestre-label">${noticiaCursoInstance?.cuatrimestre?.encodeAsHTML()}</span>
					
				</li>
				</g:if>
			
				
				<g:if test="${noticiaCursoInstance?.fecha}">
				<li class="fieldcontain">
					<span id="fecha-label" class="property-label"><g:message code="noticiaCurso.fecha.label" default="Fecha" /></span>
					
						<span class="property-value" aria-labelledby="fecha-label"><g:fieldValue bean="${noticiaCursoInstance}" field="fecha"/></span>
					
				</li>
				</g:if>
				<g:if test="${noticiaCursoInstance?.hora}">
				<li class="fieldcontain">
					<span id="hora-label" class="property-label"><g:message code="noticiaCurso.hora.label" default="Hora" /></span>
					
						<span class="property-value" aria-labelledby="hora-label"><g:fieldValue bean="${noticiaCursoInstance}" field="hora"/></span>
					
				</li>
				</g:if>			
			
				<g:if test="${noticiaCursoInstance?.mediador}">
				<li class="fieldcontain">
					<span id="mediador-label" class="property-label"><g:message code="noticiaCurso.mediador.label" default="Mediador" /></span>
					
						<span class="property-value" aria-labelledby="mediador-label">${noticiaCursoInstance?.mediador?.encodeAsHTML()}</span>
					
				</li>
				</g:if>
			
				<g:if test="${noticiaCursoInstance?.texto}">
				<li class="fieldcontain">
					<span id="texto-label" class="property-label"><g:message code="noticiaCurso.texto.label" default="Texto" /></span>
					
						<span class="property-value" aria-labelledby="texto-label"><g:fieldValue bean="${noticiaCursoInstance}" field="texto"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${noticiaCursoInstance?.titulo}">
				<li class="fieldcontain">
					<span id="titulo-label" class="property-label"><g:message code="noticiaCurso.titulo.label" default="Titulo" /></span>
					
						<span class="property-value" aria-labelledby="titulo-label"><g:fieldValue bean="${noticiaCursoInstance}" field="titulo"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${noticiaCursoInstance?.visibilidad}">
				<li class="fieldcontain">
					<span id="visibilidad-label" class="property-label"><g:message code="noticiaCurso.visibilidad.label" default="Visibilidad" /></span>
					
						<span class="property-value" aria-labelledby="visibilidad-label"><g:formatBoolean boolean="${noticiaCursoInstance?.visibilidad}" /></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form action="delete" method="DELETE" id="${noticiaCursoInstance.id}" 
				params="['cursoId': cursoId, 'cuatrimestreId': params.cuatrimestreId]">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${noticiaCursoInstance}" 
						id="${noticiaCursoInstance.id}" params="['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId]">
						<g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" 
						onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
