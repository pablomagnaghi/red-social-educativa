<%@ page import="com.fiuba.GrupoActividad" %>
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
				<li><g:link class="create" controller="curso" action="menuMediador" params="['cursoId': cursoId]">
					<g:message code="Tareas mediador" args="[entityName]" /></g:link></li>		
				<li><g:link class="create" action="menuCambios" params="['cursoId': cursoId, 'cuatrimestreId': params.cuatrimestreId, 'actividadId': actividadId]">
					<g:message code="Cambiar aprendiz de grupo" args="[entityName]" /></g:link></li>		
			</ul>
		</div>
		<div id="list-aprendiz" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<g:sortableColumn property="usuario" title="${message(code: 'Aprendiz')}" 
							params="['cursoId': cursoId, 'cuatrimestreId': params.cuatrimestreId, 'actividadId': actividadId]" />
					
						<g:sortableColumn property="grupo" title="${message(code: 'Grupo')}"  
							params="['cursoId': cursoId, 'cuatrimestreId': params.cuatrimestreId, 'actividadId': actividadId]"/>
						
						<td>Detalle</td>	
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${aprendices}">
					<tr>
						<td>${it.aprendiz} (agregar link a datos del aprendiz)</td>	
					
						<td>${it.grupo}</td>			
						
						<td><g:link action="muestraMediador" id="${it.grupo.id}"
							params="['cursoId': cursoId, 'cuatrimestreId': params.cuatrimestreId, 'actividadId': actividadId]">Ver detalle grupo ${it.grupo.id}</g:link></td>	
				
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${aprendicesCant ?: 0}" params="['cursoId': cursoId, 'cuatrimestreId': params.cuatrimestreId, 'actividadId': actividadId]" />
			</div>
		</div>
	</body>
</html>