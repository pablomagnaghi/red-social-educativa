<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		</head>
	<body>
		<div>
			<h2>
				<p>"${com.fiuba.Curso.get(cursoId)}"</p>
				<br>
				<p>Bienvenido mediador "${mediador}"</p>
			</h2> 
				<br>
				<span class = "menuButton">
					<g:link action="menuMediador" controller="curso" id="${cursoId}">
					<g:message code="Tareas administrativas de mediador" /></g:link>
				</span>
		</div>
		<div>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
		</div>
		<fieldset class="form">
			<g:render template="noticiasCurso"/>
		</fieldset>
	</body>
</html>