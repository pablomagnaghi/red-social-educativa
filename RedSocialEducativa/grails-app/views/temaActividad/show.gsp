
<%@ page import="com.fiuba.TemaActividad" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'temaActividad.label', default: 'TemaActividad')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-temaActividad" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}">
					<g:message code="default.home.label"/></a></li>
				<li><g:link controller="actividad" action="index" id="${actividadId}" params="['cursoId': cursoId]">
					<g:message code="Lista de actividads del curso ${com.fiuba.Curso.get(cursoId)}" args="[entityName]" /></g:link></li>
				<li><g:link controller="actividad" action="show" id="${actividadId}" params="['cursoId': cursoId, 'actividadId': actividadId]">
					<g:message code="Actividad: ${com.fiuba.Actividad.get(actividadId)}" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create" params="['cursoId': cursoId, 'actividadId': actividadId]">
					<g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-temaActividad" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list temaActividad">
			
				<g:if test="${temaActividadInstance?.actividad}">
				<li class="fieldcontain">
					<span id="actividad-label" class="property-label"><g:message code="temaActividad.actividad.label" default="Actividad" /></span>
					
						<span class="property-value" aria-labelledby="actividad-label"><g:link controller="actividad" action="show" id="${temaActividadInstance?.actividad?.id}">${temaActividadInstance?.actividad?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${temaActividadInstance?.tema}">
				<li class="fieldcontain">
					<span id="tema-label" class="property-label"><g:message code="temaActividad.tema.label" default="Tema" /></span>
					
						<span class="property-value" aria-labelledby="tema-label"><g:link controller="tema" action="show" id="${temaActividadInstance?.tema?.id}">${temaActividadInstance?.tema?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form action="delete" method="DELETE" id="${temaActividadInstance.id}" params="['cursoId': cursoId, 'actividadId': actividadId]">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${temaActividadInstance}"
					id="${temaActividadInstance.id}" params="['cursoId': cursoId, 'actividadId': actividadId]">
						<g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" 
						onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
