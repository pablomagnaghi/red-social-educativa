<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="layout" content="main" />
<!--<g:javascript src="formulario.js"/>-->
<title>Insert title here</title>
</head>
<body>
	<div class="nav" role="navigation">
		<g:link class="list" action="principal">
		<g:message code="Volver"/></g:link>
	</div>
	<div class="body">
	   <g:if test="${flash.message}">
	   		<div class="message" role="status">${flash.message}</div>
		</g:if>
		<g:hasErrors bean="${usuarioInstance}">
			<ul class="errors" role="alert">
				<g:eachError bean="${usuarioInstance}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
		</g:hasErrors>
		<g:form controller="red" action="revisarDatosUsuario">
			<div class="fieldcontain ${hasErrors(bean: usuarioInstance, field: 'username', 'error')} required">
				<label for="username"> 
					<g:message code="usuarioInstance.username.label" default="Dni" /> 
					<span class="required-indicator">*</span>
				</label>
				<g:textField name="username" type="number" value="${usuarioInstance?.username}" required=""/>
			</div>
		
			<div class="fieldcontain ${hasErrors(bean: usuarioInstance, field: 'apellido', 'error')} required">
				<label for="apellido">
					<g:message code="usuarioInstance.apellido.label" default="Apellido" />
					<span class="required-indicator">*</span>
				</label>
				<g:textField name="apellido" required="" value="${usuarioInstance?.apellido}"/>
			</div>
			
			<div class="fieldcontain ${hasErrors(bean: usuarioInstance, field: 'nombres', 'error')} required">
				<label for="nombres">
					<g:message code="usuarioInstance.nombres.label" default="Nombres" />
					<span class="required-indicator">*</span>
				</label>
				<g:textField name="nombres" required="" value="${miembroInstance?.nombres}"/>
			</div>

			<div class="fieldcontain ${hasErrors(bean: usuarioInstance, field: 'legajo', 'error')} required">
				<label for="legajo">
					<g:message code="usuarioInstance.legajo.label" default="Legajo" />
					<span class="required-indicator">*</span>
				</label>
				<g:textField name="legajo" type="number" value="${usuarioInstance?.legajo}" required=""/>
			</div>
			
			<div class="fieldcontain ${hasErrors(bean: usuarioInstance, field: 'padron', 'error')} required">
				<label for="padron">
					<g:message code="usuarioInstance.padron.label" default="Padron" />
					<span class="required-indicator">*</span>
				</label>
				<g:textField name="padron" type="number" value="${usuarioInstance?.padron}" required=""/>
			</div>
			
			<div
				class="fieldcontain ${hasErrors(bean: usuarioInstance, field: 'email', 'error')} required">
				<label for="email"> 
					<g:message code="usuarioInstance.email.label" default="Email" /> 
					<span class="required-indicator">*</span>
				</label>
				<g:textField name="email" required="" value="${usuarioInstance?.email}" />
			</div>
			
			<div class="fieldcontain ${hasErrors(bean: usuarioInstance, field: 'password', 'error')} ">
				<label for="password">
					<g:message code="usuarioInstance.password.label" default="Password" />
				</label>
				<g:passwordField name="password" maxlength="12" required="" value="${usuarioInstance?.password}"/>
			</div>	
	
			<div class="fieldcontain ${hasErrors(bean: usuarioInstance, field: 'password', 'error')} ">
				<label for="passwordConfirmado"> 
					<g:message code="usuarioInstance.passwordConfirmado.label" default="Confirmar Password" />
				</label>
				<g:passwordField name="passwordConfirmado" required="" />
			</div>

			<fieldset class="buttons">
				<g:submitButton name="enviarDatos" class ="save" value="Enviar datos"/>	
			</fieldset>
		</g:form>
	</div>
</body>
</html>
