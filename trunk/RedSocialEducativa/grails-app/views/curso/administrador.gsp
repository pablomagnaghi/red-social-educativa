<%@ page import="com.fiuba.Curso" %>
<%@ page import="com.fiuba.UsuarioService" %>
<%
	def usuarioService = grailsApplication.classLoader.loadClass('com.fiuba.UsuarioService').newInstance()
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
    	<g:set var="usuario" value="${varUsuarioService.usuarioActual()}"/>
    	<g:set var="administrador" value="${com.fiuba.Administrador.findByUsuario(usuario)}"/>
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
									Bienvenido administrador ${usuario} al curso ${com.fiuba.Curso.get(params.cursoId)} de 
									la asignatura ${com.fiuba.Curso.get(params.cursoId).asignatura}</h2>
								<div class="box-icon">
									<a href="#" class="btn-minimize"><i class="icon-chevron-up"></i></a>
								</div>
							</div>
							<div class="box-content">
								<div class="page-header">
									<g:render template="tituloGeneral"></g:render>
								</div>         
								<div class="row-fluid">            	 
									<div class="span9">		
										SOLO VE MATERIAL GENERAL Y TEMAS CON FOROS DE TEMAS
											<h2>Curso id: ${params.cursoId}</h2>
											<h2>Dicta cuatrimestre: ${dictaCuatrimestre}</h2>
											<h2>cuat id: ${cuatrimestre?.id}</h2>
											<h2>Noticia curso: ${noticiasCurso}</h2>   
									</div>
									<div class="span3">	
									</div>
								</div>
							</div>
						</div><!--/span-->
					</div>
					<div class="row-fluid">
						<g:render template="opcion2"/>      
					</div>	
					<div class="row-fluid">
						<g:render template="subNivel"/>      
					</div>		
					<div class="row-fluid">
						<g:render template="materiales"/>      
						<g:render template="temas"/>
					</div>	
				</div>			
			</div><!--/fluid-row-->	
		</div>
		<!-- end: Content -->
				
        <!--CLAVE ESTE DIV, SI SE SACA, NO APARECE NADA -->
        <div class="clearfix"></div>					
	</body>
</html>
