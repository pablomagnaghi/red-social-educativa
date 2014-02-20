
<%@ page import="com.fiuba.Evaluacion" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'evaluacion.label', default: 'Evaluacion')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-evaluacion" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}">
					<g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="menuMediador" controller="curso" params="['cursoId': params.cursoId]">
					<g:message code="Tareas mediador" /></g:link></li>
				<li><g:link class="create" action="create" params="['cursoId': params.cursoId]">
					<g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<h3>curso: ${params.cursoId}</h3>
		<h3>cuatri: ${params.cuatrimestreId}</h3>
		<div id="list-evaluacion" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<g:sortableColumn property="aula" title="${message(code: 'evaluacion.aula.label', default: 'Aula')}" />
					
						<g:sortableColumn property="descripcion" title="${message(code: 'evaluacion.descripcion.label', default: 'Descripcion')}" />
					
						<g:sortableColumn property="fecha" title="${message(code: 'evaluacion.fecha.label', default: 'Fecha')}" />
					
						<g:sortableColumn property="habilitada" title="${message(code: 'evaluacion.habilitada.label', default: 'Habilitada')}" />
					
						<g:sortableColumn property="horario" title="${message(code: 'evaluacion.horario.label', default: 'Horario')}" />
						
						<td>Detalle</td>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${evaluacionInstanceList}" status="i" var="evaluacionInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${evaluacionInstance.id}"  params="['cursoId': params.cursoId]">${fieldValue(bean: evaluacionInstance, field: "aula")}</g:link></td>
					
						<td>${fieldValue(bean: evaluacionInstance, field: "descripcion")}</td>
					
						<td>${fieldValue(bean: evaluacionInstance, field: "fecha")}</td>
					
						<td><g:formatBoolean boolean="${evaluacionInstance.habilitada}" /></td>
					
						<td>${fieldValue(bean: evaluacionInstance, field: "horario")}</td>
						
						<td><g:link action="show" id="${evaluacionInstance.id}"  params="['cursoId': params.cursoId]">Ver detalle ${evaluacionInstance.id}</g:link></td>
					
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${evaluacionInstanceCount ?: 0}" params="['cursoId': params.cursoId]"/>
			</div>
		</div>
	</body>
</html>
