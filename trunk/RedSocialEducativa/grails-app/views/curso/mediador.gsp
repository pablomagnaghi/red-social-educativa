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
				<p>Bienvenido mediador "${mediador.usuario}"</p>
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
			<div>
				<g:link action="actividades" params="['cursoId': cursoId]">
					<g:message code="Actividades del curso"/></g:link>
			</div>
			<fieldset class="form">
				<g:render template="esquema"/>
			</fieldset>
		</div>
	</body>
</html>