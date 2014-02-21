<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		</head>
	<body>
		<div>
			<h3>Menu de tareas para el mediador del curso "${com.fiuba.Curso.get(params.cursoId)}"</h3>
			<br>
			<h3>Los cuatrimestre que tuvo el curso: ${cuatrimestres}</h3>
			<h4>Cuatrimestre actual: ${com.fiuba.Cuatrimestre.get(cuatrimestreId)}</h4>
			
			
			<h2>Curso id: ${params.cursoId}</h2>
			<h2>Dicta cuatrimestre: ${dictaCuatrimestre}</h2>
			<h2>cuat id: ${cuatrimestreId}</h2>
			<h2>Noticia curso: ${noticiasCurso}</h2>

		
			
			<br>
			
			<ol>
				<li><g:link class="list" action="principal" controller="red">
					<g:message code="Pagina principal"/></g:link></li>
				<br>
				
				<li><g:link class="list" action="mediador" controller="curso" params="['cursoId': params.cursoId]">
					<g:message code="Pagina principal de mediador"/></g:link></li>
				<br>
		
				<g:if test="${cuatrimestreId}">
				
					<li><g:link class="list" action="index" controller="aprendiz" 
						params="['cursoId': params.cursoId, 'cuatrimestreId': cuatrimestreId]">
						<g:message code="Aprendices" /></g:link></li>
					<br>
					<li><g:link class="list" action="index" controller="noticiaCurso" 
						params="['cursoId': params.cursoId, 'cuatrimestreId': cuatrimestreId]">
						<g:message code="Cartelera" /></g:link></li>
					<br>
					<li><g:link class="list" action="general" controller="foroCurso" 
						params="['cursoId': params.cursoId, 'cuatrimestreId': cuatrimestreId]">
						<g:message code="Foros general" /></g:link></li>
					<br>
					<li><g:link class="list" action="estadisticas" controller="aprendiz" 
						params="['cursoId': params.cursoId, 'cuatrimestreId': cuatrimestreId]">
						<g:message code="Estadisticas" /></g:link></li>
					<br>
					<li><g:link class="list" action="index" controller="actividad" 
						params="['cursoId': params.cursoId, 'cuatrimestreId': cuatrimestreId]">
						<g:message code="Actividades" /></g:link></li>	

				</g:if>
				<g:else>
					<p>"TODAVIA NO HAY UN CUATRIMESTRE ACTUAL. Revisar Consolidar cuatrimestre"</p>
				</g:else>
				<br>
				<li><g:link class="list" action="index" controller="materialCurso" params="['cursoId': params.cursoId]">
					<g:message code="Material" /></g:link></li>
				<br>
				<li><g:link class="list" action="index" controller="tema" params="['cursoId': params.cursoId]">
					<g:message code="Temas" /></g:link></li>
				<br>
				<li><g:link class="list" action="index" controller="evaluacion" params="['cursoId': params.cursoId]">
					<g:message code="Evaluaciones" /></g:link></li>		
				<br>	
				<li><g:link class="list" action="create" controller="cuatrimestre" params="['cursoId': params.cursoId]">
					<g:message code="Consolidar cuatrimestre ${cuatrimestres.first()}" /></g:link></li>	
				<br>	
					<li><g:link class="list" action="indexHistoriales" controller="cuatrimestre" params="['cursoId': params.cursoId]">
					<g:message code="Historial de cuatrimestres" /></g:link></li>
				<br>		
			</ol>
		</div>
		<div>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
		</div>
	</body>
</html>
