
<%@ page import="com.encuesta.PreguntaDesarrollo" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'preguntaDesarrollo.label', default: 'PreguntaDesarrollo')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-preguntaDesarrollo" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-preguntaDesarrollo" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<g:sortableColumn property="pregunta" title="${message(code: 'preguntaDesarrollo.pregunta.label', default: 'Pregunta')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${preguntaDesarrolloInstanceList}" status="i" var="preguntaDesarrolloInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${preguntaDesarrolloInstance.id}">${fieldValue(bean: preguntaDesarrolloInstance, field: "pregunta")}</g:link></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${preguntaDesarrolloInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
