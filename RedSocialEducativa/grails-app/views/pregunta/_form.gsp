<%@ page import="com.encuesta.Pregunta" %>



<div class="fieldcontain ${hasErrors(bean: preguntaInstance, field: 'pregunta', 'error')} ">
	<label for="pregunta">
		<g:message code="pregunta.pregunta.label" default="Pregunta" />
		
	</label>
	<g:textField name="pregunta" maxlength="64" value="${preguntaInstance?.pregunta}"/>
</div>

