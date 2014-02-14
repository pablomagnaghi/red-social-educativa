<%@ page import="com.fiuba.Actividad" %>

<div class="fieldcontain ${hasErrors(bean: actividadInstance, field: 'categoria', 'error')} required">
	<label for="categoria">
		<g:message code="actividad.categoria.label" default="Categoria" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="categoria" name="categoria.id" from="${com.fiuba.CategoriaActividad.list()}" optionKey="id" required="" value="${actividadInstance?.categoria?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: actividadInstance, field: 'objetivo', 'error')} ">
	<label for="objetivo">
		<g:message code="actividad.objetivo.label" default="Objetivo" />
		
	</label>
	<g:textField name="objetivo" value="${actividadInstance?.objetivo}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: actividadInstance, field: 'fechaFinalizacion', 'error')} ">
	<label for="fechaFinalizacion">
		<g:message code="actividad.fechaFinalizacion.label" default="Fecha Finalizacion" />
		
	</label>
	<g:datePicker name="fechaFinalizacionDate" precision="day"  value="${new Date()}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: actividadInstance, field: 'evaluable', 'error')} ">
	<label for="evaluable">
		<g:message code="actividad.evaluable.label" default="Evaluable" />
		
	</label>
	<g:checkBox name="evaluable" value="${actividadInstance?.evaluable}" />
</div>

<div class="fieldcontain ${hasErrors(bean: actividadInstance, field: 'grupal', 'error')} ">
	<label for="grupal">
		<g:message code="actividad.grupal.label" default="Grupal" />
		
	</label>
	<g:checkBox name="grupal" value="${actividadInstance?.grupal}" />
</div>

<div class="fieldcontain ${hasErrors(bean: actividadInstance, field: 'visibilidad', 'error')} ">
	<label for="visibilidad">
		<g:message code="actividad.visibilidad.label" default="Visibilidad" />
		
	</label>
	<g:checkBox name="visibilidad" value="${actividadInstance?.visibilidad}" />
</div>

<div><g:hiddenField name="cuatrimestre.id" value="${params.cuatrimestreId}"/></div>


