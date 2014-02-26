<%@ page import="com.fiuba.Curso" %>
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
        <g:set var="entityName" value="Red Social Login" />
        <title><g:message code="Red Social Login" args="[entityName]" /></title>
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

					<g:if test="${flash.message}">
						<div class="message" role="status">${flash.message}</div>
					</g:if>

					<div class="row-fluid">
						<div class="box span9">
							<div class="box-header">
								<h2><i class="icon-font"></i><span class="break"></span>${com.fiuba.Curso.get(params.cursoId)}</h2>
							</div>
							<div class="box-content">
								<div class="page-header">
									<h1>Bienvenido mediador ${usuario} al curso ${com.fiuba.Curso.get(params.cursoId).nroRelativo} de la 
										materia ${com.fiuba.Curso.get(params.cursoId).materia}</h1>
									<g:if test="${cuatrimestre?.id}">
										<p>"TODAVIA NO HAY UN CUATRIMESTRE ACTUAL. Revisar Consolidar cuatrimestre" (poner esto con alerta)</p>
									</g:if>	
									
										<h2>Curso id: ${params.cursoId}</h2>
										<h2>Dicta cuatrimestre: ${dictaCuatrimestre}</h2>
										<h2>cuat id: ${cuatrimestre?.id}</h2>
										<h2>Noticia curso: ${noticiasCurso}</h2>
										
										<g:render template="general"/>
										<g:render template="noticias"/>	
								</div>     
								<div class="row-fluid">            
									<div class="span4">
										<h3>Sample text and paragraphs</h3>
										<p>
										Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cn. Pellentesque non pulvinar nisi.
										</p>						
									</div>
									<div class="span4">
										<h3>Example body text</h3>
										<p>Nullam quis risus eget urna mollis ornare vel eu leo. Cum sociis natoque penatibus et magnis</p> 
									</div>
									<div class="span4 ">
										<div class="well">
											<h1>h1. Heading 1</h1>
										</div>
									</div>
								</div><!--/row -->                           	 
							</div>
						</div><!--/span-->
						
						<div class="box span3">
							<div class="box-header" data-original-title="">
								<h2><i class="icon-list"></i><span class="break"></span>Tareas mediador</h2>
								<div class="box-icon">
					                <a href="#" class="btn-setting"><i class="icon-wrench"></i></a>
					                <a href="#" class="btn-minimize"><i class="icon-chevron-up"></i></a>
					                <a href="#" class="btn-close"><i class="icon-remove"></i></a>
		           				 </div>
							</div>
								<div class="box-content">
									<ul>
										<li>Generales
											<ul>
												<li><g:link controller="materialCurso" action="index" params="['cursoId': params.cursoId]">
														Material</g:link></li>
												<li><g:link controller="tema" action="index" params="['cursoId': params.cursoId]">
														Temas</g:link></li>
												<li><g:link controller="evaluacion" action="index" params="['cursoId': params.cursoId]">
														Evaluaciones</g:link></li>		
												<li><g:link controller="cuatrimestre" action="create" params="['cursoId': params.cursoId]">
														Consolidar cuatrimestre ${cuatrimestres.first()}</g:link></li>				
												<li><g:link  controller="cuatrimestre" action="indexHistoriales" params="['cursoId': params.cursoId]">
													Historial de cuatrimestres</g:link></li>	
											</ul>
										</li>		
										<g:if test="${cuatrimestre?.id}">
											<li>Cuatrimestrales
												<ul>
													<li><g:link controller="aprendiz" action="index" params="['cursoId': params.cursoId, 
														'cuatrimestreId': cuatrimestre?.id]">Aprendices</g:link></li>
													<li><g:link controller="noticiaCurso" action="index" params="['cursoId': params.cursoId, 
														'cuatrimestreId': cuatrimestre?.id]">Cartelera</g:link></li>	
													<li><g:link controller="foroCurso" action="general" params="['cursoId': params.cursoId, 
														'cuatrimestreId': cuatrimestre?.id]">Foro de cursada"</g:link></li>	
													<li><g:link controller="aprendiz" action="estadisticas" params="['cursoId': params.cursoId, 
														'cuatrimestreId': cuatrimestre?.id]">Estadisticas</g:link></li>	
													<li><g:link controller="actividad" action="index" params="['cursoId': params.cursoId, 
														'cuatrimestreId': cuatrimestre?.id]">Actividades</g:link></li>		
												</ul>
											</li>
										</g:if>
									</ul>
								</div>
							</div>	
						</div>				
						<div class="box span3">
							<div class="box-header" data-original-title="">
								<h2><i class="icon-list"></i><span class="break"></span>Material del curso</h2>
							</div>
							<div class="box-content">
								<ol>
								  <li>Lorem ipsum dolor sit amet</li>
								  <li>Consectetur adipiscing elit</li>
								  <li>Integer molestie lorem at massa</li>
								  <li>Facilisis in pretium nisl aliquet</li>
								  <li>Nulla volutpat aliquam velit</li>
								  <li>Faucibus porta lacus fringilla vel</li>
								  <li>Aenean sit amet erat nunc</li>
								  <li>Eget porttitor lorem</li>
								</ol>           
							</div>
						</div><!--/span-->
						
						<div class="box span3">
							<div class="box-header">
								<h2><i class="icon-list"></i>Temas del curso</h2>
							</div>
								<div class="box-content">
									<dl>
									  <dt>Description lists</dt>
									  <dd>A description list is perfect for defining terms.</dd>
									  <dt>Euismod</dt>
									  <dd>Vestibulum id ligula porta felis euismod semper eget lacinia odio sem nec elit.</dd>
									  <dd>Donec id elit non mi porta gravida at eget metus.</dd>
									  <dt>Malesuada porta</dt>
									  <dd>Etiam porta sem malesuada magna mollis euismod.</dd>
									</dl>            
								</div>
							</div>	
						</div>
					</div><!--/row-->			
				<div>	
			</div><!--/fluid-row-->
		</div>
		<!-- end: Content -->
				
        <!--CLAVE ESTE DIV, SI SE SACA, NO APARECE NADA -->
        <div class="clearfix"></div>					
	</body>
</html>








