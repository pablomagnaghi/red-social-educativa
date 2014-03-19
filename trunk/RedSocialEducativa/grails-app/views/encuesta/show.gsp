
<%@ page import="com.encuesta.Encuesta" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'encuesta.label', default: 'Encuesta')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-encuesta" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-encuesta" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list encuesta">
			
				<g:if test="${encuestaInstance?.nombre}">
				<li class="fieldcontain">
					<span id="nombre-label" class="property-label"><g:message code="encuesta.nombre.label" default="Nombre" /></span>
					
						<span class="property-value" aria-labelledby="nombre-label"><g:fieldValue bean="${encuestaInstance}" field="nombre"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${encuestaInstance?.curso}">
				<li class="fieldcontain">
					<span id="curso-label" class="property-label"><g:message code="encuesta.curso.label" default="Curso" /></span>
					
						<span class="property-value" aria-labelledby="curso-label"><g:link controller="curso" action="show" id="${encuestaInstance?.curso?.id}">${encuestaInstance?.curso?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${encuestaInstance?.preguntas}">
				<li class="fieldcontain">
					<span id="preguntas-label" class="property-label"><g:message code="encuesta.preguntas.label" default="Preguntas" /></span>
					
						<g:each in="${encuestaInstance.preguntas}" var="p">
						<span class="property-value" aria-labelledby="preguntas-label"><g:link controller="pregunta" action="show" id="${p.id}">${p?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${encuestaInstance?.preguntasChoice}">
				<li class="fieldcontain">
					<span id="preguntasChoice-label" class="property-label"><g:message code="encuesta.preguntasChoice.label" default="Preguntas Choice" /></span>
					
						<g:each in="${encuestaInstance.preguntasChoice}" var="p">
						<span class="property-value" aria-labelledby="preguntasChoice-label"><g:link controller="preguntaChoice" action="show" id="${p.id}">${p?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${encuestaInstance?.preguntasPuntaje}">
				<li class="fieldcontain">
					<span id="preguntasPuntaje-label" class="property-label"><g:message code="encuesta.preguntasPuntaje.label" default="Preguntas Puntaje" /></span>
					
						<g:each in="${encuestaInstance.preguntasPuntaje}" var="p">
						<span class="property-value" aria-labelledby="preguntasPuntaje-label"><g:link controller="preguntaPuntaje" action="show" id="${p.id}">${p?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:encuestaInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${encuestaInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
