<%@ page import="com.fiuba.TemaActividad" %>

<div>
	<g:hiddenField name="actividad.id" value="${actividadId}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: temaActividadInstance, field: 'tema', 'error')} required">
	<label for="tema">
		<g:message code="temaActividad.tema.label" default="Tema" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="tema" name="tema.id" from="${com.fiuba.Tema.list()}" optionKey="id" required="" 
		value="${temaActividadInstance?.tema?.id}" class="many-to-one"/>
</div>

