
<%@ page import="com.fiuba.NoticiaRed" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'noticiaRed.label', default: 'NoticiaRed')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-noticiaRed" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}">
					<g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create">
					<g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-noticiaRed" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
				
						<g:sortableColumn property="titulo" title="${message(code: 'noticiaRed.titulo.label', default: 'Titulo')}" />
						<g:sortableColumn property="fecha" title="${message(code: 'noticiaRed.fecha.label', default: 'Fecha')}" />
						<g:sortableColumn property="hora" title="${message(code: 'noticiaRed.hora.label', default: 'Hora')}" />
						<g:sortableColumn property="administrador" title="${message(code: 'noticiaRed.hora.label', default: 'Administrador')}" />
						<g:sortableColumn property="visibilidad" title="${message(code: 'noticiaRed.hora.label', default: 'Visibilidad')}" />
						<g:sortableColumn property="texo" title="${message(code: 'noticiaRed.hora.label', default: 'Texto')}" />
						<td> Opciones </td>	
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${noticiaRedInstanceList}" status="i" var="noticiaRedInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td>${fieldValue(bean: noticiaRedInstance, field: "titulo")}</td>
						<td>${fieldValue(bean: noticiaRedInstance, field: "fecha")}</td>
						<td>${fieldValue(bean: noticiaRedInstance, field: "hora")}</td>
						<td>${fieldValue(bean: noticiaRedInstance, field: "administrador.usuario")}</td>
						<td>${fieldValue(bean: noticiaRedInstance, field: "visibilidad")}</td>
						<td>${fieldValue(bean: noticiaRedInstance, field: "texto")}</td>
						
						<td>
							<g:form action="delete" method="DELETE" id="${noticiaRedInstance.id}">
							<fieldset class="buttons">
								<g:link class="edit" action="edit" resource="${noticiaRedInstance}" id="${noticiaRedInstance.id}"> 
									<g:message code="default.button.edit.label" default="Edit" /></g:link>
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
				<g:paginate total="${noticiaRedInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
