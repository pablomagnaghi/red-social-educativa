<%@ page import="com.encuesta.PreguntaPuntaje" %>



<div class="fieldcontain ${hasErrors(bean: preguntaPuntajeInstance, field: 'pregunta', 'error')} ">
	<label for="pregunta">
		<g:message code="preguntaPuntaje.pregunta.label" default="Pregunta" />
		
	</label>
	<g:textField name="pregunta" maxlength="64" value="${preguntaPuntajeInstance?.pregunta}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: preguntaPuntajeInstance, field: 'numerico', 'error')} ">
	<label for="numerico">
		<g:message code="preguntaPuntaje.numerico.label" default="Numerico" />
		
	</label>
	<g:checkBox name="numerico" value="${preguntaPuntajeInstance?.numerico}" />
</div>

