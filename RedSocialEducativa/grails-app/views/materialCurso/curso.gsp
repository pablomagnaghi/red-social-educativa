
<%@ page import="com.fiuba.MaterialCurso" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'materialCurso.label', default: 'MaterialCurso')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-materialCurso" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}">
					<g:message code="default.home.label"/></a></li>
				<p>VOLVER AL CURSO</p>
			</ul>
		</div>
		<h2>Curso: ${com.fiuba.Curso.get(params.cursoId)}</h2>
		<h2>Curso Id: ${params.cursoId}</h2>
		<div>
			<h1><g:message code="Material: ${material.titulo}" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
		</div>
		<div>
			<p>Autor: ${material.autor}</p>
			<p>Descripcion: ${material.descripcion}</p>
			<p>Categoria": ${material.categoria}</p>	
			<p>Responsable: ${material.responsable}</p>
			<p>Fecha"${material.fecha}</p>
			<p>Poner link al material(URL)</p>
		</div>
	</body>
</html>
