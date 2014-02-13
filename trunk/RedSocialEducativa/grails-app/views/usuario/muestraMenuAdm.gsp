<%@ page import="com.fiuba.Usuario" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'usuario.label', default: 'Usuario')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-usuario" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}">
					<g:message code="default.home.label"/></a></li>
				<li><g:link class="list" controller="administrador" action="general">
					<g:message code="Tareas administrativas"/></g:link></li>
				<li><g:link class="list" controller="curso" action="index">
					<g:message code="Administrar cursos"/></g:link></li>
				<li><g:link class="list" controller="noticiaRed" action="index">
					<g:message code="Cartelera general"/></g:link></li>
				<li><g:link class="list" controller="mediador" action="index">
					<g:message code="Administrar mediadores"/></g:link></li>
			</ul>
		</div>
		<fieldset class="form">
			<g:render template="datos"/>
		</fieldset>
	</body>
</html>