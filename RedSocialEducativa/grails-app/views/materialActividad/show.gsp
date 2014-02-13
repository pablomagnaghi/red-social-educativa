
<%@ page import="com.fiuba.MaterialActividad" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'materialActividad.label', default: 'MaterialActividad')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-materialActividad" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
			<li><a class="home" href="${createLink(uri: '/')}">
					<g:message code="default.home.label"/></a></li>
				<li><g:link controller="actividad" action="index" params="['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId]">
					<g:message code="Lista de actividades del curso ${com.fiuba.Curso.get(params.cursoId)}" args="[entityName]" /></g:link></li>
				<li><g:link controller="actividad" action="show" id="${params.actividadId}" 
						params="['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId]">
					<g:message code="Actividad: ${com.fiuba.Actividad.get(params.actividadId)}" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create" params="['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 
						'actividadId': params.actividadId]">
					<g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<h3>curso: ${params.cursoId}</h3>
		<h3>cuatri: ${params.cuatrimestreId}</h3>
		<h3>act: ${params.actividadId}</h3>
		<div id="show-materialActividad" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list materialActividad">
			
				<g:if test="${materialActividadInstance?.actividad}">
				<li class="fieldcontain">
					<span id="actividad-label" class="property-label"><g:message code="materialActividad.actividad.label" default="Actividad" /></span>
					
						<span class="property-value" aria-labelledby="actividad-label">
						<g:link controller="actividad" action="show" id="${materialActividadInstance?.actividad?.id}"
							params="['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 'actividadId': params.actividadId]">
								${materialActividadInstance?.actividad?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${materialActividadInstance?.autor}">
				<li class="fieldcontain">
					<span id="autor-label" class="property-label"><g:message code="materialActividad.autor.label" default="Autor" /></span>
					
						<span class="property-value" aria-labelledby="autor-label"><g:fieldValue bean="${materialActividadInstance}" field="autor"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${materialActividadInstance?.categoria}">
				<li class="fieldcontain">
					<span id="categoria-label" class="property-label"><g:message code="materialActividad.categoria.label" default="Categoria" /></span>
					
						<span class="property-value" aria-labelledby="categoria-label">
							${materialActividadInstance?.categoria?.encodeAsHTML()}</span>
					
				</li>
				</g:if>
			
				<g:if test="${materialActividadInstance?.descripcion}">
				<li class="fieldcontain">
					<span id="descripcion-label" class="property-label"><g:message code="materialActividad.descripcion.label" default="Descripcion" /></span>
					
						<span class="property-value" aria-labelledby="descripcion-label"><g:fieldValue bean="${materialActividadInstance}" field="descripcion"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${materialActividadInstance?.fecha}">
				<li class="fieldcontain">
					<span id="fecha-label" class="property-label"><g:message code="materialActividad.fecha.label" default="Fecha" /></span>
					
						<span class="property-value" aria-labelledby="fecha-label"><g:fieldValue bean="${materialActividadInstance}" field="fecha"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${materialActividadInstance?.responsable}">
				<li class="fieldcontain">
					<span id="responsable-label" class="property-label"><g:message code="materialActividad.responsable.label" default="Responsable" /></span>
					
						<span class="property-value" aria-labelledby="responsable-label"><g:fieldValue bean="${materialActividadInstance}" field="responsable"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${materialActividadInstance?.titulo}">
				<li class="fieldcontain">
					<span id="titulo-label" class="property-label"><g:message code="materialActividad.titulo.label" default="Titulo" /></span>
					
						<span class="property-value" aria-labelledby="titulo-label"><g:fieldValue bean="${materialActividadInstance}" field="titulo"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form action="delete" method="DELETE" id="${materialActividadInstance.id}" 
					params="['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 'actividadId': params.actividadId]">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${materialActividadInstance}"
					id="${materialActividadInstance.id}" params="['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 
							'actividadId': params.actividadId]">
						<g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" 
						onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>	
		</div>
	</body>
</html>
