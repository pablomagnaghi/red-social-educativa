
<%@ page import="com.fiuba.Aprendiz" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'aprendiz.label', default: 'Aprendiz')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-aprendiz" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-aprendiz" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list aprendiz">
			
				<g:if test="${aprendizInstance?.cuatrismestres}">
				<li class="fieldcontain">
					<span id="cuatrismestres-label" class="property-label"><g:message code="aprendiz.cuatrismestres.label" default="Cuatrismestres" /></span>
					
						<g:each in="${aprendizInstance.cuatrismestres}" var="c">
						<span class="property-value" aria-labelledby="cuatrismestres-label"><g:link controller="cuatrimestre" action="show" id="${c.id}">${c?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${aprendizInstance?.descMaterial}">
				<li class="fieldcontain">
					<span id="descMaterial-label" class="property-label"><g:message code="aprendiz.descMaterial.label" default="Desc Material" /></span>
					
						<span class="property-value" aria-labelledby="descMaterial-label"><g:fieldValue bean="${aprendizInstance}" field="descMaterial"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${aprendizInstance?.membresia}">
				<li class="fieldcontain">
					<span id="membresia-label" class="property-label"><g:message code="aprendiz.membresia.label" default="Membresia" /></span>
					
						<span class="property-value" aria-labelledby="membresia-label"><g:link controller="membresia" action="solicitarMembresia" id="${aprendizInstance?.membresia?.id}">${aprendizInstance?.membresia?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${aprendizInstance?.msjEnviados}">
				<li class="fieldcontain">
					<span id="msjEnviados-label" class="property-label"><g:message code="aprendiz.msjEnviados.label" default="Msj Enviados" /></span>
					
						<span class="property-value" aria-labelledby="msjEnviados-label"><g:fieldValue bean="${aprendizInstance}" field="msjEnviados"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${aprendizInstance?.msjLeidos}">
				<li class="fieldcontain">
					<span id="msjLeidos-label" class="property-label"><g:message code="aprendiz.msjLeidos.label" default="Msj Leidos" /></span>
					
						<span class="property-value" aria-labelledby="msjLeidos-label"><g:fieldValue bean="${aprendizInstance}" field="msjLeidos"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${aprendizInstance?.participa}">
				<li class="fieldcontain">
					<span id="participa-label" class="property-label"><g:message code="aprendiz.participa.label" default="Participa" /></span>
					
						<span class="property-value" aria-labelledby="participa-label"><g:formatBoolean boolean="${aprendizInstance?.participa}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${aprendizInstance?.pubForos}">
				<li class="fieldcontain">
					<span id="pubForos-label" class="property-label"><g:message code="aprendiz.pubForos.label" default="Pub Foros" /></span>
					
						<span class="property-value" aria-labelledby="pubForos-label"><g:fieldValue bean="${aprendizInstance}" field="pubForos"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${aprendizInstance?.rol}">
				<li class="fieldcontain">
					<span id="rol-label" class="property-label"><g:message code="aprendiz.rol.label" default="Rol" /></span>
					
						<span class="property-value" aria-labelledby="rol-label"><g:link controller="rol" action="show" id="${aprendizInstance?.rol?.id}">${aprendizInstance?.rol?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${aprendizInstance?.ultVisita}">
				<li class="fieldcontain">
					<span id="ultVisita-label" class="property-label"><g:message code="aprendiz.ultVisita.label" default="Ult Visita" /></span>
					
						<span class="property-value" aria-labelledby="ultVisita-label"><g:formatDate date="${aprendizInstance?.ultVisita}" /></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:aprendizInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${aprendizInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
