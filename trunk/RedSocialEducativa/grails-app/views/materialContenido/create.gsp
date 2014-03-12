<%@ page import="com.material.MaterialContenido" %>
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
        <g:set var="entityName" value="${message(code: 'materialContenido.label', default: 'MaterialContenido')}" />
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
							<h2>Curso: ${com.fiuba.Curso.get(params.cursoId)}</h2>
							<h2>Curso Id: ${params.cursoId}</h2>
							<h2>Tema: ${params.temaId}</h2>
							<h2>Contenido: ${params.contenidoId}</h2>
					        <g:if test="${flash.message}">
								<div class="message" role="status">${flash.message}</div>
							</g:if>    
							<g:hasErrors bean="${materialContenidoInstance}">
								<ul class="errors" role="alert">
									<g:eachError bean="${materialContenidoInstance}" var="error">
										<li <g:if test="${error in org.springframework.validation.FieldError}">
												data-field-id="${error.field}"</g:if>>
											<g:message error="${error}"/></li>
									</g:eachError>
								</ul>
							</g:hasErrors>
					        <div class="box-content">
					        	<g:form class="form-horizontal" action="save" params="['cursoId': params.cursoId, 'temaId': params.temaId, 'contenidoId': params.contenidoId]" >
					        		<g:hiddenField name="contenido.id" value="${params.contenidoId}"/>
						            <g:hiddenField name="responsable" value="${mediador.usuario}-${mediador?.jerarquia}"/>
						            <fieldset>		
										<div class="control-group">
											<label class="control-label" >Titulo</label>			
											<div class="controls">
												<g:textField name="titulo" value="${materialContenidoInstance?.titulo}"/>
											</div>	
										</div>
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

<!-- <li><a class="home" href="${createLink(uri: '/')}">
					<g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="mediador" controller="curso" params="['cursoId': params.cursoId]">
					<g:message code="Pagina principal de mediador"/></g:link></li>			
				<li><g:link class="create" action="menuMediador" controller="curso" params="['cursoId': params.cursoId]">
					<g:message code="Tareas mediador" /></g:link></li>		
				<li><g:link controller="tema" action="index" id="${params.temaId}" params="['cursoId': params.cursoId]">
					<g:message code="Lista de temas del curso ${com.fiuba.Curso.get(cursoId)}" args="[entityName]" /></g:link></li>
				<li><g:link controller="tema" action="edit" id="${params.temaId}" params="['cursoId': params.cursoId]">
					<g:message code="Tema: ${com.fiuba.Tema.get(params.temaId)}" args="[entityName]" /></g:link></li>
				<li><g:link controller="contenido" action="show" id="${params.contenidoId}" params="['cursoId': params.cursoId, 'temaId': params.temaId]">
					<g:message code="Contenido: ${com.fiuba.Contenido.get(params.contenidoId)}" args="[entityName]" /></g:link></li>		
				<li><g:link class="create" action="create" params="['cursoId': params.cursoId, 'temaId': params.temaId, 'contenidoId': params.contenidoId]">
					<g:message code="default.new.label" args="[entityName]" /></g:link></li> -->