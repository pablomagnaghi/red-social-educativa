
<%@ page import="com.fiuba.Usuario" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'usuario.label', default: 'Usuario')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-usuario" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}">
					<g:message code="default.home.label"/></a></li>
				<li><g:link class="list" controller="administrador" action="general">
					<g:message code="Tareas administrativas"/></g:link></li>
			</ul>
		</div>
		<div id="list-usuario" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<g:sortableColumn property="username" title="${message(code: 'usuario.username.label', default: 'Username')}" />			
						<g:sortableColumn property="apellido" title="${message(code: 'usuario.apellido.label', default: 'Apellido')}" />					
						<g:sortableColumn property="nombres" title="${message(code: 'usuario.nombres.label', default: 'Nombres')}" />					
						<g:sortableColumn property="email" title="${message(code: 'usuario.email.label', default: 'Email')}" />
					
						<td> Activar usuario </td>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${usuarioInstanceList}" status="i" var="usuarioInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td>${fieldValue(bean: usuarioInstance, field: "username")}</td>
						<td>${fieldValue(bean: usuarioInstance, field: "apellido")}</td>				
						<td>${fieldValue(bean: usuarioInstance, field: "nombres")}</td>					
						<td>${fieldValue(bean: usuarioInstance, field: "email")}</td>
					
						<td><g:link action="activarUsuario" controller="administrador" id="${usuarioInstance.id}"
							onclick="return confirm('${message(code: '¿Esta seguro de activar al usuario?')}');">Activar</g:link></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${usuarioInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
