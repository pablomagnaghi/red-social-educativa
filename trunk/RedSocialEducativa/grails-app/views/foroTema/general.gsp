<%@ page import="com.fiuba.ForoTema" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'foroTema.label', default: 'ForoTema')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-foroCurso" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}">
					<g:message code="default.home.label"/></a></li>
				<li><g:link class="create" controller="tema" action="curso" id="${params.temaId}" params="['cursoId': params.cursoId]">
					<g:message code="Tema ${tema}" /></g:link></li>	
				<li><g:link class="create" controller="publicacionTema" action="nueva" params="['cursoId': params.cursoId, 'temaId': params.temaId]">
					<g:message code="Nueva publicacion" /></g:link></li>
			</ul>
		</div>
		<div id="list-foroCurso" class="content scaffold-list" role="main">
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
		</div>	
		<div>
			<h2>Bienvenido al Foro: ${foro}</h2>
			<p>Foro del tema ${tema} del Curso: ${com.fiuba.Curso.get(params.cursoId)}</p>
			<br>
			<table>
			<thead>
				<tr>
					<g:sortableColumn property="Tema" title="Tema" />
					<g:sortableColumn property="Autor" title="Autor" />
					<g:sortableColumn property="Replicas" title="Replicas" />
					<g:sortableColumn property="Ultimo mensaje" title="Ultimo mensaje" />
				</tr>
			</thead>
			<tbody>
				<g:each in="${publicaciones}" >
					<tr>
						<td><g:link action="publicaciones" id="${it.id}" 
							params="['cursoId': params.cursoId, 'temaId': params.temaId]">${it.titulo}</g:link></td>
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
				<g:paginate total="${publicacionesCant ?: 0}" params="['cursoId': params.cursoId, 'temaId': params.temaId]"/>
			</div>
		</div>
	</body>
</html>