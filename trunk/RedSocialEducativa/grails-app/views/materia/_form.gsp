<%@ page import="com.fiuba.Materia" %>

<div class="fieldcontain ${hasErrors(bean: materiaInstance, field: 'codigo', 'error')} ">
	<label for="codigo">
		<g:message code="materia.codigo.label" default="Codigo" />
		
	</label>
	<g:textField name="codigo" value="${materiaInstance?.codigo}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: materiaInstance, field: 'nombre', 'error')} ">
	<label for="nombre">
		<g:message code="materia.nombre.label" default="Nombre" />
		
	</label>
	<g:textField name="nombre" value="${materiaInstance?.nombre}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: materiaInstance, field: 'contenidosMinimos', 'error')} ">
	<label for="contenidosMinimos">
		<g:message code="materia.contenidosMinimos.label" default="Contenidos Minimos" />
		
	</label>
	<g:textField name="contenidosMinimos" value="${materiaInstance?.contenidosMinimos}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: materiaInstance, field: 'creditos', 'error')} required">
	<label for="creditos">
		<g:message code="materia.creditos.label" default="Creditos" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="creditos" type="number" value="${materiaInstance.creditos}" required=""/>
</div>
