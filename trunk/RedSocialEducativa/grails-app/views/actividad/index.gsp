
<%@ page import="com.fiuba.Actividad" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'actividad.label', default: 'Actividad')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-actividad" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}">
					<g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="menuMediador" controller="curso" params="['cursoId': params.cursoId]">
					<g:message code="Tareas mediador" /></g:link></li>
				<li><g:link class="create" action="create" params="['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId]">
					<g:message code="default.new.label" args="[entityName]" /></g:link></li>	
			</ul>
		</div>
		<h3>curso: ${params.cursoId}</h3>
		<h3>cuatri: ${params.cuatrimestreId}</h3>
		<h3>act: ${params.actividadId}</h3>
		
		<div id="list-actividad" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<th><g:message code="actividad.curso.label" default="Titulo" /></th>
					
						<th><g:message code="actividad.categoria.label" default="Categoria" /></th>
					
						<td> Detalle </td>	
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${actividadInstanceList}" status="i" var="actividadInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td>${fieldValue(bean: actividadInstance, field: "titulo")}</td>
					
						<td>${fieldValue(bean: actividadInstance, field: "categoria")}</td>
						
						<td><g:link action="show" id="${actividadInstance.id}" 
							params="['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId]">Ver detalle</g:link></td>
						
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${actividadInstanceCount ?: 0}" params="['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId]" />
			</div>
		</div>
	</body>
</html>
