
<%@ page import="com.fiuba.NoticiaCurso" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'noticiaCurso.label', default: 'NoticiaCurso')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-noticiaCurso" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-noticiaCurso" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<th><g:message code="noticiaCurso.curso.label" default="Curso" /></th>
					
						<g:sortableColumn property="fecha" title="${message(code: 'noticiaCurso.fecha.label', default: 'Fecha')}" />
					
						<th><g:message code="noticiaCurso.mediador.label" default="Mediador" /></th>
					
						<g:sortableColumn property="texto" title="${message(code: 'noticiaCurso.texto.label', default: 'Texto')}" />
					
						<g:sortableColumn property="titulo" title="${message(code: 'noticiaCurso.titulo.label', default: 'Titulo')}" />
					
						<g:sortableColumn property="visibilidad" title="${message(code: 'noticiaCurso.visibilidad.label', default: 'Visibilidad')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${noticiaCursoInstanceList}" status="i" var="noticiaCursoInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${noticiaCursoInstance.id}">${fieldValue(bean: noticiaCursoInstance, field: "curso")}</g:link></td>
					
						<td><g:formatDate date="${noticiaCursoInstance.fecha}" /></td>
					
						<td>${fieldValue(bean: noticiaCursoInstance, field: "mediador")}</td>
					
						<td>${fieldValue(bean: noticiaCursoInstance, field: "texto")}</td>
					
						<td>${fieldValue(bean: noticiaCursoInstance, field: "titulo")}</td>
					
						<td><g:formatBoolean boolean="${noticiaCursoInstance.visibilidad}" /></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${noticiaCursoInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
