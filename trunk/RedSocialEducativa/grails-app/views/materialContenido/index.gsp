
<%@ page import="com.fiuba.MaterialContenido" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'materialContenido.label', default: 'MaterialContenido')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-materialContenido" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-materialContenido" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<g:sortableColumn property="autor" title="${message(code: 'materialContenido.autor.label', default: 'Autor')}" />
					
						<th><g:message code="materialContenido.categoria.label" default="Categoria" /></th>
					
						<th><g:message code="materialContenido.contenido.label" default="Contenido" /></th>
					
						<g:sortableColumn property="descripcion" title="${message(code: 'materialContenido.descripcion.label', default: 'Descripcion')}" />
					
						<g:sortableColumn property="fecha" title="${message(code: 'materialContenido.fecha.label', default: 'Fecha')}" />
					
						<g:sortableColumn property="responsable" title="${message(code: 'materialContenido.responsable.label', default: 'Responsable')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${materialContenidoInstanceList}" status="i" var="materialContenidoInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${materialContenidoInstance.id}">${fieldValue(bean: materialContenidoInstance, field: "autor")}</g:link></td>
					
						<td>${fieldValue(bean: materialContenidoInstance, field: "categoria")}</td>
					
						<td>${fieldValue(bean: materialContenidoInstance, field: "contenido")}</td>
					
						<td>${fieldValue(bean: materialContenidoInstance, field: "descripcion")}</td>
					
						<td>${fieldValue(bean: materialContenidoInstance, field: "fecha")}</td>
					
						<td>${fieldValue(bean: materialContenidoInstance, field: "responsable")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${materialContenidoInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
