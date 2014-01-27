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
			<div>
				<ol>
				<g:each in="${temasCurso}">
					<span>
						<li>${it.titulo}</li>
					</span>
					<span>
						<g:if test="${mediador || aprendiz}">	
							<g:link controller="tema" action="general" id="${it.id}" params="['cursoId': cursoId]">
									<g:message code="Acceder al tema"/></g:link>		
						</g:if>
					</span>	
				</g:each>
				</ol>
			</div>
			<div class="pagination">
				<g:paginate total="${temasCursoCant ?: 0}" params="['cursoId': cursoId]"/>
			</div>
		</div>
	</body>
</html>