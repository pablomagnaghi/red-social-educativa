
<%@ page import="com.fiuba.Mediador" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'mediador.label', default: 'Mediador')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-mediador" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-mediador" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list mediador">
			
				<g:if test="${mediadorInstance?.cursos}">
				<li class="fieldcontain">
					<span id="cursos-label" class="property-label"><g:message code="mediador.cursos.label" default="Cursos" /></span>
					
						<g:each in="${mediadorInstance.cursos}" var="c">
						<span class="property-value" aria-labelledby="cursos-label"><g:link controller="curso" action="show" id="${c.id}">${c?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${mediadorInstance?.jerarquia}">
				<li class="fieldcontain">
					<span id="jerarquia-label" class="property-label"><g:message code="mediador.jerarquia.label" default="Jerarquia" /></span>
					
						<span class="property-value" aria-labelledby="jerarquia-label"><g:fieldValue bean="${mediadorInstance}" field="jerarquia"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${mediadorInstance?.membresia}">
				<li class="fieldcontain">
					<span id="membresia-label" class="property-label"><g:message code="mediador.membresia.label" default="Membresia" /></span>
					
						<span class="property-value" aria-labelledby="membresia-label"><g:link controller="membresia" action="solicitarMembresia" id="${mediadorInstance?.membresia?.id}">${mediadorInstance?.membresia?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${mediadorInstance?.rol}">
				<li class="fieldcontain">
					<span id="rol-label" class="property-label"><g:message code="mediador.rol.label" default="Rol" /></span>
					
						<span class="property-value" aria-labelledby="rol-label"><g:link controller="rol" action="show" id="${mediadorInstance?.rol?.id}">${mediadorInstance?.rol?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:mediadorInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${mediadorInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
