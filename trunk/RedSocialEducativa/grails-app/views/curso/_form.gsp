<%@ page import="com.fiuba.Curso" %>

<div class="fieldcontain ${hasErrors(bean: cursoInstance, field: 'nombre', 'error')} ">
	<label for="nombre">
		<g:message code="curso.nombre.label" default="Nombre" />

	</label>
	<g:textField name="nombre" value="${cursoInstance?.nombre}" />
</div>

<div class="fieldcontain ${hasErrors(bean: cursoInstance, field: 'cuatDict', 'error')} ">
	<label for="cuatDict">
		<g:message code="curso.cuatDict.label" default="Cuat Dict" />
		
	</label>
	<g:select name="cuatDict" from="${['1', '2', '1|2']}" />
</div>
