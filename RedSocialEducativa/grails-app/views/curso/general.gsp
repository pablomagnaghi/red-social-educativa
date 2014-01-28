<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		</head>
	<body>
		<div>
			<h2>"Bienvenido al curso ${curso.nroRelativo} de la materia ${curso.materia}"</h2>
			<br>
		</div>
		<div>	
			<g:link class="list" action="principal" controller="red">
				<g:message code="Pagina Inicial" args="[entityName]" /></g:link>
		</div>
		<div>
			<fieldset class="form">
				<g:render template="esquema"/>
			</fieldset>
		</div>
		<div>
			<p>Para tener mayores accesos por favor, inicie sesion</p>	
		</div>
	</body>
</html>

