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
				<li><g:link class="list" action="mediador" controller="curso" params="['cursoId': cursoId]">
					<g:message code="Pagina principal de mediador"/></g:link></li>
				<li><g:link class="list" action="index" controller="aprendiz" params="['cursoId': cursoId]">
					<g:message code="Administrar aprendices" /></g:link></li>
				<li><g:link class="list" action="index" controller="tema" params="['cursoId': cursoId]">
					<g:message code="Administrar temas del curso" /></g:link></li>
				<li><g:link class="list" action="index" controller="noticiaCurso" params="['cursoId': cursoId]">
					<g:message code="Administrar cartelera del curso" /></g:link></li>
				<li><g:link class="list" action="general" controller="foroCurso" params="['cursoId': cursoId]">
					<g:message code="Administrar foros generales de cursos" /></g:link></li>
				<li><g:link class="list" action="estadisticas" controller="aprendiz" params="['cursoId': cursoId]">
					<g:message code="Estadisticas de aprendices" /></g:link></li>
			</ol>
		</div>
		<div>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
		</div>
	</body>
</html>
