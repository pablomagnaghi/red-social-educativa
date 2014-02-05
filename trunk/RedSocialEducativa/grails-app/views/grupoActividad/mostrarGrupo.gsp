
<%@ page import="com.fiuba.GrupoActividad" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'grupoActividad.label', default: 'GrupoActividad')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-grupoActividad" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}">
					<g:message code="default.home.label"/></a></li>
				<li><g:link class="create" controller="curso" action="aprendiz" params="['cursoId': cursoId]">
					<g:message code="Menu aprendiz" args="[entityName]" /></g:link></li>
				<li><g:link class="list" action="general" params="['cursoId': cursoId, 'actividadId': actividadId]">
					<g:message code="Lista de grupos" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		
		<h2>actividad: ${actividadId}</h2>
		<h2>curso: ${cursoId}</h2>
		
		<div id="show-grupoActividad" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list grupoActividad">
			
			
				<g:if test="${grupoActividadInstance?.materiales}">
				<li class="fieldcontain">
					<span id="materiales-label" class="property-label"><g:message code="grupoActividad.materiales.label" 
						default="Materiales" /></span>
					
						<g:each in="${grupoActividadInstance.materiales}" var="m">
						<span class="property-value" aria-labelledby="materiales-label">
							<g:link controller="materialGrupoActividad" action="show" id="${m.id}" 
								params="['cursoId': cursoId, 'actividadId': actividadId,
								'grupoActividadId': grupoActividadInstance.id]">${m?.encodeAsHTML()}</g:link></span>
						</g:each>
				</li>
				</g:if>
			
				<g:if test="${grupoActividadInstance?.aprendices}">
				<li class="fieldcontain">
					<span id="aprendices-label" class="property-label"><g:message code="grupoActividad.aprendices.label" default="Aprendices" /></span>
					
						<g:each in="${grupoActividadInstance.aprendices}" var="a">
						<span class="property-value" aria-labelledby="aprendices-label"><g:link controller="aprendizGrupoActividad" action="show" id="${a.id}">${a?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${grupoActividadInstance?.nombre}">
				<li class="fieldcontain">
					<span id="nombre-label" class="property-label"><g:message code="grupoActividad.nombre.label" default="Nombre" /></span>
					
						<span class="property-value" aria-labelledby="nombre-label"><g:fieldValue bean="${grupoActividadInstance}" field="nombre"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${grupoActividadInstance?.numero}">
				<li class="fieldcontain">
					<span id="numero-label" class="property-label"><g:message code="grupoActividad.numero.label" default="Numero" /></span>
					
						<span class="property-value" aria-labelledby="numero-label"><g:fieldValue bean="${grupoActividadInstance}" field="numero"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<fieldset class="buttons">
				<g:if test="${!participa}">
					<g:link class="edit" action="agregarme"
						resource="${grupoActividadInstance}" id="${grupoActividadInstance.id}"
						params="['cursoId': cursoId, 'actividadId': actividadId]">
						<g:message code="Agregarme al grupo" />
					</g:link>
				</g:if>
				<g:else>
					<g:link class="edit" action="editar"
						resource="${grupoActividadInstance}" id="${grupoActividadInstance.id}"
						params="['cursoId': cursoId, 'actividadId': actividadId]">
						<g:message code="Editar nombre de grupo" />
					</g:link>
					<g:link class="edit" controller="materialGrupoActividad" action="create"
						params="['grupoActividadId': grupoActividadInstance?.id, 'cursoId': cursoId, 'actividadId': actividadId]">
						<g:message code="Agregar material al grupo" />
					</g:link>
				</g:else>
			</fieldset>
		</div>
	</body>
</html>
