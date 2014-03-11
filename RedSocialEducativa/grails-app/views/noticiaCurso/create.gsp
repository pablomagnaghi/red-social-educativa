<%@ page import="com.cartelera.NoticiaCurso" %>
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
        <g:set var="entityName" value="${message(code: 'noticiaCurso.label', default: 'NoticiaCurso')}" />
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
					<div class="box span4">
						<div class="box-header">
							<h2><i class="icon-plus"></i>Crear</h2>
							<div class="box-icon">
								<div class="box-icon"><g:link action="index"><i class="icon-table"></i></g:link></div>
							</div>
						</div>       
						<h2>mediadorID: ${mediadorId}</h2>
						<h2>Curso: ${com.fiuba.Curso.get(params.cursoId)}</h2>
						<h2>Curso Id: ${params.cursoId}</h2>
						<h2>Cuatrimestre Id: ${params.cuatrimestreId}</h2>
						<div class="box-content">
							<g:form class="form-horizontal" action="save" params="['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId]">
								<g:hiddenField name="cuatrimestre.id" value="${params.cuatrimestreId}"/>
								<g:hiddenField name="mediador.id" value="${mediadorId}"/>
								<fieldset>	
									<g:render template="form"/>			
									<div class="form-actions">
										<button type="submit" class="btn btn-primary">Crear</button>
									</div>		    
								</fieldset>
							</g:form>
						</div>	
					</div>
					<!--/span-->
				</div>
				<!-- end: Content -->
			</div>
		<!--/fluid-row-->
        </div>
        <!--CLAVE ESTE DIV, SI SE SACA, NO APARECE NADA -->
        <div class="clearfix"></div>					
	</body>
</html>

