
<%@ page import="com.fiuba.Cuatrimestre" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'cuatrimestre.label', default: 'Cuatrimestre')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-cuatrimestre" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}">
					<g:message code="default.home.label"/></a></li>
				<li><g:link class="list" controller="administrador" action="general">
					<g:message code="Tareas administrativas"/></g:link></li>		
				<li><g:link class="create" action="create">
					<g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-cuatrimestre" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
						<th><g:message code="cuatrimestre.curso.label" default="Curso" /></th>					
						<g:sortableColumn property="anio" title="${message(code: 'cuatrimestre.anio.label', default: 'Anio')}" />
						<g:sortableColumn property="numero" title="${message(code: 'cuatrimestre.numero.label', default: 'Numero')}" />
					
						<td>Detalle</td>
					</tr>
				</thead>
				<tbody>
				<g:each in="${cuatrimestreInstanceList}" status="i" var="cuatrimestreInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
					
						<td>${fieldValue(bean: cuatrimestreInstance, field: "curso")}</td>
						<td><g:formatNumber number="${cuatrimestreInstance.anio}"/></td>
						<td>${fieldValue(bean: cuatrimestreInstance, field: "numero")}</td>
					
				
						<td><g:link action="show" id="${cuatrimestreInstance.id}">Ver detalle</g:link></td>

					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${cuatrimestreInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
