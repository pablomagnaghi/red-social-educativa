
<%@ page import="com.fiuba.Actividad" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'actividad.label', default: 'Actividad')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-actividad" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}">
					<g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index" params="['cursoId': cursoId]">
					<g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create" params="['cursoId': cursoId]">
					<g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-actividad" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list actividad">
			
				<g:if test="${actividadInstance?.categoria}">
				<li class="fieldcontain">
					<span id="categoria-label" class="property-label"><g:message code="actividad.categoria.label" default="Categoria" /></span>
					
						<span class="property-value" aria-labelledby="categoria-label">
							${actividadInstance?.categoria?.encodeAsHTML()}</span>
					
				</li>
				</g:if>
			
				<g:if test="${actividadInstance?.evaluable}">
				<li class="fieldcontain">
					<span id="evaluable-label" class="property-label"><g:message code="actividad.evaluable.label" default="Evaluable" /></span>
					
						<span class="property-value" aria-labelledby="evaluable-label">
							<g:formatBoolean boolean="${actividadInstance?.evaluable}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${actividadInstance?.fechaFinalizacion}">
				<li class="fieldcontain">
					<span id="fechaFinalizacion-label" class="property-label"><g:message code="actividad.fechaFinalizacion.label" 
						default="Fecha Finalizacion" /></span>
						
						<span class="property-value" aria-labelledby="fechaFinalizacion-label">
							<g:fieldValue bean="${actividadInstance}" field="fechaFinalizacion"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${actividadInstance?.grupal}">
				<li class="fieldcontain">
					<span id="grupal-label" class="property-label"><g:message code="actividad.grupal.label" default="Grupal" /></span>
					
						<span class="property-value" aria-labelledby="grupal-label"><g:formatBoolean boolean="${actividadInstance?.grupal}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${actividadInstance?.grupos}">
				<li class="fieldcontain">
					<span id="grupos-label" class="property-label"><g:message code="actividad.grupos.label" default="Grupos" /></span>
					
						<g:each in="${actividadInstance.grupos}" var="g">
						<span class="property-value" aria-labelledby="grupos-label"><g:link controller="grupoActividad" action="show" id="${g.id}">${g?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${actividadInstance?.materiales}">
				<li class="fieldcontain">
					<span id="materiales-label" class="property-label"><g:message code="actividad.materiales.label" default="Materiales" /></span>
						<g:each in="${actividadInstance.materiales}" var="m">
						<span class="property-value" aria-labelledby="materiales-label">
							<g:link controller="materialActividad" action="show" id="${m.id}" 
								params="['cursoId': cursoId, 'actividadId': actividadInstance.id]">${m?.encodeAsHTML()}</g:link></span>
						</g:each>
				</li>
				</g:if>
				
				<g:if test="${actividadInstance?.objetivo}">
				<li class="fieldcontain">
					<span id="objetivo-label" class="property-label"><g:message code="actividad.objetivo.label" default="Objetivo" /></span>
					
						<span class="property-value" aria-labelledby="objetivo-label"><g:fieldValue bean="${actividadInstance}" field="objetivo"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${actividadInstance?.titulo}">
				<li class="fieldcontain">
					<span id="titulo-label" class="property-label"><g:message code="actividad.titulo.label" default="Titulo" /></span>
					
						<span class="property-value" aria-labelledby="titulo-label"><g:fieldValue bean="${actividadInstance}" field="titulo"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${actividadInstance?.visibilidad}">
				<li class="fieldcontain">
					<span id="visibilidad-label" class="property-label"><g:message code="actividad.visibilidad.label" default="Visibilidad" /></span>
					
						<span class="property-value" aria-labelledby="visibilidad-label"><g:formatBoolean boolean="${actividadInstance?.visibilidad}" /></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form action="delete" method="DELETE" id="${actividadInstance.id}" params="['cursoId': cursoId]">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${actividadInstance}"
						id="${actividadInstance.id}" params="['cursoId': cursoId]">
						<g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" 
						onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
