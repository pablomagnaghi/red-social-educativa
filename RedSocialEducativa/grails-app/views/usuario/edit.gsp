<%@ page import="com.fiuba.Usuario" %>
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
        <g:set var="entityName" value="${message(code: 'usuario.label', default: 'Usuario')}" />
		<title><g:message code="default.create.label" args="[entityName]" /></title>
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
		            <div class="row-fluid">
			            <div class="span3"></div>
					    <div class="box span6">
					        <div class="box-header">     	
					            <h2><i class="icon-edit"></i>Mi perfil: ${usuario.nombres} ${usuario.apellido}</h2>
					            <div class="box-icon">
					                <g:link controller="red" action="revisarRol"><i class="icon-home"></i></g:link>
					            </div>
					        </div>
					        <div class="box-content">
					        	<g:uploadForm class="form-horizontal" action="update" method="PUT" id="${usuarioInstance.id}">
					        		<g:hiddenField name="version" value="${usuarioInstance?.version}" />
					        		<g:hiddenField name="username" value="${usuarioInstance.username}" />
					        		<g:hiddenField name="password" value="${usuarioInstance.password}" />
						            <fieldset>
						            	<g:render template="form"/>		
						            	<div class="form-actions">
											<button type="submit" class="btn btn-primary">Actualizar</button>
										</div>		    
						            </fieldset>
					            </g:uploadForm>
					        </div>
					    </div>
					    <div class="span3"></div>
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
