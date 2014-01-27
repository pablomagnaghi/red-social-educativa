<%@ page import="com.fiuba.Red" %>
<!doctype html>
<html>
    <head>
        <meta name="layout" content="main">
        <g:set var="entityName" value="Red Social Login" />
        <title><g:message code="Red Social Login" args="[entityName]" /></title>
    </head>
    <body>
	    <div id="create-endUser" class="content scaffold-create" role="main">
	    	<ul class="menuBar">
				<li id="tituloRed"><g:message code="Red Social Educativa FIUBA" /></li>
				<li><msg:showNotifications cantMensajes="${cantMensajes}"/></li>
			</ul>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:hasErrors bean="${miembroInstance}">
				<ul class="errors" role="alert">
					<g:eachError bean="${miembroInstance}" var="error">
						<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>>
							<g:message error="${error}"/>
		                </li>
					</g:eachError>
				</ul>
			</g:hasErrors>
			<sec:ifLoggedIn>
				Bienvenido <sec:username/> (<g:link controller='logout'>Salir</g:link>)
			</sec:ifLoggedIn>
			<sec:ifNotLoggedIn>
				<div>
					<g:link class="list" action="solicitarMembresia">
			       	<g:message code="Solicitar Membresía" /></g:link>
				</div>
        		<div id="loginForm">
           			<form method="POST" action="${resource(file: 'j_spring_security_check')}">
                		<table>
                  			<tr>
                    			<td>DNI:</td>
                    			<td><input type='text' class='text_' name='j_username' id='username'/></td>
                  			</tr>
                  			<tr>
                    			<td>Password:</td>
                    			<td><input type='password' class='text_' name='j_password' id='password'/></td>
                  			</tr>
                  			<tr>
                    			<td>Recordarme</td>
                    			<td><input type='checkbox' class='chk' id='remember_me' name='_spring_security_remember_me'/></td>
                  			</tr>
                  			<tr>
                    			<td colspan="2"><g:submitButton name="login" value="Login"/></td>
                  			</tr>
                		</table>
            		</form>
				</div>
			</sec:ifNotLoggedIn>
		</div>
		<div>
			<g:if test="${administrador}">
				<br><h2>Bienvenido administrador ${administrador}</h2><br> 
				<span class = "menuButton">
					<g:link action="general" controller="administrador">
					<g:message code="Tareas administrativas" /></g:link>
				</span>
				<br>
			</g:if>
		</div>
		<div>
			<span>
				<g:if test="${cursosMediador}">
					<br><h2>Mis cursos como mediador</h2><br> 
					<ol>
						<g:each in="${cursosMediador}" var="cursoMediador">
							<li>
								<span class = "menuButton">
									<g:link action="mediador" controller="curso" params="['cursoId': cursoMediador.id]">
									<g:message code="${com.fiuba.Materia.get(cursoMediador.materia.id)}-${cursoMediador}" /></g:link>
								</span>
								<br>
							</li>
						</g:each>
					</ol>
				</g:if>
			</span>
			<span>
				<g:if test="${cursosAprendiz}">
					<br><h2>Mis cursos como aprendiz</h2><br> 
					<ol>
						<g:each in="${cursosAprendiz}" var="cursoAprendiz">
							<li>
								<span class = "menuButton">
									<g:link action="aprendiz" controller="curso" params="['cursoId': cursoAprendiz.id]">
									<g:message code="${com.fiuba.Materia.get(cursoAprendiz.materia.id)}-${cursoAprendiz}" /></g:link>
								</span>
								<br>
							</li>
						</g:each>
					</ol>
				</g:if>
			</span>
		</div>
		<br>
		<div>
			<hr>
	       	<h2>Cartelera general</h2>
	       	<g:if test="${noticiasRed}">
				<table>
				<thead>
					<tr>
						<td>Autor</td>
						<td>Noticia</td>				
					</tr>
				</thead>
				<tbody>	
					<g:each in="${noticiasRed}">	
						<g:if test="${it.visibilidad}">
							<tr>
								<td>
									<p>Administrador: ${it.administrador}</p>
									<p>Publicado: ${it.fecha} - ${it.hora} <p>
								</td>
								<td>${it.texto}</td>
							</tr>
						</g:if>
					</g:each>
				</tbody>
				</table>
			</g:if>
		<h5>Agregar PAGINACION</h5>
		</div>	
		<div>
			<hr>
	       	<h2><g:link controller="foroGeneral" action="general">Foro general</g:link></h2>
	       	
	       	TEMA |COMENZADO POR |REPLICAS |ULTIMO MENSAJE

		</div>
		<div>
			<br><hr><br>
			<h2>Sector destinado a visualizar informacion y material de los cursos (foros, temas y material general)</h2>
			<br>
			<h3>Los cursos de la red Social son estos</h3>
			<br>
				<g:each in="${cursos}" var="cursoInstance">
					<p><g:link action="revisarRol" controller="curso" params="['cursoId': cursoInstance.id]">
							${com.fiuba.Materia.get(cursoInstance.materia.id)}-${cursoInstance}</g:link><p>	
					<br>
				</g:each>
			<div class="pagination">
				<g:paginate total="${cursoCant ?: 0}" />
			</div>
		</div>
		<g:render template='/login/ajaxLogin'/>
		
	</body>
</html>