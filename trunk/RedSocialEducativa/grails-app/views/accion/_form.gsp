<%@ page import="com.fiuba.Accion" %>



<div class="fieldcontain ${hasErrors(bean: accionInstance, field: 'nombre', 'error')} required">
	<label for="nombre">
		<g:message code="accion.nombre.label" default="Nombre" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="nombre" required="" value="${accionInstance?.nombre}"/>
</div>

