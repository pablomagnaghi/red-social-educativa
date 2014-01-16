
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
			
				<g:if test="${aprendizInstance?.ultVisita}">
				<li class="fieldcontain">
					<span id="ultVisita-label" class="property-label"><g:message code="aprendiz.ultVisita.label" default="Ult Visita" /></span>
					
						<span class="property-value" aria-labelledby="ultVisita-label"><g:formatDate date="${aprendizInstance?.ultVisita}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${aprendizInstance?.curso}">
				<li class="fieldcontain">
					<span id="curso-label" class="property-label"><g:message code="aprendiz.curso.label" default="Curso" /></span>
					
						<span class="property-value" aria-labelledby="curso-label"><g:link controller="curso" action="show" id="${aprendizInstance?.curso?.id}">${aprendizInstance?.curso?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${aprendizInstance?.descMaterial}">
				<li class="fieldcontain">
					<span id="descMaterial-label" class="property-label"><g:message code="aprendiz.descMaterial.label" default="Desc Material" /></span>
					
						<span class="property-value" aria-labelledby="descMaterial-label"><g:fieldValue bean="${aprendizInstance}" field="descMaterial"/></span>
					
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
			
				<g:if test="${aprendizInstance?.usuario}">
				<li class="fieldcontain">
					<span id="usuario-label" class="property-label"><g:message code="aprendiz.usuario.label" default="Usuario" /></span>
					
						<span class="property-value" aria-labelledby="usuario-label"><g:link controller="usuario" action="show" id="${aprendizInstance?.usuario?.id}">${aprendizInstance?.usuario?.encodeAsHTML()}</g:link></span>
					
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
