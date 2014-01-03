<%@ page import="com.fiuba.Red" %>
<!doctype html>
<html>
    <head>
        <meta name="layout" content="main">
        <g:set var="entityName" value="Red Social Login" />
        <title><g:message code="Red Social Login" args="[entityName]" /></title>
    </head>
    <body>
	    <div class="nav" role="navigation">
	    	<ul>
		       	<li><g:link class="list" action="solicitarMembresia">
		       		<g:message code="Solicitar Membresía" args="[entityName]" /></g:link></li>
		       	<li><g:link class="list" action="salir">
		       		<g:message code="Salir" args="[entityName]" /></g:link></li>
	       	</ul>
		</div>
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
			<g:form action="autenticacion" >
				<fieldset class="form">
					<div class="fieldcontain ${hasErrors(bean: miembroInstance, field: 'dni', 'error')} ">
						<label for="dni">
							<g:message code="miembro.dni.label" default="Dni" />   
						</label>
							<g:textField name="dni" value="${miembroInstance?.dni}"/>
					</div>
					<div class="fieldcontain ${hasErrors(bean: miembroInstance, field: 'password', 'error')} ">
						<label for="password">
							<g:message code="miembro.password.label" default="Password" /> 
						</label>
							<g:field type="password" name="password" value="${miembroInstance?.password}"/>
					</div>
					<div class="fieldcontain">
						<label for='remember_me'>Recordar contraseña</label>
						<input type='checkbox' class='chk' id='remember_me'/>
					</div>
				</fieldset>
	            <fieldset class="buttons">
					<g:submitButton name="login" class="save" value="Ingresar" />
				</fieldset>
			</g:form>
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
							<g:link action="index" controller="materia" id="${materia.id}">
							<g:message code="${materia}" /></g:link>
						</span>
					</li>
				</g:each>
			</ol>
		</div>
	</body>
</html>