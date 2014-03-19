<%@ page import="com.encuesta.PreguntaChoice" %>
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
        <g:set var="entityName" value="${message(code: 'preguntaChoice.label', default: 'PreguntaChoice')}" />
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
						            <h2><i class="icon-edit"></i>Editar</h2>
						            <div class="box-icon">
						                <g:link action="create" params="['cursoId': params.cursoId, 'encuestaId': params.encuestaId]">
						                	<i class="icon-plus"></i></g:link>
						                <g:link controller="encuesta" action="show" id="${params.encuestaId}" 
						                	params="['cursoId': params.cursoId]"><i class="icon-question-sign"></i></g:link>
						            </div>
						        </div>
						        <div class="box-content">
						        	<g:form class="form-horizontal" action="update" method="PUT" id="${preguntaChoiceInstance.id}" 
						        		params="['cursoId': params.cursoId, 'encuestaId': params.encuestaId, 'preguntaAntigua': pregunta]">
						        		<g:hiddenField name="version" value="${preguntaChoiceInstance?.version}" />
							            <fieldset>
							            	<g:render template="form"/>				
							            	<div class="form-actions">
												<button type="submit" class="btn btn-primary">Actualizar</button>
											</div>		    
							            </fieldset>
						            </g:form>    
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
