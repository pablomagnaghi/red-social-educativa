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
			<ol>
				<li><g:link class="list" action="principal" controller="red">
					<g:message code="Pagina principal"/></g:link></li>
				<li><g:link class="list" action="menuMediador" controller="curso" params="['cursoId': cursoId]">
					<g:message code="Tareas administrativas de mediador" /></g:link></li>
		
			</ol>
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