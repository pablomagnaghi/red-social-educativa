
<%@ page import="com.fiuba.PublicacionGeneral" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'publicacionGeneral.label', default: 'PublicacionGeneral')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-publicacionGeneral" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-publicacionGeneral" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<th><g:message code="publicacionGeneral.publicacionPadre.label" default="Publicacion Padre" /></th>
					
						<g:sortableColumn property="contenido" title="${message(code: 'publicacionGeneral.contenido.label', default: 'Contenido')}" />
					
						<g:sortableColumn property="fecha" title="${message(code: 'publicacionGeneral.fecha.label', default: 'Fecha')}" />
					
						<th><g:message code="publicacionGeneral.foro.label" default="Foro" /></th>
					
						<g:sortableColumn property="hora" title="${message(code: 'publicacionGeneral.hora.label', default: 'Hora')}" />
					
						<g:sortableColumn property="responsable" title="${message(code: 'publicacionGeneral.responsable.label', default: 'Responsable')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${publicacionGeneralInstanceList}" status="i" var="publicacionGeneralInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${publicacionGeneralInstance.id}">${fieldValue(bean: publicacionGeneralInstance, field: "publicacionPadre")}</g:link></td>
					
						<td>${fieldValue(bean: publicacionGeneralInstance, field: "contenido")}</td>
					
						<td>${fieldValue(bean: publicacionGeneralInstance, field: "fecha")}</td>
					
						<td>${fieldValue(bean: publicacionGeneralInstance, field: "foro")}</td>
					
						<td>${fieldValue(bean: publicacionGeneralInstance, field: "hora")}</td>
					
						<td>${fieldValue(bean: publicacionGeneralInstance, field: "responsable")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${publicacionGeneralInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
