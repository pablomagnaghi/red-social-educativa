<%@ page import="com.fiuba.GrupoActividadAprendiz" %>



<div class="fieldcontain ${hasErrors(bean: grupoActividadAprendizInstance, field: 'nota', 'error')} ">
	<label for="nota">
		<g:message code="grupoActividadAprendiz.nota.label" default="Nota" />
		
	</label>
	<g:field name="nota" type="number" min="1" value="${grupoActividadAprendizInstance.nota}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: grupoActividadAprendizInstance, field: 'aprendiz', 'error')} required">
	<label for="aprendiz">
		<g:message code="grupoActividadAprendiz.aprendiz.label" default="Aprendiz" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="aprendiz" name="aprendiz.id" from="${com.fiuba.Aprendiz.list()}" optionKey="id" required="" value="${grupoActividadAprendizInstance?.aprendiz?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: grupoActividadAprendizInstance, field: 'grupo', 'error')} required">
	<label for="grupo">
		<g:message code="grupoActividadAprendiz.grupo.label" default="Grupo" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="grupo" name="grupo.id" from="${com.fiuba.GrupoActividad.list()}" optionKey="id" required="" value="${grupoActividadAprendizInstance?.grupo?.id}" class="many-to-one"/>
</div>

