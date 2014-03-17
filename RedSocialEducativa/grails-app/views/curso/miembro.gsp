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
					<div class="row-fluid">
						<div class="box span12">
							<div class="box-header">
								<h2><i class="icon-th-large"></i></i><span class="break"></span>
									Bienvenido miembro ${usuario} al curso ${com.cursado.Curso.get(params.cursoId)} de 
									la asignatura ${com.cursado.Curso.get(params.cursoId).asignatura}</h2>
								<div class="box-icon">
									<g:link controller="red" action="cursos"><i class="icon-edit"></i></g:link>
								</div>
							</div>
							<div class="box-content">
								<div class="page-header">
									<div class="btn-group">
										<p><g:link controller="materialCurso" action="index" params="['cursoId': params.cursoId]">
												<button class="btn btn-large dropdown-toggle btn-primary" >Material del curso</button></g:link>
											<g:link controller="tema" action="index" params="['cursoId': params.cursoId]">
												<button class="btn btn-large dropdown-toggle btn-primary" >Temas del curso</button></g:link>	
										</p>
									</div>         
								</div>
								<div class="row-fluid">            	 
									<div class="span12">												
										<g:if test="${com.cursado.Curso.get(params.cursoId).cuatDict == com.fiuba.Utilidades.CUAT_AMBOS}">
											<h3>Este curso se dicta durante todo el año</h3>
										</g:if>
										<g:else>
											<h3>Este curso se dicta solo durante el cuatrimestre ${com.cursado.Curso.get(params.cursoId).cuatDict}</h3>
										</g:else>
										<g:if test="${dictaCuatrimestre}">
											<g:if test="${cuatrimestre?.id}">
												<g:if test="${solicitoParticipacion}">	
													<span class="label label-success">Su solicitud de particion en el curso ya ha sido recibida</span>	      
												</g:if>
												<g:else>
													<p class="btn-group">
														<g:link action="solicitarParticipacionEnElCurso" params="['cursoId': params.cursoId]">
															<button class="btn btn-success" >Participar en el curso</button></g:link>
														</p>
												</g:else>
											</g:if>
											<g:else>
												<h3>Las opciones del cuatrimestre de cursada se visualizaran en breve</h3>
											</g:else>
										</g:if>
										<g:if test="${flash.message}">
											<div class="box-content alerts">
												<div class="alert alert-info">
													<button class="close" data-dismiss="alert" type="button"></button>
													<strong></strong> 
													${flash.message}
												</div>
											</div>    
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
