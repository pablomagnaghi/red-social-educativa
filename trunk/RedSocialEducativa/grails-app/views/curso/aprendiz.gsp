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
			<g:if test="${aprendiz?.participa}">	
				<div>
					<g:link action="actividades" params="['cursoId': cursoId]">
					<g:message code="Actividades del curso"/></g:link>
				</div>	
				<div>
					<g:link action="evaluaciones" params="['cursoId': cursoId]">
					<g:message code="Evaluaciones del curso"/></g:link>
				</div>
				<div>
					<g:link action="mostrar" controller="evaluacion" params="['cursoId': cursoId]">
					<g:message code="Mis evaluaciones en el curso"/></g:link>
				</div>
				
				<li><g:link class="list" action="general" controller="grupoCurso" params="['cursoId': cursoId]">
					<g:message code="Los grupos del curso"/></g:link></li>
				<g:if test="${aprendiz?.grupo}">
					<li><g:link class="list" action="mostrar" controller="grupoCurso" id="${aprendiz?.grupo.id}" params="['cursoId': cursoId]">
						<g:message code="Mi grupo"/></g:link></li>
				</g:if>	
					
					
	    	</g:if>
	    	<g:else>
			    <p>Su solicitud de particion en el curso ya ha sido recibida.</p>
			</g:else>    
		</div>
	</body>
</html>