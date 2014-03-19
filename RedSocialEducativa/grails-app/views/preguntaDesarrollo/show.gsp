
<%@ page import="com.encuesta.PreguntaDesarrollo" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'preguntaDesarrollo.label', default: 'PreguntaDesarrollo')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-preguntaDesarrollo" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-preguntaDesarrollo" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list preguntaDesarrollo">
			
				<g:if test="${preguntaDesarrolloInstance?.pregunta}">
				<li class="fieldcontain">
					<span id="pregunta-label" class="property-label"><g:message code="preguntaDesarrollo.pregunta.label" default="Pregunta" /></span>
					
						<span class="property-value" aria-labelledby="pregunta-label"><g:fieldValue bean="${preguntaDesarrolloInstance}" field="pregunta"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:preguntaDesarrolloInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${preguntaDesarrolloInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
