
<%@ page import="com.fiuba.Tema" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'tema.label', default: 'Tema')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-tema" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}">
					<g:message code="default.home.label"/></a></li>
				<li><g:link action="general" controller="curso" params="['cursoId': cursoId]">
					<g:message code="Curso: ${com.fiuba.Curso.get(cursoId)}"/></g:link></li>
			</ul>
		</div>		
		<div>
			<h1><g:message code="Temas del curso: ${com.fiuba.Curso.get(cursoId)}" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
						<g:sortableColumn property="titulo" title="${message(code: 'tema.titulo.label', default: 'Titulo')}" />
					</tr>
				</thead>
				<tbody>
				<g:each in="${temasCurso}" status="i" var="temaInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">				
						<td>${fieldValue(bean: temaInstance, field: "titulo")}</td>
						<g:if test="${mediador || aprendiz}">						
							<td>
								<p>Link de acceso al foro, contenidos y material del tema</p>
								<p><g:link controller="tema" action="general" id="${temaInstance.id}" params="['cursoId': cursoId]">
									<g:message code="Informacion del tema"/></g:link>
							</td>				
						</g:if>
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${temasCursoCant ?: 0}" params="['cursoId': cursoId]"/>
			</div>
		</div>
	</body>
</html>