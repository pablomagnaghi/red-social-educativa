<%@ page import="com.material.MaterialTema" %>
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
        <g:set var="entityName" value="${message(code: 'materialTema.label', default: 'MaterialTema')}" />
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
		            <div class="row-fluid">
					    <div class="box span8">
					        <div class="box-header">     	
					            <h2><i class="icon-edit"></i>Editar</h2>
					            <div class="box-icon">
					                <g:link action="create" params="['cursoId': params.cursoId]"><i class="icon-plus"></i></g:link>
					            </div>
					        </div>
					        <h2>Curso: ${com.fiuba.Curso.get(params.cursoId)}</h2>
							<h2>Curso Id: ${params.cursoId}</h2>
							<h2>Tema ID: ${params.temaId}</h2>
					        <g:if test="${flash.message}">
								<div class="message" role="status">${flash.message}</div>
							</g:if>      
							<g:hasErrors bean="${materialTemaInstance}">
								<ul class="errors" role="alert">
									<g:eachError bean="${materialTemaInstance}" var="error">
										<li <g:if test="${error in org.springframework.validation.FieldError}">
												data-field-id="${error.field}"</g:if>>
											<g:message error="${error}"/></li>
									</g:eachError>
								</ul>
							</g:hasErrors>
					        <div class="box-content">
					        	<g:form class="form-horizontal" action="update" method="PUT" id="${materialTemaInstance.id}" 
					        		params="['cursoId': params.cursoId, 'temaId': params.temaId]">
					        		<g:hiddenField name="version" value="${materialTemaInstance?.version}" />
						            <g:hiddenField name="tema.id" value="${params.temaId}"/>
						            <g:hiddenField name="titulo" value="${materialTemaInstance.titulo}"/>
									<g:hiddenField name="responsable" value="${materialTemaInstance.responsable}"/>
						            <fieldset>
						            	<g:render template="form"/>			
						            	<div class="form-actions">
											<button type="submit" class="btn btn-primary">Actualizar</button>
										</div>		    
						            </fieldset>
					            </g:form>    
					        </div>
						</div>
					    <div class="span4"></div>
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

<!-- BREADCRUM -->
<!-- <li><g:link controller="tema" action="index" params="['cursoId': params.cursoId]">
					<g:message code="Lista de temas del curso ${com.fiuba.Curso.get(params.cursoId)}" args="[entityName]" /></g:link></li>
				<li><g:link controller="tema" action="show" id="${params.temaId}" params="['cursoId': params.cursoId]">
					<g:message code="Tema: ${com.fiuba.Tema.get(params.temaId)}" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create" params="['cursoId': params.cursoId, 'temaId': params.temaId]">
					<g:message code="default.new.label" args="[entityName]" /></g:link></li> -->