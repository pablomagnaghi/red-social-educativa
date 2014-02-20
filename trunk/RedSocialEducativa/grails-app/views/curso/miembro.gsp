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
				<p>Bienvenido miembro: "${miembro}"</p>
			</h2> 
		</div>
		<h2>Curso id: ${params.cursoId}</h2>
		<h2>Dicta cuatrimestre: ${dictaCuatrimestre}</h2>
		<h2>cuat id: ${cuatrimestre?.id}</h2>
		<h2>Noticia curso: ${noticiasCurso}</h2>
		<div>
		    <g:link class="list" action="principal" controller="red">
		       	<g:message code="Volver" args="[entityName]" /></g:link>
		</div>
		<div>
		    <g:link class="list" action="solicitarParticipacionEnElCurso" params="['cursoId': cursoId]">
				<g:message code="Solicitar partipacion en el curso" args="[entityName]" /></g:link>    
		</div>
		<div>
			<fieldset class="form">
				<g:render template="esquema"/>
			</fieldset>
		</div>
	</body>
</html>