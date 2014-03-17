<%@ page import="com.material.MaterialGrupoActividad" %>
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
        <g:set var="entityName" value="${message(code: 'materialGrupoActividad.label', default: 'MaterialGrupoActividad')}" />
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
									<g:link controller="actividad" action="index" params="['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId]">
										<button class="btn">Actividades del cuatrimestre ${com.cursado.Cuatrimestre.get(params.cuatrimestreId)}</button></g:link>
								</p>
						    </div>
							<!-- Fin: BREADCRUM -->  
						    <div class="box">
						        <div class="box-header">     	
						            <h2><i class="icon-edit"></i>Editar</h2>
						            <div class="box-icon">
						                <g:link action="create" params="['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 
						                	'actividadId': params.actividadId, 'grupoActividadId': params.grupoActividadId]"><i class="icon-plus"></i></g:link>
						                <g:link controller="grupoActividad" action="grupoAprendiz" id="${params.grupoActividadId}"
											params="['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 'actividadId': params.actividadId, ]">
											<i class="icon-group"></i></g:link>	 	
						            </div>
						        </div>
						        <div class="box-content">
						            <g:uploadForm class="form-horizontal" action="update" method="PUT" id="${materialGrupoActividadInstance.id}" 
						        		params="['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 'actividadId': params.actividadId, 
										'grupoActividadId': params.grupoActividadId, 'tituloAnterior': titulo]"> 
						        		<g:hiddenField name="version" value="${materialGrupoActividadInstance?.version}" />
						        		<g:hiddenField name="archivo.id" value="${materialGrupoActividadInstance?.archivo?.id}"/>
							            <fieldset>
							            	<g:render template="form"/>		
							            	<div class="control-group">
												<label class="control-label">Cambiar archivo (32MB)</label>
												<div class="controls">
													<input type="file" name="archivoSubido"/>
												</div>
											</div>		
							            	<div class="form-actions">
												<button type="submit" class="btn btn-primary">Actualizar</button>
											</div>		    
							            </fieldset>
						            </g:uploadForm>    
	
						        </div>
							</div>
						</div>	
					    <div class="span2"></div>
					    <!--/span-->
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
