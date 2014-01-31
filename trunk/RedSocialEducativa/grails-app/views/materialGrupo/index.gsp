
<%@ page import="com.fiuba.MaterialGrupo" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'materialGrupo.label', default: 'MaterialGrupo')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-materialGrupo" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-materialGrupo" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<g:sortableColumn property="autor" title="${message(code: 'materialGrupo.autor.label', default: 'Autor')}" />
					
						<th><g:message code="materialGrupo.categoria.label" default="Categoria" /></th>
					
						<g:sortableColumn property="descripcion" title="${message(code: 'materialGrupo.descripcion.label', default: 'Descripcion')}" />
					
						<g:sortableColumn property="fecha" title="${message(code: 'materialGrupo.fecha.label', default: 'Fecha')}" />
					
						<th><g:message code="materialGrupo.grupo.label" default="Grupo" /></th>
					
						<g:sortableColumn property="responsable" title="${message(code: 'materialGrupo.responsable.label', default: 'Responsable')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${materialGrupoInstanceList}" status="i" var="materialGrupoInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${materialGrupoInstance.id}">${fieldValue(bean: materialGrupoInstance, field: "autor")}</g:link></td>
					
						<td>${fieldValue(bean: materialGrupoInstance, field: "categoria")}</td>
					
						<td>${fieldValue(bean: materialGrupoInstance, field: "descripcion")}</td>
					
						<td>${fieldValue(bean: materialGrupoInstance, field: "fecha")}</td>
					
						<td>${fieldValue(bean: materialGrupoInstance, field: "grupo")}</td>
					
						<td>${fieldValue(bean: materialGrupoInstance, field: "responsable")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${materialGrupoInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
