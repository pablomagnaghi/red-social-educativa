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
			<h1><g:message code="Tema ${com.fiuba.Tema.get(temaId)} del curso: ${com.fiuba.Curso.get(cursoId)}" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
		</div>	
		<div>
			<hr>
			<h2><g:link controller="foroTema" action="general" params="['cursoId': cursoId, 'temaId': temaId]">
				<g:message code="Foro del tema"/></g:link></h2>
		</div>
		<div>
			<hr>
			<h2><g:link controller="materialTema" action="general" params="['cursoId': cursoId, 'temaId': temaId]">
				<g:message code="Material del tema"/></g:link></h2>
		</div>
		<div>
			<hr>
			<h2><g:link controller="contenido" action="general" params="['cursoId': cursoId, 'temaId': temaId]">
				<g:message code="Contenidos del tema"/></g:link></h2>
		</div>
	</body>
</html>