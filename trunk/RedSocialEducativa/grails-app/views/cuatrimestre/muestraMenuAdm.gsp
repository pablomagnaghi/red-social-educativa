
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
				<li><g:link class="list" controller="administrador" action="general">
					<g:message code="Tareas administrativas"/></g:link></li>	
			</ul>
		</div>
		<h4>PARAMS: ${params}</h4>
		<div id="show-cuatrimestre" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list cuatrimestre">
			
			
				<g:if test="${cuatrimestreInstance?.curso}">
				<li class="fieldcontain">
					<span id="curso-label" class="property-label">
						<g:message code="cuatrimestre.curso.label" default="Curso" /></span>
					<span class="property-value" aria-labelledby="curso-label">
						<g:link controller="curso" action="show" id="${cuatrimestreInstance?.curso?.id}">
							${cuatrimestreInstance?.curso?.encodeAsHTML()}</g:link></span>
				</li>
				</g:if>
			
				<g:if test="${cuatrimestreInstance?.anio}">
				<li class="fieldcontain">
					<span id="anio-label" class="property-label">
						<g:message code="cuatrimestre.anio.label" default="Anio" /></span>
					<span class="property-value" aria-labelledby="anio-label">
						<g:formatNumber number="${cuatrimestreInstance.anio}"/></span>
				</li>
				</g:if>
			
			
				<g:if test="${cuatrimestreInstance?.numero}">
				<li class="fieldcontain">
					<span id="numero-label" class="property-label">
						<g:message code="cuatrimestre.numero.label" default="Numero" /></span>
					<span class="property-value" aria-labelledby="numero-label">
						<g:fieldValue bean="${cuatrimestreInstance}" field="numero"/></span>
				</li>
				</g:if>
			
				<g:if test="${cuatrimestreInstance?.habGrupos}">
				<li class="fieldcontain">
					<span id="habGrupos-label" class="property-label">
						<g:message code="cuatrimestre.habGrupos.label" default="Hab Grupos" /></span>				
					<span class="property-value" aria-labelledby="habGrupos-label">
						<g:formatBoolean boolean="${cuatrimestreInstance?.habGrupos}" /></span>
				</li>
				</g:if>
			
				<g:if test="${cuatrimestreInstance?.nroUltGrupo}">
				<li class="fieldcontain">
					<span id="nroUltGrupo-label" class="property-label">
						<g:message code="cuatrimestre.nroUltGrupo.label" default="Nro Ult Grupo" /></span>
					<span class="property-value" aria-labelledby="nroUltGrupo-label">
						<g:fieldValue bean="${cuatrimestreInstance}" field="nroUltGrupo"/></span>		
				</li>
				</g:if>
			
				<g:if test="${cuatrimestreInstance?.aprendices}">
				<li class="fieldcontain">
					<span id="aprendices-label" class="property-label">
						<g:message code="cuatrimestre.aprendices.label" default="Aprendices" /></span>
					<g:each in="${cuatrimestreInstance.aprendices}" var="a">
						<span class="property-value" aria-labelledby="aprendices-label">
							<g:link controller="usuario" action="show" id="${a.usuario.id}">${a?.encodeAsHTML()}</g:link></span>
					</g:each>
				</li>
				</g:if>
	
			</ol>
		</div>
	</body>
</html>
