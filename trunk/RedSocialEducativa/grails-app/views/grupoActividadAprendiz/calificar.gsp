<%@ page import="com.fiuba.GrupoActividadAprendiz" %>
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
					    <div class="box span8">
					        <div class="box-header">
					            <h2><i class="icon-plus"></i>Calificar</h2>
					            <div class="box-icon">
					                <a href="#" class="btn-setting"><i class="icon-wrench"></i></a>
					                <a href="#" class="btn-minimize"><i class="icon-chevron-up"></i></a>
					                <a href="#" class="btn-close"><i class="icon-remove"></i></a>
					            </div>
					        </div>
							<h2>Curso: ${com.fiuba.Curso.get(params.cursoId)}</h2>
							<h2>Curso Id: ${params.cursoId}</h2>
							<h2>Cuatrimestre Id: ${params.cuatrimestreId}</h2>
							<h2>Actividad Id: ${params.actividadId}</h2>
							<h2>GRupo Actividad Id: ${params.grupoActividadId}</h2>
							<h2>GRupo Actividad Id: ${grupoActividadAprendizInstance.grupo.id}</h2>
							<h2>Aprendiz Id: ${grupoActividadAprendizInstance.aprendiz.id}</h2>
							<h2>GRupo Actividad aprendiz Id: ${params.id}</h2>
					        <g:if test="${flash.message}">
								<div class="message" role="status">${flash.message}</div>
							</g:if>    
							<g:hasErrors bean="${grupoActividadAprendizInstance}">
								<ul class="errors" role="alert">
									<g:eachError bean="${grupoActividadAprendizInstance}" var="error">
										<li <g:if test="${error in org.springframework.validation.FieldError}">
												data-field-id="${error.field}"</g:if>>
											<g:message error="${error}"/></li>
									</g:eachError>
								</ul>
							</g:hasErrors>
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
												 <g:field name="nota" type="number" min="1" value="${grupoActividadAprendizInstance.nota}"/> 
											</div>	
										</div>
						            	
						            	<div class="form-actions">
											<button type="submit" class="btn btn-primary">Crear</button>
										</div>		    
						            </fieldset>
					            </g:form> 
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