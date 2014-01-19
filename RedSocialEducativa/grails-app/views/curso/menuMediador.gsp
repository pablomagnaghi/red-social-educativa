<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		</head>
	<body>
		<div>
			<h3>Menu de tareas para el mediador del curso "${com.fiuba.Curso.get(cursoId)}"</h3>
			<br>
			<ol>
				<li><g:link class="list" action="principal" controller="red">
					<g:message code="Pagina principal"/></g:link></li>
				<li><g:link class="list" action="index" controller="aprendiz" id="${cursoId}">
					<g:message code="Administrar aprendices" /></g:link></li>
				<li><g:link class="list" action="index" controller="tema" id="${cursoId}">
					<g:message code="Administrar temas del curso" /></g:link></li>
				<li><g:link class="list" action="index" controller="noticiaCurso" id="${cursoId}">
					<g:message code="Administrar cartelera del curso" /></g:link></li>
			</ol>
		</div>
		<div>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
		</div>
	</body>
</html>
