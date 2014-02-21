
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
				<li><a class="home" href="${createLink(uri: '/')}">
					<g:message code="default.home.label"/></a></li>
				<li><g:link class="list" controller="administrador" action="menu">
					<g:message code="Tareas administrativas"/></g:link></li>
				<li><g:link class="create" action="create">
					<g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-mediador" class="content scaffold-list" role="main">
			<h1><g:message code="Lista de mediadores" /></h1>
			<table>
			<thead>
					<tr>
						<g:sortableColumn property="usuario" title="${message(code: 'mediador.usuario.label', default: 'Usuario')}" />	
						<g:sortableColumn property="curso" title="${message(code: 'mediador.curso.label', default: 'Curso')}" />
						<g:sortableColumn property="jerarquia" title="${message(code: 'mediador.jerarquia.label', default: 'Jerarquia')}" />	
						<td> Detalle </td>	
					</tr>
				</thead>
				<tbody>
				<g:each in="${mediadorInstanceList}" status="i" var="mediadorInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
										
						<td>${fieldValue(bean: mediadorInstance, field: "usuario")}</td>
					
						<td>${fieldValue(bean: mediadorInstance, field: "curso")}</td>
					
						<td>${fieldValue(bean: mediadorInstance, field: "jerarquia")}</td>
						
						<td><g:link action="show" id="${mediadorInstance.id}">
							Ver detalle</g:link></td>
			
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
