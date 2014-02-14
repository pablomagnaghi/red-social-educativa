
<%@ page import="com.fiuba.Actividad" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'actividad.label', default: 'actividad')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-actividad" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}">
					<g:message code="default.home.label"/></a></li>
				<g:if test="${aprendizId}">
					<li><g:link class="list" controller="curso" action="aprendiz" params="['cursoId': params.cursoId]">
						<g:message code="Menu aprendiz del curso ${com.fiuba.Curso.get(params.cuatrimestreId)}" args="[entityName]" /></g:link></li>
				</g:if>
				<g:else>
					<li><g:link class="list" controller="curso" action="mediador" params="['cursoId': params.cursoId]">
						<g:message code="Menu mediador  del curso ${com.fiuba.Curso.get(params.cursoId)}" args="[entityName]" /></g:link></li>
				</g:else>	
				<li><g:link class="list" controller="curso" action="actividades" params="['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId]">
						<g:message code="Actividades  del curso ${com.fiuba.Curso.get(params.cursoId)}" args="[entityName]" /></g:link></li>
			</ul>
		</div>
				<h3>curso: ${params.cursoId}</h3>
		<h3>cuatri: ${params.cuatrimestreId}</h3>
		<h3>act: ${params.actividadId}</h3>
		<div>
			<h1><g:message code="Actividad: ${actividad.titulo}" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
		</div>
		<div>
			<p>Categoria": ${actividad.categoria}</p>	
		</div>
		
		<div>
			<hr>
			<h2><g:link controller="materialActividad" action="general" params="['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 'actividadId': params.actividadId]">
				<g:message code="Material de la actividad"/></g:link></h2>
		</div>
		<div>
			<hr>
			<h2><g:link controller="temaActividad" action="general" params="['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 'actividadId': params.actividadId]">
				<g:message code="Temas asociados con la actividad"/></g:link></h2>
		</div>
		<div>
		
				<li><g:link class="list" action="menuAprendiz" controller="grupoActividad" params="['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 'actividadId': params.actividadId]">
					<g:message code="Los grupos de la actividad"/></g:link></li>
				<g:if test="${grupoActividadAprendiz}">	
					<li><g:link class="list" action="muestraAprendiz" controller="grupoActividad" id="${grupoActividadAprendiz.grupo.id}" 
						params="['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 'actividadId': params.actividadId]">
						<g:message code="Mi grupo ${grupoActividadAprendiz.grupo}"/></g:link></li>
				</g:if>

		</div>
	</body>
</html>
