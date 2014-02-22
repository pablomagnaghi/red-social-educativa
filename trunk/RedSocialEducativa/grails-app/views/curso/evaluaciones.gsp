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
				<li><g:link class="list" controller="curso" action="aprendiz" params="['cursoId': params.cursoId]">
					<g:message code="Menu aprendiz del curso ${com.fiuba.Curso.get(params.cursoId)}" args="[entityName]" /></g:link></li>			
			</ul>
		</div>		
		<h2>Curso id: ${params.cursoId}</h2>
		<div>
			<h1><g:message code="Evaluaciones del curso: ${com.fiuba.Curso.get(params.cursoId)}" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<div>
				<ol>
				<g:each in="${evaluaciones}">
					<span>
						<li>${it.fecha}-${it.horario}</li>
					</span>
					<span>
						<g:link controller="evaluacion" action="menuAprendiz" id="${it.id}" params="['cursoId': params.cursoId]">
							<g:message code="Acceder a la evaluacion"/></g:link>		
					</span>		
				</g:each>
				</ol>
			</div>
			<div class="pagination">
				<g:paginate total="${evaluacionesCant ?: 0}" params="['cursoId': params.cursoId]"/>
			</div>
		</div>
	</body>
</html>