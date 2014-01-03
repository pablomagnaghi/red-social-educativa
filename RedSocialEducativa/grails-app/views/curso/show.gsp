
<%@ page import="com.fiuba.Curso" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'curso.label', default: 'Curso')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-curso" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="indexMed"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-curso" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list curso">
			
				<g:if test="${cursoInstance?.cuatDict}">
				<li class="fieldcontain">
					<span id="cuatDict-label" class="property-label"><g:message code="curso.cuatDict.label" default="Cuat Dict" /></span>
					
						<span class="property-value" aria-labelledby="cuatDict-label"><g:fieldValue bean="${cursoInstance}" field="cuatDict"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${cursoInstance?.cuatrimestres}">
				<li class="fieldcontain">
					<span id="cuatrimestres-label" class="property-label"><g:message code="curso.cuatrimestres.label" default="Cuatrimestres" /></span>
					
						<g:each in="${cursoInstance.cuatrimestres}" var="c">
						<span class="property-value" aria-labelledby="cuatrimestres-label"><g:link controller="cuatrimestre" action="show" id="${c.id}">${c?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${cursoInstance?.mediadores}">
				<li class="fieldcontain">
					<span id="mediadores-label" class="property-label"><g:message code="curso.mediadores.label" default="Mediadores" /></span>
					
						<g:each in="${cursoInstance.mediadores}" var="m">
						<span class="property-value" aria-labelledby="mediadores-label"><g:link controller="mediador" action="show" id="${m.id}">${m?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${cursoInstance?.nroRelativo}">
				<li class="fieldcontain">
					<span id="nroRelativo-label" class="property-label"><g:message code="curso.nroRelativo.label" default="Nro Relativo" /></span>
					
						<span class="property-value" aria-labelledby="nroRelativo-label"><g:fieldValue bean="${cursoInstance}" field="nroRelativo"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:cursoInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${cursoInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
