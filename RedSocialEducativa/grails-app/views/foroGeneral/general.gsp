<%@ page import="com.fiuba.ForoGeneral" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'foroGeneral.label', default: 'ForoGeneral')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-foroGeneral" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}">
					<g:message code="default.home.label"/></a></li>
				<li><g:link class="create" controller="publicacionGeneral" action="nueva">
					<g:message code="Nueva publicacion" /></g:link></li>
			</ul>
		</div>
		<div id="list-foroGeneral" class="content scaffold-list" role="main">
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
		</div>	
		<div>
			<h2>Foro general: ${com.fiuba.ForoGeneral.first()}</h2>
			<br>
			<table>
			<thead>
				<tr>
					<g:sortableColumn property="Tema" title="Tema" />
					<g:sortableColumn property="Autor" title="Autor (comenzado por)" />
					<g:sortableColumn property="Replicas" title="Replicas" />
					<g:sortableColumn property="Ultimo mensaje" title="Ultimo mensaje" />
				</tr>
			</thead>
			<tbody>
				<g:each in="${publicaciones}" >
					<tr>
						<td><g:link action="publicaciones" id="${it.id}">${it.titulo}</g:link></td>
						<td>${it.responsable}</td>
						<td>${it.respuestas?.size()}</td>
						<td>
							<g:if test="${it.respuestas}">
								<p>${it.respuestas.last().responsable}</p>
								<p>${it.respuestas.last().fecha}</p>
								<p>${it.respuestas.last().hora}</p>
							</g:if>
							<g:else>
								<p>${it.responsable}</p>
								<p>${it.fecha}</p>
								<p>${it.hora}</p>
							</g:else>
						</td>
					</tr>
				</g:each>
			</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${publicacionesCant ?: 0}" />
			</div>
		</div>
	</body>
</html>