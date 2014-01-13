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
			<h1><g:message code="Red Social Educativa FIUBA" /></h1>
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
				<h2>Bienvenido administrador ${administrador}</h2> 
				<span class = "menuButton">
					<g:link action="general" controller="administrador">
					<g:message code="Tareas administrativas" /></g:link>
				</span>
			</g:if>
		</div>
		<div>
			<span>
				<g:if test="${cursosMediador}">
					<h2>Mis cursos como mediador</h2> 
					<ol>
						<g:each in="${cursosMediador}" var="cursoMediador">
							<li>
								<span class = "menuButton">
									<g:link action="mediador" controller="curso" id="${cursoMediador.id}">
									<g:message code="${cursoMediador}" /></g:link>
								</span>
							</li>
						</g:each>
					</ol>
				</g:if>
			</span>
			<span>
				<g:if test="${cursosAprendiz}">
					<h2>Mis cursos como aprendiz</h2> 
					<ol>
						<g:each in="${cursosAprendiz}" var="cursoAprendiz">
							<li>
								<span class = "menuButton">
									<g:link action="aprendiz" controller="curso" id="${cursoAprendiz.id}">
									<g:message code="${cursoAprendiz}" /></g:link>
								</span>
							</li>
						</g:each>
					</ol>
				</g:if>
			</span>
		</div>
		<div>
	       		<br><h2>Proximamente la cartelera</h2>
		</div>
		<div>
			<br><hr><br>
			<h2>Sector destinado a visualizar informacion y material de los cursos (foros, temas y material general)</h2>
			<br>
			<h3>Los cursos de la red Social son estos</h3>
			<br>
			<ol>
				<g:each in="${materias}" var="materia">
					<g:each in="${materia.cursos}" var="curso">
						<li>
							<span class = "menuButton">
								<g:link action="revisarRol" controller="curso" id="${curso.id}">
								<g:message code="${curso}" /></g:link>
							</span>
						</li>
					</g:each>	
				</g:each>
			</ol>
		</div>
		
		 <g:render template='/login/ajaxLogin'/>
		
	</body>
</html>