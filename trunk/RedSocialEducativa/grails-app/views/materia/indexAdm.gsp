
<%@ page import="com.fiuba.Materia" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'materia.label', default: 'Materia')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-materia" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-materia" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<g:sortableColumn property="codigo" title="${message(code: 'materia.codigo.label', default: 'Codigo')}" />
					
						<g:sortableColumn property="contenidosMinimos" title="${message(code: 'materia.contenidosMinimos.label', default: 'Contenidos Minimos')}" />
					
						<g:sortableColumn property="creditos" title="${message(code: 'materia.creditos.label', default: 'Creditos')}" />
					
						<g:sortableColumn property="nombre" title="${message(code: 'materia.nombre.label', default: 'Nombre')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${materiaInstanceList}" status="i" var="materiaInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${materiaInstance.id}">${fieldValue(bean: materiaInstance, field: "codigo")}</g:link></td>
					
						<td>${fieldValue(bean: materiaInstance, field: "contenidosMinimos")}</td>
					
						<td>${fieldValue(bean: materiaInstance, field: "creditos")}</td>
					
						<td>${fieldValue(bean: materiaInstance, field: "nombre")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${materiaInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
