<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		</head>
	<body>
		<div>
			<h2><p>"${com.fiuba.Curso.get(params.cursoId)}"</p>
				<br>
				<p>"Bienvenido miembro ${usuario} al curso ${com.fiuba.Curso.get(params.cursoId).nroRelativo} de la 
				materia ${com.fiuba.Curso.get(params.cursoId).materia}"</p>
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
		<g:if test="${cuatrimestre?.id}">
			<g:if test="${solicitoParticipacion}">
			<div>
				<p>Su solicitud de particion en el curso ya ha sido recibida.</p>			      
			</div>
			</g:if>
			<g:else>
				<g:link class="list" action="solicitarParticipacionEnElCurso" params="['cursoId': params.cursoId]">
				<g:message code="Solicitar partipacion en el curso" args="[entityName]" /></g:link>  
			</g:else>
		</g:if>	
		<div>
			<fieldset class="form">
				<g:render template="general"/>
			</fieldset>
		</div>
	</body>
</html>