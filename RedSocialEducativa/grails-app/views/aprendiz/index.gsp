
<%@ page import="com.fiuba.Aprendiz" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'aprendiz.label', default: 'Aprendiz')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-aprendiz" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}">
					<g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="menuMediador" controller="curso" params="['cursoId': cursoId]">
					<g:message code="Tareas mediador" /></g:link></li>
				<li><g:link class="create" action="create" params="['cursoId': cursoId, 'cuatrimestreId': cuatrimestreId]">
					<g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		
		<h2>Params: ${params}</h2>
		<h2>Curso: ${com.fiuba.Curso.get(cursoId)}</h2>
		<h2>Curso Id: ${cursoId}</h2>
		<h2>Cuatrimestre Id: ${cuatrimestreId}</h2>
		
		<div id="list-aprendiz" class="content scaffold-list" role="main">
			<h1><g:message code="Aprendices del curso ${com.fiuba.Curso.get(cursoId)}" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="usuario" title="${message(code: 'aprendiz.usuario.label', default: 'Usuario')}" />
										
						<td>Datos del aprendiz</td>
						
						<td>Estado</td>
						
					</tr>
				</thead>
				<tbody>
					<g:each in="${aprendizInstanceList}" status="i" var="aprendizInstance">
						<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
						
							<td>${fieldValue(bean: aprendizInstance, field: "usuario")}</td>					
												
							<td><g:link controller="usuario" action="show" id="${aprendizInstance.usuario.id}" 
								params="['cursoId': cursoId, 'cuatrimestreId': cuatrimestreId]">
								<g:message code="Ver datos" /></g:link></td>
								
							<g:if test="${!aprendizInstance.participa}">
								<td>Esperando aceptacion (<g:link action="activarAprendiz" controller="mediador" id="${aprendizInstance.id}" 
									params="['cursoId': cursoId, 'cuatrimestreId': cuatrimestreId]"><g:message code="Aceptar aprendiz" /></g:link>)
								</td>
							</g:if>		
							<g:else>
								<td>Aprendiz activo (<g:link class="delete" action="delete" id="${aprendizInstance.id}" 
									params="['cursoId': cursoId, 'cuatrimestreId': cuatrimestreId]"
									value="${message(code: 'default.button.delete.label', default: 'Delete')}" 
									onclick="return confirm('${message(code: 'default.button.delete.confirm.message', 
									default: 'Are you sure?')}');">
									<g:message code="Eliminar aprendiz" /></g:link>)
								</td>
							</g:else>
						</tr>
					</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${aprendizInstanceCount ?: 0}" params="['cursoId': cursoId, 'cuatrimestreId': cuatrimestreId]"/>
			</div>
		</div>
	</body>
</html>
