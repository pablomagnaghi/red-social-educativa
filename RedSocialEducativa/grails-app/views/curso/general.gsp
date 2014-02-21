<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		</head>
	<body>
		<div>
			<h2>"Bienvenido al curso ${com.fiuba.Curso.get(params.cursoId).nroRelativo} de la materia ${com.fiuba.Curso.get(params.cursoId).materia}"</h2>
			<br>
		</div>
		<h2>Curso id: ${params.cursoId}</h2>
		<h2>Dicta cuatrimestre: ${dictaCuatrimestre}</h2>
		<h2>cuat id: ${cuatrimestreId}</h2>
		<h2>Noticia curso: ${noticiasCurso}</h2>
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

