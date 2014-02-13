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
				<p>Bienvenido aprendiz: "${aprendiz}"</p>
			</h2> 
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
			<h2>"PARTICIPA: ${aprendiz.participa}"</h2>	
			<h2>"CURSANDO: ${cursando}"</h2>
			<g:if test="${aprendiz.participa}">
				<g:if test="${cursando}">	
					<div>
						<g:link action="actividades" params="['cursoId': cursoId, 'cuatrimestreId': cuatrimestreId]">
						<g:message code="Actividades del cuatrimestre"/></g:link>
					</div>	
					<div>
						<g:link action="evaluaciones" params="['cursoId': cursoId, 'cuatrimestreId': cuatrimestreId]">
						<g:message code="Evaluaciones del cuatrimestre"/></g:link>
					</div>
					<div>
						<g:link action="mostrar" controller="evaluacion" params="['cursoId': cursoId, 'cursoId': cursoId]">
						<g:message code="Mis evaluaciones en el curso"/></g:link>
					</div>
					<div>
						<li><g:link class="list" action="menuAprendiz" controller="grupoCurso" params="['cursoId': cursoId, 'cuatrimestreId': cuatrimestreId]">
							<g:message code="Los grupos del cuatrimestre"/></g:link></li>
						<g:if test="${aprendiz?.grupo}">
							<li><g:link class="list" action="muestraAprendiz" controller="grupoCurso" id="${aprendiz?.grupo.id}" 
								params="['cursoId': cursoId, 'cuatrimestreId': cuatrimestreId]">
								<g:message code="Mi grupo"/></g:link></li>
						</g:if>	
					</div>
		    	</g:if>
		    	<g:else>
		    		<p>Usted curso la materia durante el cuatrimestre: ${aprendiz.cuatrimestre.anio} - ${aprendiz.cuatrimestre.numero}</p>
		    	</g:else>
		    </g:if>		
	    	<g:else>
			    <p>Su solicitud de particion en el curso ya ha sido recibida.</p>
			</g:else>    
		</div>
	</body>
</html>