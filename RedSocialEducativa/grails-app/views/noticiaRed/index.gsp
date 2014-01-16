
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
				<li><g:link class="list" controller="administrador" action="general">
					<g:message code="Tareas administrativas"/></g:link></li>
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
					
						<th><g:message code="noticiaRed.administrador.label" default="Administrador" /></th>
					
						<g:sortableColumn property="fecha" title="${message(code: 'noticiaRed.fecha.label', default: 'Fecha')}" />
				
						<g:sortableColumn property="titulo" title="${message(code: 'noticiaRed.titulo.label', default: 'Titulo')}" />
						
						<td> Detalle </td>	
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${noticiaRedInstanceList}" status="i" var="noticiaRedInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td>${fieldValue(bean: noticiaRedInstance, field: "administrador")}</td>
					
						<td><g:formatDate date="${noticiaRedInstance.fecha}" /></td>
					
						<td>${fieldValue(bean: noticiaRedInstance, field: "titulo")}</td>
					
						<td><g:link action="show" id="${noticiaRedInstance.id}">
							Ver detalle</g:link></td>
					
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
