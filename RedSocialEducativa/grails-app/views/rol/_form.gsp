<%@ page import="com.fiuba.Rol" %>



<div class="fieldcontain ${hasErrors(bean: rolInstance, field: 'nombre', 'error')} required">
	<label for="nombre">
		<g:message code="rol.nombre.label" default="Nombre" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="nombre" required="" value="${rolInstance?.nombre}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: rolInstance, field: 'acciones', 'error')} ">
	<label for="acciones">
		<g:message code="rol.acciones.label" default="Acciones" />
		
	</label>
	<g:select name="acciones" from="${com.fiuba.Accion.list()}" multiple="multiple" optionKey="id" size="5" value="${rolInstance?.acciones*.id}" class="many-to-many"/>
</div>

