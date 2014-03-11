<%@ page import="com.foro.PublicacionGeneral" %>
<%
	def usuarioService = grailsApplication.classLoader.loadClass('com.fiuba.UsuarioService').newInstance()
%>
<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="red">
        <g:set var="entityName" value="${message(code: 'publicacionGeneral.label', default: 'PublicacionGeneral')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
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
	            	<div class="row-fluid">
	            		<div class="span2"></div>
					    <div class="box span8">
					        <div class="box-header">     	
					            <h2><i class="icon-edit"></i>Crear</h2>
					        </div>
							<div class="box-content">
								<g:form class="form-horizontal" action="guardar" params="['pubInicialId': params.pubInicialId]">
									<fieldset>
										<g:render template="form"/>			
										<div class="form-actions">
											<button type="submit" class="btn btn-primary">Crear</button>
										</div>		    
									</fieldset>
								</g:form>
							</div>
						</div>
						<div class="span2"></div>
					</div>		
 				</div>
            	<!-- end: Content -->
        	</div>
        	<!--/fluid-row-->
        </div>
        <!--CLAVE ESTE DIV, SI SE SACA, NO APARECE NADA -->
        <div class="clearfix"></div>					
	</body>
</html>
