<%@ page import="com.encuesta.PreguntaDesarrollo" %>



<div class="fieldcontain ${hasErrors(bean: preguntaDesarrolloInstance, field: 'pregunta', 'error')} ">
	<label for="pregunta">
		<g:message code="preguntaDesarrollo.pregunta.label" default="Pregunta" />
		
	</label>
	<g:textField name="pregunta" maxlength="64" value="${preguntaDesarrolloInstance?.pregunta}"/>
</div>

