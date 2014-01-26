<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		</head>
	<body>
		<div>
			<h4>"Bienvenido al curso ${curso.nroRelativo} de la materia ${curso.materia}"</h4>
		       	<g:link class="list" action="principal" controller="red">
		       	<g:message code="Pagina Inicial" args="[entityName]" /></g:link>
		</div>
		<div>
			<fieldset class="form">
				<g:render template="esquema"/>
			</fieldset>
		</div>
		<div>
			<br>
			<p>Para tener mayores accesos por favor, inicie sesion</p>	
			<br>
			<h2>Proximamente</h2>
			<br>
			<p>1 - Visualizar información y material de los cursos (foros, temas y material general)</p>
			<p>2 - Dejar comentario o mensaje para un curso (foro de curso) HECHO</p>
		</div>
	</body>
</html>

