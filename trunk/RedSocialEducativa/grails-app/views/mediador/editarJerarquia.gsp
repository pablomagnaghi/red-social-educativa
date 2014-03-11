<%@ page import="com.fiuba.Mediador" %>
<%
	def usuarioService = grailsApplication.classLoader.loadClass('com.fiuba.UsuarioService').newInstance()
%>
<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="red">
        <g:set var="entityName" value="${message(code: 'mediador.label', default: 'Mediador')}" />
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
					            <h2><i class="icon-edit"></i>Editar</h2>
					            <div class="box-icon">
					                <g:link action="create"><i class="icon-plus"></i></g:link>
					                <g:link action="index"><i class="icon-table"></i></g:link>
					            </div>
					        </div>
					        <div class="box-content">
					        	<g:form class="form-horizontal" action="actualizarJerarquia" method="PUT" id="${mediadorInstance.id}" >
					        		<g:hiddenField name="version" value="${mediadorInstance?.version}" />
					        		<g:hiddenField name="rol.id" value="${mediadorInstance.rol.id}"/>
					        		<g:hiddenField name="usaurio.id" value="${mediadorInstance.usuario.id}" />
					        		<g:hiddenField name="curso.id" value="${mediadorInstance.usuario.id}" />
						            <fieldset>
						            	<div class="control-group">
											<label class="control-label" >Jerarquia</label>			
											<div class="controls">
												<g:select name="jerarquia" from="${['1-Profesor', '2-JTP', '3-AP', '4-AS', '5-Colaborador']}" />
											</div>	
										</div>			
						            	<div class="form-actions">
											<button type="submit" class="btn btn-primary">Actualizar</button>
										</div>		    
						            </fieldset>
					            </g:form>
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
