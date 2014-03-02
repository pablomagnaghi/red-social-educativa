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
					            <h2><i class="icon-plus"></i>Crear</h2>
					            <div class="box-icon">
					                <a href="#" class="btn-setting"><i class="icon-wrench"></i></a>
					                <a href="#" class="btn-minimize"><i class="icon-chevron-up"></i></a>
					                <a href="#" class="btn-close"><i class="icon-remove"></i></a>
					            </div>
					        </div>
					        <g:if test="${flash.message}">
								<div class="message" role="status">${flash.message}</div>
							</g:if>      
							<g:hasErrors bean="${mediadorInstance}">
								<ul class="errors" role="alert">
									<g:eachError bean="${mediadorInstance}" var="error">
										<li <g:if test="${error in org.springframework.validation.FieldError}">
												data-field-id="${error.field}"</g:if>>
											<g:message error="${error}"/></li>
									</g:eachError>
								</ul>
							</g:hasErrors>
					        <div class="box-content">
					        	<g:form class="form-horizontal" action="save">
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
