<%@ page import="com.fiuba.Materia" %>



<div class="fieldcontain ${hasErrors(bean: materiaInstance, field: 'codigo', 'error')} ">
	<label for="codigo">
		<g:message code="materia.codigo.label" default="Codigo" />
		
	</label>
	<g:textField name="codigo" value="${materiaInstance?.codigo}"/>
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

<div class="fieldcontain ${hasErrors(bean: materiaInstance, field: 'cursos', 'error')} ">
	<label for="cursos">
		<g:message code="materia.cursos.label" default="Cursos" />
		
	</label>
	<g:select name="cursos" from="${com.fiuba.Curso.list()}" multiple="multiple" optionKey="id" size="5" value="${materiaInstance?.cursos*.id}" class="many-to-many"/>
</div>

<div class="fieldcontain ${hasErrors(bean: materiaInstance, field: 'nombre', 'error')} ">
	<label for="nombre">
		<g:message code="materia.nombre.label" default="Nombre" />
		
	</label>
	<g:textField name="nombre" value="${materiaInstance?.nombre}"/>
</div>

