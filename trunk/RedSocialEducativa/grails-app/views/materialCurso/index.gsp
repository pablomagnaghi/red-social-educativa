
<%@ page import="com.fiuba.MaterialCurso" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'materialCurso.label', default: 'MaterialCurso')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-materialCurso" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-materialCurso" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<g:sortableColumn property="autor" title="${message(code: 'materialCurso.autor.label', default: 'Autor')}" />
					
						<th><g:message code="materialCurso.categoria.label" default="Categoria" /></th>
					
						<th><g:message code="materialCurso.curso.label" default="Curso" /></th>
					
						<g:sortableColumn property="descripcion" title="${message(code: 'materialCurso.descripcion.label', default: 'Descripcion')}" />
					
						<g:sortableColumn property="fecha" title="${message(code: 'materialCurso.fecha.label', default: 'Fecha')}" />
					
						<g:sortableColumn property="responsable" title="${message(code: 'materialCurso.responsable.label', default: 'Responsable')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${materialCursoInstanceList}" status="i" var="materialCursoInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${materialCursoInstance.id}">${fieldValue(bean: materialCursoInstance, field: "autor")}</g:link></td>
					
						<td>${fieldValue(bean: materialCursoInstance, field: "categoria")}</td>
					
						<td>${fieldValue(bean: materialCursoInstance, field: "curso")}</td>
					
						<td>${fieldValue(bean: materialCursoInstance, field: "descripcion")}</td>
					
						<td>${fieldValue(bean: materialCursoInstance, field: "fecha")}</td>
					
						<td>${fieldValue(bean: materialCursoInstance, field: "responsable")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${materialCursoInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
