
<%@ page import="com.fiuba.Calendario" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'calendario.label', default: 'Calendario')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-calendario" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
	
		<div class="nav" role="navigation">
			<ul>
				<li><g:link class="list" action="principal">
						<g:message code="Pagina inicial" args="[entityName]" />
					</g:link></li>
				<li><g:link class="create" action="menu" controller="administrador">
						<g:message code="Volver a tareas administrativas" args="[entityName]" />
					</g:link></li>
				<li><g:link class="create" action="create">
						<g:message code="default.new.label" args="[entityName]" />
					</g:link></li>
			</ul>
		</div>
		<div id="list-calendario" class="content scaffold-list" role="main">
		<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<g:sortableColumn property="anio" title="${message(code: 'calendario.anio.label', default: 'Anio')}" />
					
						<g:sortableColumn property="inicioPrimerCuatrimestre" title="${message(code: 'calendario.inicioPrimerCuatrimestre.label', default: 'Inicio Primer Cuatrimestre')}" />
					
						<g:sortableColumn property="inicioSegundoCuatrimestre" title="${message(code: 'calendario.inicioSegundoCuatrimestre.label', default: 'Inicio Segundo Cuatrimestre')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${calendarioInstanceList}" status="i" var="calendarioInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${calendarioInstance.id}">
							<g:formatNumber number="${calendarioInstance.anio}"/></g:link></td>				
					
						<td><g:formatNumber number="${calendarioInstance.inicioPrimerCuatrimestre}"/></td>
						
						<td><g:formatNumber number="${calendarioInstance.inicioSegundoCuatrimestre}"/></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${calendarioInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
