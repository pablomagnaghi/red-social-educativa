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
			<g:hasErrors bean="${usuarioInstance}">
				<ul class="errors" role="alert">
					<g:eachError bean="${usuarioInstance}" var="error">
						<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>>
							<g:message error="${error}"/>
		                </li>
					</g:eachError>
				</ul>
			</g:hasErrors>
			<g:if test="${usuario}">
				Hola ${usuario}, 
				<g:link class="list" action="salir">
		       		<g:message code="Salir" args="[entityName]" /></g:link>
			</g:if>
			<g:else>
				<div class="nav" role="navigation">
	    			<ul>
		       			<li><g:link class="list" action="solicitarMembresia">
		       			<g:message code="Solicitar Membresía" args="[entityName]" /></g:link></li>
	       			</ul>
				</div>
				<g:form action="autenticacion" >
					<fieldset class="form">
						<div class="fieldcontain ${hasErrors(bean: usuarioInstance, field: 'dni', 'error')} ">
							<label for="dni">
								<g:message code="usuario.dni.label" default="Dni" />   
							</label>
								<g:textField name="dni" value="${usuarioInstance?.dni}"/>
						</div>
						<div class="fieldcontain ${hasErrors(bean: usuarioInstance, field: 'password', 'error')} ">
							<label for="password">
								<g:message code="usuario.password.label" default="Password" /> 
							</label>
								<g:field type="password" name="password" value="${usuarioInstance?.password}"/>
						</div>
						<div class="fieldcontain">
							<label for='remember_me'>Recordar contraseña</label>
							<input type='checkbox' class='chk' id='remember_me' name='remember_me'/>
						</div>
					</fieldset>
		            <fieldset class="buttons">
						<g:submitButton name="login" class="save" value="Ingresar" />
					</fieldset>
				</g:form>
			</g:else>
		</div>
		<div>
			<g:if test="${administrador}">
				<h2>Bienvenido administrador ${administrador}</h2> 
				<span class = "menuButton">
					<g:link action="index" controller="administrador">
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
					<h2>Mis cursos como aprendizr</h2> 
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
			<h2>Sector destinado a vistas de materias</h2>
			<br>
			<ol>
				<g:each in="${materias}" var="materia">
					<li>
						<span class = "menuButton">
							<g:link action="general" controller="materia" id="${materia.id}">
							<g:message code="${materia}" /></g:link>
						</span>
					</li>
				</g:each>
			</ol>
		</div>
	</body>
</html>