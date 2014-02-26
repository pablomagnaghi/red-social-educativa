<%@ page import="com.fiuba.Red" %>
<!doctype html>
<html>
    <head>
        <meta name="layout" content="main">
        <g:set var="entityName" value="Red Social Login" />
        <title><g:message code="Red Social Login" args="[entityName]" /></title>
    </head>
    <body>
		<div>
			<br>
			<h2>Sector destinado a visualizar informacion y material de los cursos (foros, temas y material general)</h2>
			<br>
			<h3>Los cursos de la red Social son estos</h3>
			<br>
				<g:each in="${cursos}" var="cursoInstance">
					<p><g:link action="revisarRolEnCurso" params="['cursoId': cursoInstance.id]">
							${com.fiuba.Materia.get(cursoInstance.materia.id)}-${cursoInstance}</g:link><p>	
					<br>
				</g:each>
			<div class="pagination">
				<g:paginate total="${cursoCant ?: 0}" />
			</div>
		</div>
		<div>
			<p>Noticia red; "${com.fiuba.NoticiaRed.first()}"</p>
			<p>Noticia curso; "${com.fiuba.NoticiaCurso.first()}"</p>
			<p>Material curso; "${com.fiuba.MaterialCurso.first()}"</p>
			<p>Material tema; "${com.fiuba.MaterialTema.first()}"</p>
			<p>Foro general; "${com.fiuba.ForoGeneral.first()}"</p>
			<p>Foro curso; "${com.fiuba.ForoCurso.first()}"</p>
			<p>Foro tema; "${com.fiuba.ForoTema.first()}"</p>
			<p>Grupo actividad: "${com.fiuba.GrupoActividad.first().aprendices}"</p>
			<p>Dia: ${com.fiuba.Utilidades.DIA}</p>
			<p>Mes: ${com.fiuba.Utilidades.MES}</p>
			<p>Anio: ${com.fiuba.Utilidades.ANIO}</p>
			<p>Fecha: ${com.fiuba.Utilidades.FECHA}</p>
			<p>Fecha proxima semana: ${com.fiuba.Utilidades.FECHA_PROXIMA_SEMANA}</p>
			<p>FORMATO FECHA: ${(new Date()).format(com.fiuba.Utilidades.FORMATO_FECHA)}</p>
			<p>Fecha PCUAT: ${com.fiuba.Utilidades.FECHA_PRIMER_CUATRIMESTRE}</p>
			<p>Fecha SCUAT: ${com.fiuba.Utilidades.FECHA_SEGUNDO_CUATRIMESTRE}</p>
			
			
		</div>
	</body>
</html>
