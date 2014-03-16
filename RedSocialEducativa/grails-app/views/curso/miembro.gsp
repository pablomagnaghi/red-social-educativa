<%@ page import="com.cursado.Curso" %>
<%@ page import="com.fiuba.UsuarioService" %>
<%@ page import="com.fiuba.MediadorService" %>
<%@ page import="com.fiuba.AprendizService" %>
<%
	def usuarioService = grailsApplication.classLoader.loadClass('com.fiuba.UsuarioService').newInstance()
	def mediadorService = grailsApplication.classLoader.loadClass('com.fiuba.MediadorService').newInstance()
	def aprendizService = grailsApplication.classLoader.loadClass('com.fiuba.AprendizService').newInstance()
%>

<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="red">
        <g:set var="entityName" value="Red Social Login" />
        <title><g:message code="Red Social Login" args="[entityName]" /></title>
    </head>
    <body>
    	<!-- Para el header y el panel lateral -->
    	<g:set var="varUsuarioService" bean="usuarioService"/>
    	<g:set var="varMediadorService" bean="mediadorService"/>
    	<g:set var="varAprendizService" bean="aprendizService"/>
    	<g:set var="usuario" value="${varUsuarioService.usuarioActual()}"/>
    	<g:set var="cursosMediador" value="${varMediadorService.obtenerCursos(usuario)}"/>
    	<g:set var="cursosAprendiz" value="${varAprendizService.obtenerCursos(usuario)}"/>
    	<div class="container-fluid-full">
			<div class="row-fluid">
	            <g:render template="/templateRed/panel" />
	            <!-- start: Content -->
	            <!-- PANEL CENTRAL -->
	            <div id="content" class="span10">
	            	<g:if test="${flash.message}">
						<div class="message" role="status">${flash.message}</div>
					</g:if>
					<div class="row-fluid">
						<div class="box span12">
							<div class="box-header">
								<h2><i class="icon-font"></i><span class="break"></span>
									Bienvenido miembro ${usuario} al curso ${com.cursado.Curso.get(params.cursoId)} de 
									la asignatura ${com.cursado.Curso.get(params.cursoId).asignatura}</h2>
								<div class="box-icon">
									<a href="#" class="btn-minimize"><i class="icon-chevron-up"></i></a>
								</div>
							</div>
							<div class="box-content">
								<div class="page-header">
									<g:render template="tituloGeneral"></g:render>
										<h2>Curso id: ${params.cursoId}</h2>
										<h2>Dicta cuatrimestre: ${dictaCuatrimestre}</h2>
										<h2>cuat id: ${cuatrimestre?.id}</h2>
										<h2>Noticia curso: ${noticiasCurso}</h2>
										<h2>"PARTICIPA: ${aprendiz?.participa}"</h2>	
										<h2>"APRENDIZ CURSNADO": ${aprendiz?.cursando}</h2>
										<h2>"CURSANDO: ${cursando}"</h2>
								</div>         
								<div class="row-fluid">            	 
									<div class="span12">
										<div>
											<g:link controller="materialCurso" action="index" params="['cursoId': params.cursoId]">
												<g:message code="Material del curso" /></g:link>
										</div>
										<div>
											<g:link controller="tema" action="index" params="['cursoId': params.cursoId]">
												<g:message code="Temas del curso" /></g:link>
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
									</div>
								</div>
							</div>
						</div><!--/span-->
					</div>	
					<div class="row-fluid">
					</div>	
				</div>	
			</div><!--/fluid-row-->	
		</div>
		<!-- end: Content -->
				
        <!--CLAVE ESTE DIV, SI SE SACA, NO APARECE NADA -->
        <div class="clearfix"></div>					
	</body>
</html>
