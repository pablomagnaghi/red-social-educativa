<%@ page import="com.fiuba.EvaluacionAprendiz" %>

<div class="fieldcontain ${hasErrors(bean: evaluacionAprendizInstance, field: 'aprendiz', 'error')} required">
	<label for="aprendiz">
		<g:message code="evaluacionAprendiz.aprendiz.label" default="Aprendiz" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="aprendiz" name="aprendiz.id" from="${aprendices}" optionKey="id" required="" 
		value="${evaluacionAprendizInstance?.aprendiz?.id}" class="many-to-one"/>
</div>

<div><g:hiddenField name="evaluacion.id" value="${params.evaluacionId}"/></div>