
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
				<li><a class="home" href="${createLink(uri: '/')}">
					<g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="mediador" controller="curso" params="['cursoId': params.cursoId]">
					<g:message code="Pagina principal de mediador"/></g:link></li>	
				<li><g:link class="create" action="menuMediador" controller="curso" params="['cursoId': params.cursoId]">
					<g:message code="Tareas mediador" /></g:link></li>
				<li><g:link class="create" action="create" params="['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId]">
					<g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		
		<h2>Params: ${params}</h2>
		<h2>Curso: ${com.fiuba.Curso.get(params.cursoId)}</h2>
		<h2>Curso Id: ${params.cursoId}</h2>
		<h2>Cuatrimestre Id: ${params.cuatrimestreId}</h2>

		<div id="list-noticiaCurso" class="content scaffold-list" role="main">
			<h1><g:message code="Noticias del curso ${com.fiuba.Curso.get(params.cursoId)}" /></h1>
			<h1><g:message code="Cuatrimestre ${com.fiuba.Cuatrimestre.get(params.cuatrimestreId)}" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>

						
						<g:sortableColumn property="titulo" title="${message(code: 'noticiaCurso.titulo.label', default: 'Titulo')}" />
						<g:sortableColumn property="fecha" title="${message(code: 'noticiaCurso.fecha.label', default: 'Fecha')}" />
						<g:sortableColumn property="hora" title="${message(code: 'noticiaCurso.hora.label', default: 'Hora')}" />
						<th><g:message code="noticiaCurso.mediador.label" default="Mediador" /></th>
					
					
						<td> Detalle </td>	
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${noticiaCursoInstanceList}" status="i" var="noticiaCursoInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td>${fieldValue(bean: noticiaCursoInstance, field: "titulo")}</td>
						<td>${fieldValue(bean: noticiaCursoInstance, field: "fecha")}</td>
						<td>${fieldValue(bean: noticiaCursoInstance, field: "hora")}</td>
						<td>${fieldValue(bean: noticiaCursoInstance, field: "mediador")}</td>
						
					
						<td><g:link action="show" id="${noticiaCursoInstance.id}" 
							params="['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId]">
							Ver detalle</g:link></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${noticiaCursoInstanceCount ?: 0}" params="['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId]"/>
			</div>
		</div>
	</body>
</html>
