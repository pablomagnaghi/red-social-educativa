
<%@ page import="com.fiuba.EvaluacionAprendiz" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'evaluacionAprendiz.label', default: 'EvaluacionAprendiz')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-evaluacionAprendiz" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-evaluacionAprendiz" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<g:sortableColumn property="nota" title="${message(code: 'evaluacionAprendiz.nota.label', default: 'Nota')}" />
					
						<th><g:message code="evaluacionAprendiz.aprendiz.label" default="Aprendiz" /></th>
					
						<th><g:message code="evaluacionAprendiz.evaluacion.label" default="Evaluacion" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${evaluacionAprendizInstanceList}" status="i" var="evaluacionAprendizInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${evaluacionAprendizInstance.id}">${fieldValue(bean: evaluacionAprendizInstance, field: "nota")}</g:link></td>
					
						<td>${fieldValue(bean: evaluacionAprendizInstance, field: "aprendiz")}</td>
					
						<td>${fieldValue(bean: evaluacionAprendizInstance, field: "evaluacion")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${evaluacionAprendizInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
