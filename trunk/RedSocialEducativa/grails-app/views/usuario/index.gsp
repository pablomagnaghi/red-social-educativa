
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
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
			</ul>
		</div>
		<div id="list-usuario" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<p>No se borran porque falta arreglar la cascada en mensajeria</p>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<g:sortableColumn property="username" title="${message(code: 'usuario.username.label', default: 'Username')}" />					
						<g:sortableColumn property="apellido" title="${message(code: 'usuario.apellido.label', default: 'Apellido')}" />					
						<g:sortableColumn property="nombres" title="${message(code: 'usuario.nombres.label', default: 'Nombres')}" />					
						<g:sortableColumn property="legajo" title="${message(code: 'usuario.legajo.label', default: 'Legajo')}" />				
						<g:sortableColumn property="padron" title="${message(code: 'usuario.padron.label', default: 'Padron')}" />
						<g:sortableColumn property="fechaSolicitud" title="${message(code: 'usuario.nombres.label', default: 'Fecha Solicitud')}" />					
						<td>Opciones</td>
					</tr>
				</thead>
				<tbody>
				<g:each in="${usuarioInstanceList}" status="i" var="usuarioInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td>${fieldValue(bean: usuarioInstance, field: "username")}</td>				
						<td>${fieldValue(bean: usuarioInstance, field: "apellido")}</td>				
						<td>${fieldValue(bean: usuarioInstance, field: "nombres")}</td>				
						<td>${fieldValue(bean: usuarioInstance, field: "legajo")}</td>				
						<td>${fieldValue(bean: usuarioInstance, field: "padron")}</td>
						<td>${fieldValue(bean: usuarioInstance, field: "fechaSolicitud")}</td>				
						<td><p>"${usuarioInstance.id}"</p>
							<g:form action="delete" method="DELETE" id="${usuarioInstance.id}">
							<fieldset class="buttons">
								<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" 
									onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
							</fieldset>
							</g:form>
						</td>

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
