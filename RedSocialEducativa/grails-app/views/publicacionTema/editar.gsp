<%@ page import="com.fiuba.PublicacionTema" %>
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
		<g:set var="entityName" value="${message(code: 'publicacionTema.label', default: 'PublicacionTema')}" />
		<title><g:message code="default.edit.label" args="[entityName]" /></title>
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
					    <div class="box span12">
					        <div class="box-header">     	
					            <h2><i class="icon-edit"></i>Editar</h2>
					        </div>
							<g:if test="${flash.message}">
								<div class="message" role="status">${flash.message}</div>
							</g:if>
							<g:hasErrors bean="${publicacionTemaInstance}">
								<ul class="errors" role="alert">
									<g:eachError bean="${publicacionTemaInstance}" var="error">
										<li <g:if test="${error in org.springframework.validation.FieldError}">
											data-field-id="${error.field}"</g:if>>
											<g:message error="${error}"/></li>
									</g:eachError>
								</ul>
							</g:hasErrors>
							<div class="box-content">
								<g:form class="form-horizontal" action="actualizar" method="PUT" id="${publicacionTemaInstance.id}" 
									params="['pubInicialId': params.pubInicialId, 'cursoId': params.cursoId, 'temaId': params.temaId]">
									<g:hiddenField name="version" value="${publicacionTemaInstance?.version}" />
									<g:hiddenField name="foro.id" value="${com.fiuba.ForoTema.findByTema(com.fiuba.Tema.get(params.temaId)).id}"/>
									<g:hiddenField name="dni" value="${usuario.username}" />
									<g:hiddenField name="responsable" value="${usuario}"/>
	
									<fieldset>
										<g:render template="form"/>	
										<div class="form-actions">
											<button type="submit" class="btn btn-primary">Crear</button>
										</div>		    
									</fieldset>				
								</g:form>
							</div>
						</div>
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

<!--  PARA EL BREADCRUMB -->

<!-- <li><g:link class="create" controller="foroTema" action="general" params="['cursoId': params.cursoId, 'temaId': params.temaId]">
					<g:message code="Foro Tema" /></g:link></li>
				<li><g:link class="list" controller="foroTema" action="publicaciones" 
					id="${params.pubInicialId}" params="['cursoId': params.cursoId, 'temaId': params.temaId]">
					<g:message code="Tema actual" /></g:link></li> -->
