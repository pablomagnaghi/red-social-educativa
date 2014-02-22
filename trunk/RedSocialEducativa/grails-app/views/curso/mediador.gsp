<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		</head>
	<body>
		<div>
			<h2>
				<p>"${com.fiuba.Curso.get(params.cursoId)}"</p>
				<br>
				<p>"Bienvenido mediador ${usuario} al curso ${com.fiuba.Curso.get(params.cursoId).nroRelativo} de la 
				materia ${com.fiuba.Curso.get(params.cursoId).materia}"</p>
			</h2> 
			<br>
			<ol>
				<li><g:link class="list" action="principal" controller="red">
					<g:message code="Pagina principal"/></g:link></li>
				<li><g:link class="list" action="menuMediador" controller="curso" params="['cursoId': params.cursoId]">
					<g:message code="Tareas administrativas de mediador" /></g:link></li>
		
			</ol>
		</div>
		<h2>Curso id: ${params.cursoId}</h2>
		<h2>Dicta cuatrimestre: ${dictaCuatrimestre}</h2>
		<h2>cuat id: ${cuatrimestre?.id}</h2>
		<h2>Noticia curso: ${noticiasCurso}</h2>
		<div>	
			<fieldset class="form">
				<g:render template="general"/>
			</fieldset>
			<fieldset class="form">
				<g:render template="noticias"/>
			</fieldset>   
		</div>
		
	</body>
</html>