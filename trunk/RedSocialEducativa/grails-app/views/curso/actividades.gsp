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
				<g:if test="${aprendiz}">
					<li><g:link class="list" controller="curso" action="aprendiz" params="['cursoId': cursoId]">
						<g:message code="Menu aprendiz del curso ${com.fiuba.Curso.get(cursoId)}" args="[entityName]" /></g:link></li>
				</g:if>
				<g:else>
					<li><g:link class="list" controller="curso" action="mediador" params="['cursoId': cursoId]">
						<g:message code="Menu mediador del curso ${com.fiuba.Curso.get(cursoId)}" args="[entityName]" /></g:link></li>
				</g:else>				
			</ul>
		</div>		
		<div>
			<h1><g:message code="Material del curso: ${com.fiuba.Curso.get(cursoId)}" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<div>
				<ol>
				<g:each in="${actividades}">
					<span>
						<li>${it.titulo}</li>
					</span>
					<span>
						<g:link controller="actividad" action="general" id="${it.id}" params="['cursoId': cursoId]">
							<g:message code="Acceder a la actividad"/></g:link>		
					</span>		
				</g:each>
				</ol>
			</div>
			<div class="pagination">
				<g:paginate total="${actividadesCant ?: 0}" params="['cursoId': cursoId]"/>
			</div>
		</div>
	</body>
</html>