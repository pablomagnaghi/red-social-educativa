
<%@ page import="com.fiuba.MaterialCurso" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'materialCurso.label', default: 'MaterialCurso')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-materialCurso" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}">
					<g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="mediador" controller="curso" params="['cursoId': params.cursoId]">
					<g:message code="Pagina principal de mediador"/></g:link></li>		
				<li><g:link class="create" action="menuMediador" controller="curso" params="['cursoId': params.cursoId]">
					<g:message code="Tareas mediador" /></g:link></li>	
				<li><g:link class="list" action="index" params="['cursoId': params.cursoId]">
					<g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create" params="['cursoId': params.cursoId]">
					<g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<h2>Curso: ${com.fiuba.Curso.get(params.cursoId)}</h2>
		<h2>Curso Id: ${params.cursoId}</h2>
		<div id="show-materialCurso" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list materialCurso">
			
				<g:if test="${materialCursoInstance?.titulo}">
				<li class="fieldcontain">
					<span id="titulo-label" class="property-label"><g:message code="materialCurso.titulo.label" default="Titulo" /></span>
					
						<span class="property-value" aria-labelledby="titulo-label"><g:fieldValue bean="${materialCursoInstance}" field="titulo"/></span>
					
				</li>
				</g:if>
			
			
				<g:if test="${materialCursoInstance?.autor}">
				<li class="fieldcontain">
					<span id="autor-label" class="property-label"><g:message code="materialCurso.autor.label" default="Autor" /></span>
					
						<span class="property-value" aria-labelledby="autor-label"><g:fieldValue bean="${materialCursoInstance}" field="autor"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${materialCursoInstance?.categoria}">
				<li class="fieldcontain">
					<span id="categoria-label" class="property-label"><g:message code="materialCurso.categoria.label" default="Categoria" /></span>
					
						<span class="property-value" aria-labelledby="categoria-label">${materialCursoInstance?.categoria?.encodeAsHTML()}</span>
					
				</li>
				</g:if>
				
				<g:if test="${materialCursoInstance?.descripcion}">
				<li class="fieldcontain">
					<span id="descripcion-label" class="property-label"><g:message code="materialCurso.descripcion.label" default="Descripcion" /></span>
					
						<span class="property-value" aria-labelledby="descripcion-label"><g:fieldValue bean="${materialCursoInstance}" field="descripcion"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${materialCursoInstance?.fecha}">
				<li class="fieldcontain">
					<span id="fecha-label" class="property-label"><g:message code="materialCurso.fecha.label" default="Fecha" /></span>
					
						<span class="property-value" aria-labelledby="fecha-label"><g:fieldValue bean="${materialCursoInstance}" field="fecha"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${materialCursoInstance?.responsable}">
				<li class="fieldcontain">
					<span id="responsable-label" class="property-label"><g:message code="materialCurso.responsable.label" default="Responsable" /></span>
					
						<span class="property-value" aria-labelledby="responsable-label"><g:fieldValue bean="${materialCursoInstance}" field="responsable"/></span>
					
				</li>
				</g:if>

			</ol>
			<g:form action="delete" method="DELETE" id="${materialCursoInstance.id}" params="['cursoId': params.cursoId]">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${materialCursoInstance}" 
						id="${materialCursoInstance.id}" params="['cursoId': params.cursoId]">
						<g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
