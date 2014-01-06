
<%@ page import="com.fiuba.Mediador" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'mediador.label', default: 'Mediador')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-mediador" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-mediador" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<g:sortableColumn property="jerarquia" title="${message(code: 'mediador.jerarquia.label', default: 'Jerarquia')}" />
					
						<th><g:message code="mediador.membresia.label" default="Membresia" /></th>
					
						<th><g:message code="mediador.rol.label" default="Rol" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${mediadorInstanceList}" status="i" var="mediadorInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${mediadorInstance.id}">${fieldValue(bean: mediadorInstance, field: "jerarquia")}</g:link></td>
					
						<td>${fieldValue(bean: mediadorInstance, field: "membresia")}</td>
					
						<td>${fieldValue(bean: mediadorInstance, field: "rol")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${mediadorInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
