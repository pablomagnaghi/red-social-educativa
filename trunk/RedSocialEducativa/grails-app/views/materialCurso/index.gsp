<%@ page import="com.fiuba.MaterialCurso" %>
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
        <g:set var="entityName" value="${message(code: 'materialCurso.label', default: 'MaterialCurso')}" />
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
		            <p class="breadcrumb">
					  	<g:link controller="curso" action="mediador" controller="curso" params="['cursoId': params.cursoId]">
					  		${com.fiuba.Curso.get(params.cursoId)}</g:link>
					</p>
					
					<div class="box-content buttons">
						<p class="btn-group">
							<button class="btn">Left</button>
				  			<button class="btn">Middle</button>
				        	<button class="btn">Right</button>
						</p>
				   		<p>
				   			<button class="btn btn-small"><i class="icon-star"></i> Icon button</button>
				 			<button class="btn btn-small btn-primary">Small button</button>
							<button class="btn btn-small btn-danger">Small button</button>
							<button class="btn btn-small btn-warning">Small button</button>
				 			<button class="btn btn-small btn-success">Small button</button>
							<button class="btn btn-small btn-info">Small button</button>
							<button class="btn btn-small btn-inverse">Small button</button>
						</p>
				    </div>
					<!-- Fin: BREADCRUM -->            
	            
					<g:if test="${flash.message}">
						<div class="message" role="status">${flash.message}</div>
					</g:if>
	                <g:render template="tabla" />		
 				</div>
            	<!-- end: Content -->
        	</div>
        	<!--/fluid-row-->
        </div>
        <!--CLAVE ESTE DIV, SI SE SACA, NO APARECE NADA -->
        <div class="clearfix"></div>					
	</body>
</html>
