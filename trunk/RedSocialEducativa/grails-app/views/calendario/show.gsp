
<%@ page import="com.fiuba.Calendario" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'calendario.label', default: 'Calendario')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-calendario" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="general" controller="administrador">
						<g:message code="Volver a tareas administrativas" args="[entityName]" /></g:link></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-calendario" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list calendario">

				<g:if test="${calendarioInstance?.inicioPrimerCuatrimestre}">
				<li class="fieldcontain">
					<span id="inicioPrimerCuatrimestre-label" class="property-label">
						<g:message code="calendario.inicioPrimerCuatrimestre.label" default="Inicio Primer Cuatrimestre" /></span>
					<span class="property-value" aria-labelledby="inicioPrimerCuatrimestre-label">
						<g:formatNumber number="${calendarioInstance.inicioPrimerCuatrimestre}"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${calendarioInstance?.inicioSegundoCuatrimestre}">
				<li class="fieldcontain">
					<span id="inicioSegundoCuatrimestre-label" class="property-label">
						<g:message code="calendario.inicioSegundoCuatrimestre.label" default="Inicio Segundo Cuatrimestre" /></span>
					<span class="property-value" aria-labelledby="inicioSegundoCuatrimestre-label">						
						<g:formatNumber number="${calendarioInstance.inicioSegundoCuatrimestre}"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:calendarioInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${calendarioInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
