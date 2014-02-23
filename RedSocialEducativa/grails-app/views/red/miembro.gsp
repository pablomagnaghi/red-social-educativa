<%@ page import="com.fiuba.Red" %>
<!doctype html>
<html>
    <head>
        <meta name="layout" content="main">
        <g:set var="entityName" value="Red Social Login" />
        <title><g:message code="Red Social Login" args="[entityName]" /></title>
    </head>
    <body>
	    <div id="create-endUser" class="content scaffold-create" role="main">
	    	<ul class="menuBar">
				<li id="tituloRed"><g:message code="Red Social Educativa FIUBA" /></li>
				<li><msg:showNotifications cantMensajes="${cantMensajes}"/></li>
				<g:link controller="mensajeria" action="index">Mensajeria (hace click aca, el cartel de mensajito falla con la seguridad)</g:link>
			</ul>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<sec:ifLoggedIn>
				Bienvenido <sec:username/> (<g:link controller='logout'>Salir</g:link>)
			</sec:ifLoggedIn>
		</div>
		<div>
			<span>
				<g:if test="${cursosMediador}">
					<br><h2>Mis cursos como mediador</h2><br> 
					<ol>
						<g:each in="${cursosMediador}" var="cursoMediador">
							<li>
								<span class = "menuButton">
									<g:link action="mediador" controller="curso" params="['cursoId': cursoMediador.id]">
									<g:message code="${com.fiuba.Materia.get(cursoMediador.materia.id)}-${cursoMediador}" /></g:link>
								</span>
								<br>
							</li>
						</g:each>
					</ol>
				</g:if>
			</span>
			<span>
				<g:if test="${cursosAprendiz}">
					<br><h2>Mis cursos como aprendiz</h2><br> 
					<ol>
						<g:each in="${cursosAprendiz}" var="cursoAprendiz">
							<li>
								<span class = "menuButton">
									<g:link action="aprendiz" controller="curso" params="['cursoId': cursoAprendiz.id]">
									<g:message code="${com.fiuba.Materia.get(cursoAprendiz.materia.id)}-${cursoAprendiz}" /></g:link>
								</span>
								<br>
							</li>
						</g:each>
					</ol>
				</g:if>
			</span>
		</div>
		<br>
		<div>
			<hr>
	       	<h2>Cartelera general</h2>
	       	<g:if test="${noticiasRed}">
				<table>
				<thead>
					<tr>
						<td>Autor</td>
						<td>Noticia</td>				
					</tr>
				</thead>
				<tbody>	
					<g:each in="${noticiasRed}">	
						<g:if test="${it.visibilidad}">
							<tr>
								<td>
									<p>Administrador: ${it.administrador}</p>
									<p>Publicado: ${it.fecha} - ${it.hora} <p>
								</td>
								<td>${it.texto}</td>
							</tr>
						</g:if>
					</g:each>
				</tbody>
				</table>
			</g:if>
		<h5>Agregar PAGINACION</h5>
		</div>	
		<div>
			<hr>
				<br><h2><g:link controller="foroGeneral" action="general">Foro general</g:link></h2><br>
			<hr>
		</div>
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
