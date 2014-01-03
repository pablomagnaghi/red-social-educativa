<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="layout" content="main" />
<g:javascript src="formulario.js"/>
<title>Insert title here</title>
</head>
<body>
	<div class="nav" role="navigation">
		<g:link class="list" action="index">
		<g:message code="Volver"/></g:link>
	</div>
	<div class="body">
	   <g:if test="${flash.message}">
	   		<div class="message" role="status">${flash.message}</div>
		</g:if>
		<g:form action="revisarDatosMembresia" onSubmit="return validarPasswords();">
			<div class="fieldcontain ${hasErrors(bean: membresiaInstance, field: 'dni', 'error')} required">
				<label for="dni"> 
					<g:message code="membresia.dni.label" default="Dni" /> 
					<span class="required-indicator">*</span>
				</label>
				<g:textField name="dni" type="number" value="${membresiaInstance?.dni}" required=""/>
			</div>
		
			<div class="fieldcontain ${hasErrors(bean: membresiaInstance, field: 'apellido', 'error')} required">
				<label for="apellido">
					<g:message code="membresia.apellido.label" default="Apellido" />
					<span class="required-indicator">*</span>
				</label>
				<g:textField name="apellido" required="" value="${membresiaInstance?.apellido}"/>
			</div>
			
			<div class="fieldcontain ${hasErrors(bean: membresiaInstance, field: 'nombres', 'error')} required">
				<label for="nombres">
					<g:message code="membresia.nombres.label" default="Nombres" />
					<span class="required-indicator">*</span>
				</label>
				<g:textField name="nombres" required="" value="${miembroInstance?.nombres}"/>
			</div>

			<div class="fieldcontain ${hasErrors(bean: membresiaInstance, field: 'legajo', 'error')} required">
				<label for="legajo">
					<g:message code="membresia.legajo.label" default="Legajo" />
					<span class="required-indicator">*</span>
				</label>
				<g:textField name="legajo" type="number" value="${membresiaInstance?.legajo}" required=""/>
			</div>
			
			<div class="fieldcontain ${hasErrors(bean: membresiaInstance, field: 'padron', 'error')} required">
				<label for="padron">
					<g:message code="membresia.padron.label" default="Padron" />
					<span class="required-indicator">*</span>
				</label>
				<g:textField name="padron" type="number" value="${membresiaInstance?.padron}" required=""/>
			</div>
			
			<div
				class="fieldcontain ${hasErrors(bean: membresiaInstance, field: 'email', 'error')} required">
				<label for="email"> 
					<g:message code="membresia.email.label" default="Email" /> 
					<span class="required-indicator">*</span>
				</label>
				<g:textField name="email" required=""
					value="${membresiaInstance?.email}" />
			</div>
			
			<div class="fieldcontain ${hasErrors(bean: membresiaInstance, field: 'password', 'error')} ">
				<label for="password">
					<g:message code="membresia.password.label" default="Password" />
					
				</label>
				<g:passwordField name="password" maxlength="12" required="" id="pass"  value="${membresiaInstance?.password}"/>
			</div>	
	
			<div class="fieldcontain ${hasErrors(bean: membresiaInstance, field: 'password', 'error')} ">
				<label for="direcciones"> 
					<g:message code="membresia.direcciones.label" default="Verificar Password" />
				</label>
				<g:passwordField name="password" id="reTypePass" required="" />
			</div>

			<fieldset class="buttons">
				<g:submitButton name="enviarDatos" class ="save" value="Enviar datos"/>	
			</fieldset>
		</g:form>
	</div>
</body>
</html>
