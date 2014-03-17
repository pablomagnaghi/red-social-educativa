<%@ page import="com.cursado.GrupoActividad" %>
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
       <g:set var="entityName" value="${message(code: 'grupoActividad.label', default: 'GrupoActividad')}" />
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
									<g:link controller="curso" action="aprendiz" params="['cursoId': params.cursoId]">
										<button class="btn">${com.cursado.Curso.get(params.cursoId)}</button></g:link>
									<g:link controller="actividad" action="index" params="['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId]">
										<button class="btn">Actividades del cuatrimestre ${com.cursado.Cuatrimestre.get(params.cuatrimestreId)}</button></g:link>	
								</p>
						    </div>
							<!-- Fin: BREADCRUM -->
						    <div class="box">
						        <div class="box-header">
						            <h2><i class="icon-plus"></i>Crear grupo para la actividad ${com.cursado.Actividad.get(params.actividadId)}</h2>
						            <div class="box-icon">
						                <g:link action="gruposAprendiz" params="['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 
						                	'actividadId': params.actividadId]"><i class="icon-group"></i></g:link>
						            </div>
						        </div>
						        <div class="box-content">
						        	<g:form class="form-horizontal" action="guardar" params="['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 
						        		'actividadId': params.actividadId, 'numGrupo': numGrupo]" >
						        		<g:hiddenField name="actividad.id" value="${params.actividadId}"/>    		
							            <g:hiddenField name="numero" value="${numGrupo}"/>
							            <fieldset>		
							            	<g:if test="${flash.message}">
												<div class="box-content alerts">
													<div class="alert alert-error"><button class="close" data-dismiss="alert" type="button"></button><strong>${flash.message}</strong></div>
												</div>
											</g:if>
											<g:hasErrors bean="${grupoActividadInstance}">
												<div class="box-content alerts">
													<div class="alert alert-error"><button class="close" data-dismiss="alert" type="button"></button><strong>Revise el formulario</strong></div>										
												</div>
											</g:hasErrors>
											<!-- NOMBRE -->
											<g:if test="${!hasErrors(bean:grupoActividadInstance, field: 'nombre', 'error')}">
												<div class="control-group">
													<label class="control-label" >Nombre</label>			
													<div class="controls"><g:textField name="nombre" value="${grupoActividadInstance?.nombre}" style='width: 62%' maxlength="64"/></div>	
												</div>		
											</g:if>
											<g:else>
												<div class="control-group error">
													<label class="control-label" >Nombre</label>			
													<div class="controls">
														<g:textField name="nombre" value="${grupoActividadInstance?.nombre}" style='width: 62%' maxlength="64"/>
														<span class="help-inline"><g:renderErrors bean="${grupoActividadInstance}" as="list" field="nombre"/></span>
													</div>	
												</div>	 
											</g:else>
							            	<div class="form-actions">
												<button type="submit" class="btn btn-primary">Crear</button>
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
