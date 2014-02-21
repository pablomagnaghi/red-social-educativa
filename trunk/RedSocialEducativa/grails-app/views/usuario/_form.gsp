<%@ page import="com.fiuba.Usuario" %>

<div class="fieldcontain ${hasErrors(bean: usuarioInstance, field: 'username', 'error')} required">
	<label for="username">
		<g:message code="usuario.username.label" default="Username" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="username" required="" value="${usuarioInstance?.username}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: usuarioInstance, field: 'password', 'error')} required">
	<label for="password">
		<g:message code="usuario.password.label" default="Password" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="password" required="" value="${usuarioInstance?.password}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: usuarioInstance, field: 'apellido', 'error')} required">
	<label for="apellido">
		<g:message code="usuario.apellido.label" default="Apellido" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="apellido" required="" value="${usuarioInstance?.apellido}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: usuarioInstance, field: 'nombres', 'error')} required">
	<label for="nombres">
		<g:message code="usuario.nombres.label" default="Nombres" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="nombres" required="" value="${usuarioInstance?.nombres}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: usuarioInstance, field: 'legajo', 'error')} ">
	<label for="legajo">
		<g:message code="usuario.legajo.label" default="Legajo" />
		
	</label>
	<g:field name="legajo" type="number" value="${usuarioInstance.legajo}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: usuarioInstance, field: 'padron', 'error')} ">
	<label for="padron">
		<g:message code="usuario.padron.label" default="Padron" />
		
	</label>
	<g:field name="padron" type="number" value="${usuarioInstance.padron}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: usuarioInstance, field: 'email', 'error')} ">
	<label for="email">
		<g:message code="usuario.email.label" default="Email" />
		
	</label>
	<g:field type="email" name="email" value="${usuarioInstance?.email}"/>
</div>

<div><g:hiddenField name="fechaSolicitud" value="${(new Date()).format(com.fiuba.Utilidades.FORMATO_FECHA)}"/></div>

