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
				<li><g:link class="list" action="mediador" controller="curso" params="['cursoId': params.cursoId]">
					<g:message code="Pagina principal de mediador"/></g:link></li>
				<li><g:link class="create" action="menuMediador" controller="curso" params="['cursoId': params.cursoId]">
					<g:message code="Tareas mediador" /></g:link></li>
			</ul>
		</div>
		<h4>PARAMS: ${params}</h4>
		<div id="list-aprendiz" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
						<g:sortableColumn property="usuario" title="${message(code: 'aprendiz.usuario.label', default: 'Usuario')}" />
						<g:sortableColumn property="msjEnviados" title="${message(code: 'aprendiz.msjEnviados.label', default: 'Msj Enviados')}" />
						<g:sortableColumn property="msjLeidos" title="${message(code: 'aprendiz.msjLeidos.label', default: 'Msj Leidos')}" />	
						<g:sortableColumn property="pubForos" title="${message(code: 'aprendiz.pubForos.label', default: 'Pub Foros')}" />
						<g:sortableColumn property="descMaterial" title="${message(code: 'aprendiz.descMaterial.label', default: 'Desc Material')}" />
						<g:sortableColumn property="ultVisita" title="${message(code: 'aprendiz.ultVisita.label', default: 'Ult Visita')}" />
					</tr>
				</thead>
				<tbody>
				<g:each in="${aprendizInstanceList}" status="i" var="aprendizInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td>${fieldValue(bean: aprendizInstance, field: "usuario")}</td>			
						<td>${fieldValue(bean: aprendizInstance, field: "msjEnviados")}</td>
						<td>${fieldValue(bean: aprendizInstance, field: "msjLeidos")}</td>		
						<td>${fieldValue(bean: aprendizInstance, field: "pubForos")}</td>	
						<td>${fieldValue(bean: aprendizInstance, field: "descMaterial")}</td>
						<td>${fieldValue(bean: aprendizInstance, field: "ultVisita")}</td>	
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${aprendizInstanceCount ?: 0}" params="['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId]" />
			</div>
		</div>
	</body>
</html>