
<%@ page import="com.fiuba.Cuatrimestre" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'cuatrimestre.label', default: 'Cuatrimestre')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-cuatrimestre" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-cuatrimestre" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<g:sortableColumn property="anio" title="${message(code: 'cuatrimestre.anio.label', default: 'Anio')}" />
					
						<th><g:message code="cuatrimestre.curso.label" default="Curso" /></th>
					
						<g:sortableColumn property="habGrupos" title="${message(code: 'cuatrimestre.habGrupos.label', default: 'Hab Grupos')}" />
					
						<g:sortableColumn property="nroUltGrupo" title="${message(code: 'cuatrimestre.nroUltGrupo.label', default: 'Nro Ult Grupo')}" />
					
						<g:sortableColumn property="numero" title="${message(code: 'cuatrimestre.numero.label', default: 'Numero')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${cuatrimestreInstanceList}" status="i" var="cuatrimestreInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${cuatrimestreInstance.id}">${fieldValue(bean: cuatrimestreInstance, field: "anio")}</g:link></td>
					
						<td>${fieldValue(bean: cuatrimestreInstance, field: "curso")}</td>
					
						<td><g:formatBoolean boolean="${cuatrimestreInstance.habGrupos}" /></td>
					
						<td>${fieldValue(bean: cuatrimestreInstance, field: "nroUltGrupo")}</td>
					
						<td>${fieldValue(bean: cuatrimestreInstance, field: "numero")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${cuatrimestreInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
