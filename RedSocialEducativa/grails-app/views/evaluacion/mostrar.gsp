
<%@ page import="com.fiuba.Evaluacion" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'evaluacion.label', default: 'evaluacion')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-evaluacion" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}">
					<g:message code="default.home.label"/></a></li>
				<g:if test="${aprendizId}">
					<li><g:link class="list" controller="curso" action="aprendiz" params="['cursoId': cursoId]">
						<g:message code="Menu aprendiz  del curso ${com.fiuba.Curso.get(cursoId)}" args="[entityName]" /></g:link></li>
				</g:if>
				<g:else>
					<li><g:link class="list" controller="curso" action="mediador" params="['cursoId': cursoId]">
						<g:message code="Menu mediador  del curso ${com.fiuba.Curso.get(cursoId)}" args="[entityName]" /></g:link></li>
				</g:else>	
			</ul>
		</div>
		<div>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
		</div>
		<div>
			<g:if test="${evaluacionesAprendiz}">
				<h1><g:message code="Evaluaciones del aprendiz: ${evaluacionesAprendiz.first().aprendiz} en el curso
					${evaluacionesAprendiz.first().evaluacion.curso}" args="[entityName]" /></h1>
				<g:each in="${evaluacionesAprendiz}">
					<p>Evaluacion: ${it.evaluacion}</p>
					<p>Nota: AGREGAR NOTA</p>	
				</g:each>
			</g:if>
			<g:else>
				<p>Usted no posee evaluaciones en este curso</p>
			</g:else>
		</div>
	</body>
</html>
