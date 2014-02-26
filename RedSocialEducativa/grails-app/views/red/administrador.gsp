<%@ page import="com.fiuba.Red" %>
<%@ page import="com.fiuba.UsuarioService" %>
<%
	def usuarioService = grailsApplication.classLoader.loadClass('com.fiuba.UsuarioService').newInstance()
%>

<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="red">
        <g:set var="entityName" value="Red Social Login" />
        <title><g:message code="Red Social Login" args="[entityName]" /></title>
    </head>
    <body>
        <!-- Para el header y el panel lateral -->
    	<g:set var="varUsuarioService" bean="usuarioService"/>
    	<g:set var="usuario" value="${varUsuarioService.usuarioActual()}"/>
    	<g:set var="administrador" value="${com.fiuba.Administrador.findByUsuario(usuario)}"/>
    	
    	<div class="container-fluid-full">
			<div class="row-fluid">
				<g:render template="/templateRed/panel" />
				<!-- start: Content -->
				<!-- PANEL CENTRAL -->
				<div id="content" class="span10">
					<g:render template="noticiasRed" />		
				</div>
				<!-- end: Content -->
			</div>
			<!--/fluid-row-->
		</div>
		<!--CLAVE ESTE DIV, SI SE SACA, NO APARECE NADA -->
        <div class="clearfix"></div>					
	</body>
</html>
