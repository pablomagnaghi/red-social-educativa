<%@ page import="com.fiuba.Calendario" %>
<%
	def usuarioService = grailsApplication.classLoader.loadClass('com.fiuba.UsuarioService').newInstance()
%>
<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="red">
		<g:set var="entityName" value="${message(code: 'calendario.label', default: 'Calendario')}" />
		<title><g:message code="default.edit.label" args="[entityName]" /></title>
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
					            </div>
					        </div>
					        <g:if test="${flash.message}">
								<div class="message" role="status">${flash.message}</div>
							</g:if>      
							<g:hasErrors bean="${calendarioInstance}">
								<ul class="errors" role="alert">
									<g:eachError bean="${calendarioInstance}" var="error">
										<li <g:if test="${error in org.springframework.validation.FieldError}">
												data-field-id="${error.field}"</g:if>>
											<g:message error="${error}"/></li>
									</g:eachError>
								</ul>
							</g:hasErrors>
					        <div class="box-content">
					        	<g:form class="form-horizontal" action="update" method="PUT" id="${calendarioInstance.id}">
					        		<g:hiddenField name="version" value="${calendarioInstance?.version}" />
						            <fieldset>
						            	<g:render template="form"/>			
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




