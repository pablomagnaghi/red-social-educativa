
<%@ page import="com.fiuba.MaterialTema" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'materialTema.label', default: 'MaterialTema')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-materialTema" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-materialTema" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<g:sortableColumn property="autor" title="${message(code: 'materialTema.autor.label', default: 'Autor')}" />
					
						<th><g:message code="materialTema.categoria.label" default="Categoria" /></th>
					
						<g:sortableColumn property="descripcion" title="${message(code: 'materialTema.descripcion.label', default: 'Descripcion')}" />
					
						<g:sortableColumn property="fecha" title="${message(code: 'materialTema.fecha.label', default: 'Fecha')}" />
					
						<g:sortableColumn property="responsable" title="${message(code: 'materialTema.responsable.label', default: 'Responsable')}" />
					
						<th><g:message code="materialTema.tema.label" default="Tema" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${materialTemaInstanceList}" status="i" var="materialTemaInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${materialTemaInstance.id}">${fieldValue(bean: materialTemaInstance, field: "autor")}</g:link></td>
					
						<td>${fieldValue(bean: materialTemaInstance, field: "categoria")}</td>
					
						<td>${fieldValue(bean: materialTemaInstance, field: "descripcion")}</td>
					
						<td>${fieldValue(bean: materialTemaInstance, field: "fecha")}</td>
					
						<td>${fieldValue(bean: materialTemaInstance, field: "responsable")}</td>
					
						<td>${fieldValue(bean: materialTemaInstance, field: "tema")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${materialTemaInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
