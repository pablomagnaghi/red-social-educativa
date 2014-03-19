<%@ page import="com.encuesta.Encuesta" %>
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
        <g:set var="entityName" value="${message(code: 'encuesta.label', default: 'Encuesta')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
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
		            	<div class="span2"></div>
					    <div class="span8"> 
						    <!-- comienzo: BREADCRUM -->
							<div class="box-content buttons">
								<p class="btn-group">
									<g:link controller="red" action="revisarRolEnCurso" params="['cursoId': params.cursoId]">
										<button class="btn">${com.cursado.Curso.get(params.cursoId)}</button></g:link>
								</p>
						    </div>
							<!-- Fin: BREADCRUM -->  
							<div class="box"> 
						        <div class="box-header">
						            <h2><i class="icon-plus"></i>Crear</h2>
						            <div class="box-icon">
					               		<g:link action="index" params="['cursoId': params.cursoId]"><i class="icon-table"></i></g:link>
					            	</div>
					        	</div>
						        <div class="box-content">
						        	<g:form class="form-horizontal" action="save" params="['cursoId': params.cursoId]">
							            <fieldset>		
							            	<g:render template="form"/>	
							            	<div class="form-actions">
												<button type="submit" class="btn btn-primary">Crear</button>
											</div>		    
							            </fieldset>
						            </g:form>
						        </div>
						    </div>    
					    </div>
					    <!--/span-->
					    <div class="span2"></div>
					</div>
					<!--/row-->    		
 				</div>
            	<!-- end: Content -->
        	</div>
        	<!--/fluid-row-->
        </div>
        <!--CLAVE ESTE DIV, SI SE SACA, NO APARECE NADA -->
        <div class="clearfix"></div>					
	</body>
</html>
