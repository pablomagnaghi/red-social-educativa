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
				<p>"Bienvenido aprendiz ${usuario} al curso ${com.fiuba.Curso.get(params.cursoId).nroRelativo} de la 
				materia ${com.fiuba.Curso.get(params.cursoId).materia}"</p>
			</h2> 
		</div>
		<div>
			<g:link class="list" action="principal" controller="red">
			<g:message code="Pagina Inicial" args="[entityName]" /></g:link>
		</div>
		<div>
			<fieldset class="form">
				<g:render template="general"/>
			</fieldset>   
		</div>
		
		<h2>Curso id: ${params.cursoId}</h2>
		<h2>Dicta cuatrimestre: ${dictaCuatrimestre}</h2>
		<h2>cuat id: ${cuatrimestre?.id}</h2>
		<h2>Noticia curso: ${noticiasCurso}</h2>
		<h2>"PARTICIPA: ${aprendiz.participa}"</h2>	
		<h2>"APRENDIZ CURSNADO": ${aprendiz.cursando}</h2>
		<h2>"CURSANDO: ${cursando}"</h2>


		<div>
			<fieldset class="form">
				<g:render template="noticias"/>
			</fieldset>   
			<g:if test="${aprendiz.cursando}">	
				<div>
					<g:link action="actividades" params="['cursoId': params.cursoId, 'cuatrimestreId': cuatrimestre?.id]">
					<g:message code="Actividades del cuatrimestre" /></g:link>
				</div>	
				<div>
					<g:link controller="foroCurso" action="general" params="['cursoId': params.cursoId, 'cuatrimestreId': cuatrimestre?.id]">
					<g:message code="Foro del curso"/></g:link>
				</div>

		    </g:if>
		    <g:else>
		    	<p>Usted curso la materia durante el cuatrimestre: ${aprendiz.cuatrimestre.anio} - ${aprendiz.cuatrimestre.numero}</p>
		    </g:else>	 
		    
		    <div>
				<g:link action="evaluaciones" params="['cursoId': params.cursoId]">
				<g:message code="Evaluaciones del curso" /></g:link>
			</div>
			<div>
				<g:link action="evaluacionesAprendiz" controller="evaluacion" params="['cursoId': params.cursoId]">
				<g:message code="Mis evaluaciones en el curso" /></g:link>
			</div>
		</div>
	</body>
</html>