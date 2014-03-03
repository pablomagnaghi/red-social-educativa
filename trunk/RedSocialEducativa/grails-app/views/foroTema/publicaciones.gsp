<%@ page import="com.fiuba.ForoTema" %>
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
        <g:set var="entityName" value="${message(code: 'foroTema.label', default: 'ForoTema')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
 		<!-- Para el header y el panel lateral -->
    	<g:set var="varUsuarioService" bean="usuarioService"/>
    	<g:set var="varMediadorService" bean="mediadorService"/>
    	<g:set var="varAprendizService" bean="aprendizService"/>
    	<g:set var="usuario" value="${varUsuarioService.usuarioActual()}"/>
    	<g:set var="administrador" value="${com.fiuba.Administrador.findByUsuario(usuario)}"/>
    	<g:set var="cursosMediador" value="${varMediadorService.obtenerCursos(usuario)}"/>
    	<g:set var="cursosAprendiz" value="${varAprendizService.obtenerCursos(usuario)}"/>
 	
    	<div class="container-fluid-full">
			<div class="row-fluid">   
	            <g:render template="/templateRed/panel" />
	            <!-- start: Content -->
	            <!-- PANEL CENTRAL -->
	            <div id="content" class="span10">
					<g:if test="${flash.message}">
						<div class="message" role="status">${flash.message}</div>
					</g:if>
					<h2>Mediador = ${mediador}</h2>
					<h2>Aprendiz = ${aprendiz}</h2>
	                <g:render template="discusiones" />		
 				</div>
            	<!-- end: Content -->
        	</div>
        	<!--/fluid-row-->
        </div>
        <!--CLAVE ESTE DIV, SI SE SACA, NO APARECE NADA -->
        <div class="clearfix"></div>					
	</body>
</html>

<!-- BREADCRUMBS -->
<!-- <li><g:link class="create" action="general" params="['cursoId': params.cursoId, 'temaId': params.temaId]">
						<g:message code="Volver a foro Tema" /></g:link></li>	
				<li><g:link class="create" controller="publicacionTema" action="nueva" 
						params="['pubInicialId': params.pubInicialId, 'cursoId': params.cursoId, 'temaId': params.temaId]">
						<g:message code="Publicar respuesta" /></g:link></li>
				<g:if test="${mediador}">
					<li><g:link class="create" controller="publicacionTema" action="eliminar" id="${params.pubInicialId}" 
						params="['pubInicialId': params.pubInicialId, 'cursoId': params.cursoId, 'temaId': params.temaId]">
						<g:message code="Eliminar tema" /></g:link></li>
				</g:if>	 -->
