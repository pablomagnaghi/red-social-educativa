<%@ page import="com.fiuba.EvaluacionAprendiz" %>
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
        <g:set var="entityName" value="${message(code: 'evaluacionAprendiz.label', default: 'EvaluacionAprendiz')}" />
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
	            	<!-- comienzo: BREADCRUM -->
					<div class="box-content buttons">
						<p class="btn-group">
							<g:link controller="curso" action="mediador" params="['cursoId': params.cursoId]">
								<button class="btn">${com.fiuba.Curso.get(params.cursoId)}</button></g:link>
							<g:if test="${params.cuatrimestreId}">	
								<g:link controller="aprendiz" action="index" params="['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId]">
									<button class="btn">Aprendices curso ${com.fiuba.Curso.get(params.cursoId)} cuatrimestre 
										${com.fiuba.Cuatrimestre.get(params.cuatrimestreId)}</button></g:link>	
							</g:if>
							<g:else>
								<g:link controller="aprendiz" action="aprendicesCurso" params="['cursoId': params.cursoId]">
									<button class="btn">Aprendices curso ${com.fiuba.Curso.get(params.cursoId)}</button></g:link>	
							</g:else>		
						</p>
				    </div>
					<!-- Fin: BREADCRUM --> 
	                <g:render template="aprendiz" />		
 				</div>
            	<!-- end: Content -->
        	</div>
        	<!--/fluid-row-->
        </div>
        <!--CLAVE ESTE DIV, SI SE SACA, NO APARECE NADA -->
        <div class="clearfix"></div>					
	</body>
</html>