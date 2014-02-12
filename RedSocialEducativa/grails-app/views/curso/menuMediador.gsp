<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		</head>
	<body>
		<div>
			<h3>Menu de tareas para el mediador del curso "${com.fiuba.Curso.get(cursoId)}"</h3>
			<br>
			
			<h4>Cuatrimestre actual: ${com.fiuba.Cuatrimestre.get(cuatrimestreId)}</h4>
			<br>
			
			<ol>
				<li><g:link class="list" action="principal" controller="red">
					<g:message code="Pagina principal"/></g:link></li>
				<br>
				
				<li><g:link class="list" action="mediador" controller="curso" params="['cursoId': cursoId]">
					<g:message code="Pagina principal de mediador"/></g:link></li>
				<br>
		
				<g:if test="${cuatrimestreId}">
				
					<li><g:link class="list" action="index" controller="aprendiz" 
						params="['cursoId': cursoId, 'cuatrimestreId': cuatrimestreId]">
						<g:message code="Administrar aprendices" /></g:link></li>
					<br>
					<li><g:link class="list" action="index" controller="noticiaCurso" 
						params="['cursoId': cursoId, 'cuatrimestreId': cuatrimestreId]">
						<g:message code="Administrar cartelera del curso" /></g:link></li>
					<br>
					<li><g:link class="list" action="general" controller="foroCurso" 
						params="['cursoId': cursoId, 'cuatrimestreId': cuatrimestreId]">
						<g:message code="Administrar foros generales de cursos" /></g:link></li>
					<br>
					<li><g:link class="list" action="estadisticas" controller="aprendiz" 
						params="['cursoId': cursoId, 'cuatrimestreId': cuatrimestreId]">
						<g:message code="Estadisticas de aprendices" /></g:link></li>
					<br>
					<li><g:link class="list" action="menuMediador" controller="grupoCurso" 
						params="['cursoId': cursoId, 'cuatrimestreId': cuatrimestreId]">
						<g:message code="Grupos del curso" /></g:link></li>	
					<br>
					<li><g:link class="list" action="index" controller="actividad" 
						params="['cursoId': cursoId, 'cuatrimestreId': cuatrimestreId]">
						<g:message code="Administrar actividades del curso" /></g:link></li>	

				</g:if>
				<g:else>
					<p>"TODAVIA NO HAY UN CUATRIMESTRE ACTUAL. Revisar Consolidar cuatrimestre"</p>
				</g:else>
				<br>
				<li><g:link class="list" action="index" controller="materialCurso" params="['cursoId': cursoId]">
					<g:message code="Administrar material del curso" /></g:link></li>
				<br>
				<li><g:link class="list" action="index" controller="tema" params="['cursoId': cursoId]">
					<g:message code="Administrar temas del curso" /></g:link></li>
				<br>
				<li><g:link class="list" action="index" controller="evaluacion" params="['cursoId': cursoId]">
					<g:message code="Administrar evaluaciones del curso" /></g:link></li>		
				<br>	
				<li><g:link class="list" action="index" controller="cuatrimestre" params="['cursoId': cursoId]">
					<g:message code="Consolidar cuatrimestre (IMPLEMENTAR)" /></g:link></li>	
				<br>	
					<li><g:link class="list" action="index" controller="cuatrimestre" params="['cursoId': cursoId]">
					<g:message code="Historial de cuatrimestres del curso" /></g:link></li>
				<br>		
				<p>AGREGAR CONSOLIDAR CUATRIMESTRE, ESTE METODO TIENE QUE LLAMAR A NEW CUATRIMESTRE Y ADEMAS CONSOLIDAR EL ANTERIOR</p>	
			</ol>
		</div>
		<div>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
		</div>
	</body>
</html>
