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
		<h2>Curso id: ${params.cursoId}</h2>
		<h2>Dicta cuatrimestre: ${dictaCuatrimestre}</h2>
		<h2>cuat id: ${cuatrimestre?.id}</h2>
		<h2>Noticia curso: ${noticiasCurso}</h2>
		<h2>"PARTICIPA: ${aprendiz.participa}"</h2>	
		<h2>"APRENDIZ CURSNADO": ${aprendiz.cursando}</h2>
		<h2>"CURSANDO: ${cursando}"</h2>


		<div>
			<g:if test="${noticiasCurso}">
				<h2>Cartelera general</h2>
				<br>
				<table>
					<thead>
						<tr>
							<td>Autor</td>
							<td>Noticia</td>
						</tr>
					</thead>
					<tbody>
						<g:each in="${noticiasCurso}">
							<g:if test="${it.visibilidad}">
								<tr>
									<td>
										<p>Mediador: ${it.mediador}</p>
										<p>Publicado: ${it.fecha}-${it.hora}<p>
									</td>
									<td>${it.texto}</td>
								</tr>
							</g:if>
						</g:each>
					</tbody>
					</table>
				<h5>Agregar PAGINACION</h5>
			</g:if>
			<g:if test="${aprendiz.participa}">
												<g:if test="${cursando}">	
					<div>
						<g:link action="actividades" params="['cursoId': params.cursoId, 'cuatrimestreId': cuatrimestre?.id]">
						<g:message code="Actividades del cuatrimestre" /></g:link>
					</div>	
					<div>
						<g:link action="evaluaciones" params="['cursoId': params.cursoId, 'cuatrimestreId': cuatrimestre?.id]">
						<g:message code="Evaluaciones del cuatrimestre" /></g:link>
					</div>
					<div>
						<g:link action="evaluacionesAprendiz" controller="evaluacion" 
							params="['cursoId': params.cursoId, 'cuatrimestreId': cuatrimestre?.id]">
						<g:message code="Mis evaluaciones en el curso" /></g:link>
					</div>
					<div>
						<li><g:link class="list" action="menuAprendiz" controller="grupoCurso" 
							params="['cursoId': params.cursoId, 'cuatrimestreId': cuatrimestre?.id]">
							<g:message code="Los grupos del cuatrimestre" /></g:link></li>
						<g:if test="${aprendiz?.grupo}">
							<li><g:link class="list" action="muestraAprendiz" controller="grupoCurso" id="${aprendiz?.grupo.id}"
									params="['cursoId': params.cursoId, 'cuatrimestreId': cuatrimestre?.id]">
								<g:message code="Mi grupo" /></g:link></li>
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