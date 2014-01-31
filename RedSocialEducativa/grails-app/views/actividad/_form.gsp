<%@ page import="com.fiuba.Actividad" %>

<div><g:hiddenField name="curso.id" value="${cursoId}"/></div>

<div class="fieldcontain ${hasErrors(bean: actividadInstance, field: 'categoria', 'error')} required">
	<label for="categoria">
		<g:message code="actividad.categoria.label" default="Categoria" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="categoria" name="categoria.id" from="${com.fiuba.CategoriaActividad.list()}" optionKey="id" required="" value="${actividadInstance?.categoria?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: actividadInstance, field: 'evaluable', 'error')} ">
	<label for="evaluable">
		<g:message code="actividad.evaluable.label" default="Evaluable" />
		
	</label>
	<g:checkBox name="evaluable" value="${actividadInstance?.evaluable}" />
</div>

<div class="fieldcontain ${hasErrors(bean: actividadInstance, field: 'fechaFinalizacion', 'error')} ">
	<label for="fechaFinalizacion">
		<g:message code="actividad.fechaFinalizacion.label" default="Fecha Finalizacion" />
		
	</label>
	<g:textField name="fechaFinalizacion" value="${actividadInstance?.fechaFinalizacion}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: actividadInstance, field: 'grupal', 'error')} ">
	<label for="grupal">
		<g:message code="actividad.grupal.label" default="Grupal" />
		
	</label>
	<g:checkBox name="grupal" value="${actividadInstance?.grupal}" />
</div>

<div class="fieldcontain ${hasErrors(bean: actividadInstance, field: 'grupos', 'error')} ">
	<label for="grupos">
		<g:message code="actividad.grupos.label" default="Grupos" />
		
	</label>
	<g:select name="grupos" from="${com.fiuba.GrupoActividad.list()}" multiple="multiple" optionKey="id" size="5" value="${actividadInstance?.grupos*.id}" class="many-to-many"/>
</div>

<div class="fieldcontain ${hasErrors(bean: actividadInstance, field: 'material', 'error')} ">
	<label for="material">
		<g:message code="actividad.material.label" default="Material" />
		
	</label>
	<g:select name="material" from="${com.fiuba.MaterialActividad.list()}" multiple="multiple" optionKey="id" size="5" value="${actividadInstance?.material*.id}" class="many-to-many"/>
</div>

<div class="fieldcontain ${hasErrors(bean: actividadInstance, field: 'objetivo', 'error')} ">
	<label for="objetivo">
		<g:message code="actividad.objetivo.label" default="Objetivo" />
		
	</label>
	<g:textField name="objetivo" value="${actividadInstance?.objetivo}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: actividadInstance, field: 'titulo', 'error')} ">
	<label for="titulo">
		<g:message code="actividad.titulo.label" default="Titulo" />
		
	</label>
	<g:textField name="titulo" value="${actividadInstance?.titulo}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: actividadInstance, field: 'visibilidad', 'error')} ">
	<label for="visibilidad">
		<g:message code="actividad.visibilidad.label" default="Visibilidad" />
		
	</label>
	<g:checkBox name="visibilidad" value="${actividadInstance?.visibilidad}" />
</div>

