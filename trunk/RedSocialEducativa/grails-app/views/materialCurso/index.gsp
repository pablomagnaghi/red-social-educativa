
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
				<li><a class="home" href="${createLink(uri: '/')}">
					<g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="menuMediador" controller="curso" params="['cursoId': cursoId]">
					<g:message code="Tareas mediador" /></g:link></li>
				<li><g:link class="create" action="create" params="['cursoId': cursoId]">
					<g:message code="default.new.label" args="[entityName]" /></g:link></li>	
			</ul>
		</div>

		<h2>Curso: ${com.fiuba.Curso.get(cursoId)}</h2>
		<h2>Curso Id: ${cursoId}</h2>
		
		<div id="list-materialCurso" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
						<g:sortableColumn property="titulo" title="${message(code: 'materialCurso.titulo.label', default: 'Titulo')}" />
	
						<th><g:message code="materialCurso.categoria.label" default="Categoria" /></th>
						
						<g:sortableColumn property="responsable" title="${message(code: 'materialCurso.responsable.label', default: 'Responsable')}" />
					
						<g:sortableColumn property="fecha" title="${message(code: 'materialCurso.fecha.label', default: 'Fecha')}" />
		
						<td> Detalle </td>	
					</tr>
				</thead>
				<tbody>
				<g:each in="${materialCursoInstanceList}" status="i" var="materialCursoInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td>${fieldValue(bean: materialCursoInstance, field: "titulo")}</td>
					
						<td>${fieldValue(bean: materialCursoInstance, field: "categoria")}</td>
					
						<td>${fieldValue(bean: materialCursoInstance, field: "responsable")}</td>
					
						<td>${fieldValue(bean: materialCursoInstance, field: "fecha")}</td>		
						
						<td><g:link action="show" id="${materialCursoInstance.id}">Ver detalle</g:link></td>
						
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${materialCursoInstanceCount ?: 0}" params="['cursoId': cursoId]" />
			</div>
		</div>
	</body>
</html>
