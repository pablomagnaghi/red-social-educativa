<%@ page import="com.fiuba.ForoGeneral" %>
<%
	def usuarioService = grailsApplication.classLoader.loadClass('com.fiuba.UsuarioService').newInstance()
%>
<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="red">
        <g:set var="entityName" value="${message(code: 'foroGeneral.label', default: 'ForoGeneral')}" />
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
					<g:if test="${flash.message}">
						<div class="message" role="status">${flash.message}</div>
					</g:if>
					<g:hasErrors bean="${publicacionGeneralInstance}">
						<ul class="errors" role="alert">
							<g:eachError bean="${publicacionGeneralInstance}" var="error">
								<li <g:if test="${error in org.springframework.validation.FieldError}">
									data-field-id="${error.field}"</g:if>>
									<g:message error="${error}"/></li>
							</g:eachError>
						</ul>
					</g:hasErrors>
					<div class="box-content">
						<g:form class="form-horizontal" action="actualizar" method="PUT" id="${publicacionGeneralInstance.id}" 
							params="['pubInicialId': params.pubInicialId]">
							<g:hiddenField name="version" value="${publicacionGeneralInstance?.version}" />
							<fieldset>
								<g:render template="form"/>	
								<g:hiddenField name="titulo" value="${publicacionGeneralInstance.titulo}"/>		
								<div class="form-actions">
									<button type="submit" class="btn btn-primary">Crear</button>
								</div>		    
							</fieldset>
						</g:form>
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
