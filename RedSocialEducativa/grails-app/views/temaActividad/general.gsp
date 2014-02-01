<%@ page import="com.fiuba.TemaActividad" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'contenido.label', default: 'Contenido')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-foroCurso" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}">
					<g:message code="default.home.label"/></a></li>
				<li><g:link class="create" controller="actividad" action="general" id="${actividadId}" params="['cursoId': cursoId]">
					<g:message code="Actividad ${actividad}" /></g:link></li>	
			</ul>
		</div>
		<div>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
		</div>	
		<div>
			<p>Tema de la actividad ${actividad} del Curso: ${com.fiuba.Curso.get(cursoId)}</p>
			<br>
				<ol>
					<g:each in="${temas}">
						<div><h1><g:message code="Tema: ${it.tema.titulo}" args="[entityName]" /></h1></div>
						<div>
							<p>Poner link al tema(URL)</p>
						</div>
					</g:each>
				</ol>	
			<div class="pagination">
				<g:paginate total="${temasCant ?: 0}" params="['cursoId': cursoId, 'actividadId': actividadId]"/>
			</div>
		</div>	
	</body>
</html>