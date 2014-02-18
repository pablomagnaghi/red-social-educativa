
<%@ page import="com.fiuba.Cuatrimestre" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'cuatrimestre.label', default: 'Cuatrimestre')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-cuatrimestre" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}">
					<g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="menuMediador" controller="curso" params="['cursoId': params.cursoId]">
					<g:message code="Tareas mediador" /></g:link></li>	
				<li><g:link class="list" action="indexHistoriales" params="['cursoId': params.cursoId]">
					<g:message code="default.list.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<h2>Curso: ${com.fiuba.Curso.get(params.cursoId)}</h2>
		<h2>Curso Id: ${params.cursoId}</h2>
		<div id="show-cuatrimestre" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list cuatrimestre">
			
				<g:if test="${cuatrimestreInstance?.actividades}">
				<li class="fieldcontain">
					<span id="actividades-label" class="property-label"><g:message code="cuatrimestre.actividades.label" default="Actividades" /></span>
					
						<g:each in="${cuatrimestreInstance.actividades}" var="a">
						<span class="property-value" aria-labelledby="actividades-label"><g:link controller="actividad" action="show" id="${a.id}">${a?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${cuatrimestreInstance?.anio}">
				<li class="fieldcontain">
					<span id="anio-label" class="property-label"><g:message code="cuatrimestre.anio.label" default="Anio" /></span>
					
						<span class="property-value" aria-labelledby="anio-label">
							<g:formatNumber number="${cuatrimestreInstance.anio}"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${cuatrimestreInstance?.aprendices}">
				<li class="fieldcontain">
					<span id="aprendices-label" class="property-label"><g:message code="cuatrimestre.aprendices.label" default="Aprendices" /></span>
					
						<g:each in="${cuatrimestreInstance.aprendices}" var="a">
						<span class="property-value" aria-labelledby="aprendices-label">
							<g:link controller="usuario" action="muestraMenuMed" id="${a.usuario.id}"
								params="['cursoId': params.cursoId, 'cuatrimestreId': cuatrimestreInstance.id]">${a?.encodeAsHTML()}</g:link></span>
							<p>Mostrar si participa y cursa sacar de curso</p>
							<p>agregar la opcion de dejar de participar</p>
						</g:each>
					
				</li>
				</g:if>
					
				<g:if test="${cuatrimestreInstance?.foro}">
				<li class="fieldcontain">
					<span id="foro-label" class="property-label"><g:message code="cuatrimestre.foro.label" default="Foro" /></span>
					
						<span class="property-value" aria-labelledby="foro-label">
							<g:link controller="foroCurso" action="general" id="${cuatrimestreInstance?.foro?.id}" 
								params="['cursoId': cuatrimestreInstance.curso.id, 'cuatrimestreId': cuatrimestreInstance.id]">		
								${cuatrimestreInstance?.foro?.encodeAsHTML()}</g:link></span>
				</li>
				</g:if>
			
				<g:if test="${cuatrimestreInstance?.grupos}">
				<li class="fieldcontain">
					<span id="grupos-label" class="property-label"><g:message code="cuatrimestre.grupos.label" default="Grupos" /></span>
					
						<g:each in="${cuatrimestreInstance.grupos}" var="g">
						<span class="property-value" aria-labelledby="grupos-label"><g:link controller="grupoCurso" action="show" id="${g.id}">${g?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${cuatrimestreInstance?.habGrupos}">
				<li class="fieldcontain">
					<span id="habGrupos-label" class="property-label"><g:message code="cuatrimestre.habGrupos.label" default="Hab Grupos" /></span>
					
						<span class="property-value" aria-labelledby="habGrupos-label"><g:formatBoolean boolean="${cuatrimestreInstance?.habGrupos}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${cuatrimestreInstance?.noticiasCurso}">
				<li class="fieldcontain">
					<span id="noticiasCurso-label" class="property-label"><g:message code="cuatrimestre.noticiasCurso.label" default="Noticias Curso" /></span>
					
						<g:each in="${cuatrimestreInstance.noticiasCurso}" var="n">
						<span class="property-value" aria-labelledby="noticiasCurso-label"><g:link controller="noticiaCurso" action="show" id="${n.id}">${n?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${cuatrimestreInstance?.nroUltGrupo}">
				<li class="fieldcontain">
					<span id="nroUltGrupo-label" class="property-label"><g:message code="cuatrimestre.nroUltGrupo.label" default="Nro Ult Grupo" /></span>
					
						<span class="property-value" aria-labelledby="nroUltGrupo-label"><g:fieldValue bean="${cuatrimestreInstance}" field="nroUltGrupo"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${cuatrimestreInstance?.numero}">
				<li class="fieldcontain">
					<span id="numero-label" class="property-label"><g:message code="cuatrimestre.numero.label" default="Numero" /></span>
					
						<span class="property-value" aria-labelledby="numero-label"><g:fieldValue bean="${cuatrimestreInstance}" field="numero"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form action="delete" method="DELETE" id="${cuatrimestreInstance.id}" params="['cursoId': params.cursoId]">
				<fieldset class="buttons">
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" 
						onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>