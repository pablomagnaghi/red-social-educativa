
<%@ page import="com.fiuba.MaterialContenido" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'materialContenido.label', default: 'MaterialContenido')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-materialContenido" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}">
					<g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="mediador" controller="curso" params="['cursoId': params.cursoId]">
					<g:message code="Pagina principal de mediador"/></g:link></li>			
				<li><g:link class="create" action="menuMediador" controller="curso" params="['cursoId': params.cursoId]">
					<g:message code="Tareas mediador" /></g:link></li>		
				<li><g:link controller="tema" action="index" id="${params.temaId}" params="['cursoId': params.cursoId]">
					<g:message code="Lista de temas del curso ${com.fiuba.Curso.get(cursoId)}" args="[entityName]" /></g:link></li>
				<li><g:link controller="tema" action="edit" id="${params.temaId}" params="['cursoId': params.cursoId]">
					<g:message code="Tema: ${com.fiuba.Tema.get(params.temaId)}" args="[entityName]" /></g:link></li>
				<li><g:link controller="contenido" action="show" id="${params.contenidoId}" params="['cursoId': params.cursoId, 'temaId': params.temaId]">
					<g:message code="Contenido: ${com.fiuba.Contenido.get(params.contenidoId)}" args="[entityName]" /></g:link></li>		
				<li><g:link class="create" action="create" params="['cursoId': params.cursoId, 'temaId': params.temaId, 'contenidoId': params.contenidoId]">
					<g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<h2>Curso: ${com.fiuba.Curso.get(params.cursoId)}</h2>
		<h2>Curso Id: ${params.cursoId}</h2>
		<h2>Tema: ${params.temaId}</h2>
		<h2>Contenido: ${params.contenidoId}</h2>
		<div id="show-materialContenido" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list materialContenido">
			
				<g:if test="${materialContenidoInstance?.titulo}">
				<li class="fieldcontain">
					<span id="titulo-label" class="property-label"><g:message code="materialContenido.titulo.label" default="Titulo" /></span>
					
						<span class="property-value" aria-labelledby="titulo-label"><g:fieldValue bean="${materialContenidoInstance}" field="titulo"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${materialContenidoInstance?.autor}">
				<li class="fieldcontain">
					<span id="autor-label" class="property-label"><g:message code="materialContenido.autor.label" default="Autor" /></span>
					
						<span class="property-value" aria-labelledby="autor-label"><g:fieldValue bean="${materialContenidoInstance}" field="autor"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${materialContenidoInstance?.categoria}">
				<li class="fieldcontain">
					<span id="categoria-label" class="property-label"><g:message code="materialContenido.categoria.label" default="Categoria" /></span>
					
						<span class="property-value" aria-labelledby="categoria-label">${materialContenidoInstance?.categoria?.encodeAsHTML()}</span>
					
				</li>
				</g:if>
			
				<g:if test="${materialContenidoInstance?.contenido}">
				<li class="fieldcontain">
					<span id="contenido-label" class="property-label"><g:message code="materialContenido.contenido.label" default="Contenido" /></span>
					
						<span class="property-value" aria-labelledby="contenido-label">
							<g:link controller="contenido" action="show" id="${materialContenidoInstance?.contenido?.id}"
								params="['cursoId': params.cursoId, 'temaId': params.temaId]">
								${materialContenidoInstance?.contenido?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${materialContenidoInstance?.descripcion}">
				<li class="fieldcontain">
					<span id="descripcion-label" class="property-label"><g:message code="materialContenido.descripcion.label" default="Descripcion" /></span>
					
						<span class="property-value" aria-labelledby="descripcion-label"><g:fieldValue bean="${materialContenidoInstance}" field="descripcion"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${materialContenidoInstance?.fecha}">
				<li class="fieldcontain">
					<span id="fecha-label" class="property-label"><g:message code="materialContenido.fecha.label" default="Fecha" /></span>
					
						<span class="property-value" aria-labelledby="fecha-label"><g:fieldValue bean="${materialContenidoInstance}" field="fecha"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${materialContenidoInstance?.responsable}">
				<li class="fieldcontain">
					<span id="responsable-label" class="property-label"><g:message code="materialContenido.responsable.label" default="Responsable" /></span>
					
						<span class="property-value" aria-labelledby="responsable-label"><g:fieldValue bean="${materialContenidoInstance}" field="responsable"/></span>
					
				</li>
				</g:if>
		
			
			</ol>
			<g:form action="delete" method="DELETE" id="${materialContenidoInstance.id}" 
				params="['cursoId': params.cursoId, 'temaId': params.temaId, 'contenidoId': params.contenidoId]">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${materialContenidoInstance}"
						id="${materialContenidoInstance.id}" params="['cursoId': params.cursoId, 'temaId': params.temaId, 'contenidoId': params.contenidoId]">
						<g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
	
		</div>
	</body>
</html>
