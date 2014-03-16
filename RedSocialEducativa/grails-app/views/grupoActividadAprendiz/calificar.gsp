<%@ page import="com.cursado.GrupoActividadAprendiz" %>
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
        <g:set var="entityName" value="${message(code: 'grupoActividadAprendiz.label', default: 'GrupoActividadAprendiz')}" />
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
									<g:link controller="curso" action="mediador" params="['cursoId': params.cursoId]">
										<button class="btn">${com.cursado.Curso.get(params.cursoId)}</button></g:link>
									<g:link controller="actividad" action="index" params="['cursoId': params.cursoId]">
										<button class="btn">Actividades del cuatrimestre ${com.cursado.Cuatrimestre.get(params.cuatrimestreId)}
										</button></g:link>	
								</p>
							</div>
							<!-- Fin: BREADCRUM -->  
						    <div class="box">
						        <div class="box-header">
						            <h2><i class="icon-plus"></i>Recalificación del aprendiz ${grupoActividadAprendizInstance.aprendiz} 
						            	del grupo ${grupoActividadAprendizInstance.grupo}</h2>
										<div class="box-icon"><g:link controller="grupoActividad" action="gruposMediador" 
											params="['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 
											'actividadId': params.actividadId]"><i class="icon-table"></i></g:link></div>
						        </div>
						        <g:if test="${flash.message}">
									<div class="box-content alerts">
										<div class="alert alert-error"><button class="close" data-dismiss="alert" type="button">
											</button><strong>${flash.message}</strong></div>
									</div>
								</g:if>
						        <div class="box-content">
						        	<g:form class="form-horizontal" action="guardarCalificacion" id="${params.id}" 
						        		params="['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 
						        		'actividadId': params.actividadId, 'grupoActividadId': params.grupoActividadId]">
										<g:hiddenField name="version" value="${grupoActividadAprendizInstance?.version}" />
										<g:hiddenField name="grupo.id" value="${grupoActividadAprendizInstance.grupo.id}"/>
										<g:hiddenField name="aprendiz.id" value="${grupoActividadAprendizInstance.aprendiz.id}"/>
							            <fieldset>		
							            	
							            	<div class="control-group">
												<label class="control-label" >Nota</label>			
												<div class="controls">
													<g:field name="nota" type="number decimal" value="${grupoActividadAprendizInstance.nota}" 
														style="width: 5%; text-align: center"/>	 
												</div>	
											</div>
							            	
							            	<div class="form-actions">
												<button type="submit" class="btn btn-primary">Recalificar</button>
											</div>		    
							            </fieldset>
						            </g:form> 
						        </div>
						    </div>
					    </div>
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
