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
				<p>Bienvenido miembro: "${miembro}"</p>
			</h2> 
		</div>
		<div>
		    <g:link class="list" action="principal" controller="red">
		       	<g:message code="Volver" args="[entityName]" /></g:link>
		</div>
		<div>
		    <g:link class="list" action="solicitarParticipacionEnElCurso" params="['cursoId': cursoId]">
				<g:message code="Solicitar partipacion en el curso" args="[entityName]" /></g:link>    
		</div>
	</body>
</html>