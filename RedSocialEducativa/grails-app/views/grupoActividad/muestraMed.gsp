
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
				<li><g:link class="create" controller="curso" action="menuMediador" params="['cursoId': params.cursoId]">
					<g:message code="Tareas mediador" args="[entityName]" /></g:link></li>
				<li><g:link class="list" action="menuMed" params="['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 'actividadId': params.actividadId]">
					<g:message code="Lista de grupos" args="[entityName]" /></g:link></li>
			</ul>
		</div>
				<h2>Params: ${params}</h2>
		<h2>Curso: ${com.fiuba.Curso.get(params.cursoId)}</h2>
		<h2>Curso Id: ${params.cursoId}</h2>
		<h2>Cuatrimestre Id: ${params.cuatrimestreId}</h2>
		<h2>Actividad Id: ${params.actividadId}</h2>
		<div id="show-grupoCurso" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list grupoActividad">
			
				<g:if test="${grupoActividadInstance?.materiales}">
				<li class="fieldcontain">
					<span id="materiales-label" class="property-label">
						<g:message code="grupoActividad.materiales.label" default="Materiales" /></span>
					
						<g:each in="${grupoActividadInstance.materiales}" var="m">
						<span class="property-value" aria-labelledby="materiales-label">
							<g:link controller="materialGrupoActividad" action="muestraMediador" id="${m.id}"
								params="['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 'actividadId': params.actividadId, 
									'grupoActividadId': grupoActividadInstance.id]">${m?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${grupoActividadInstance?.aprendices}">
				<li class="fieldcontain">
					<span id="aprendices-label" class="property-label"><g:message code="grupoActividad.aprendices.label" default="Aprendices" /></span>
					
						<g:each in="${grupoActividadInstance.aprendices}" var="a">
						<span class="property-value" aria-labelledby="aprendices-label">
							<g:link controller="usuario" action="muestraMenuMed" id="${a.aprendiz.usuario.id}">${a?.encodeAsHTML()}</g:link></span>
							<g:if test="${com.fiuba.Actividad.get(params.actividadId).evaluable}">
								<g:link controller="grupoActividadAprendiz" action="calificar" 
									id="${com.fiuba.GrupoActividadAprendiz.findByAprendizAndGrupo(com.fiuba.Aprendiz.get(a.aprendiz.id), 
										 com.fiuba.GrupoActividad.get(grupoActividadInstance.id)).id}"								
									params="['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 'actividadId': params.actividadId, 
									'grupoActividadId': grupoActividadInstance.id]">
									Calificar</g:link>
									
									<p>Aprendiz Id: ${a.id} - ${com.fiuba.Aprendiz.get(a.aprendiz.id)}</p>
									<p>Grupo actividad Id: ${grupoActividadInstance.id} - ${com.fiuba.GrupoActividad.get(grupoActividadInstance.id)}</p>
									<p>Grupo actividad aprendiz Id: ${com.fiuba.GrupoActividadAprendiz.findByAprendizAndGrupo(com.fiuba.Aprendiz.get(a.aprendiz.id), 
										 com.fiuba.GrupoActividad.get(grupoActividadInstance.id)).id}</p>
							</g:if>
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
			
			<g:form action="eliminar" method="DELETE" id="${grupoActividadInstance.id}" params="['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 'actividadId': params.actividadId]">
				<fieldset class="buttons">
					<g:actionSubmit class="delete" action="eliminar" value="${message(code: 'default.button.delete.label', default: 'Delete')}" 
						onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
