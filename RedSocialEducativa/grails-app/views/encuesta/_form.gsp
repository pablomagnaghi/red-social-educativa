<%@ page import="com.encuesta.Encuesta" %>



<div class="fieldcontain ${hasErrors(bean: encuestaInstance, field: 'nombre', 'error')} ">
	<label for="nombre">
		<g:message code="encuesta.nombre.label" default="Nombre" />
		
	</label>
	<g:textField name="nombre" maxlength="64" value="${encuestaInstance?.nombre}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: encuestaInstance, field: 'curso', 'error')} required">
	<label for="curso">
		<g:message code="encuesta.curso.label" default="Curso" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="curso" name="curso.id" from="${com.cursado.Curso.list()}" optionKey="id" required="" value="${encuestaInstance?.curso?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: encuestaInstance, field: 'preguntas', 'error')} ">
	<label for="preguntas">
		<g:message code="encuesta.preguntas.label" default="Preguntas" />
		
	</label>
	<g:select name="preguntas" from="${com.encuesta.Pregunta.list()}" multiple="multiple" optionKey="id" size="5" value="${encuestaInstance?.preguntas*.id}" class="many-to-many"/>
</div>

<div class="fieldcontain ${hasErrors(bean: encuestaInstance, field: 'preguntasChoice', 'error')} ">
	<label for="preguntasChoice">
		<g:message code="encuesta.preguntasChoice.label" default="Preguntas Choice" />
		
	</label>
	<g:select name="preguntasChoice" from="${com.encuesta.PreguntaChoice.list()}" multiple="multiple" optionKey="id" size="5" value="${encuestaInstance?.preguntasChoice*.id}" class="many-to-many"/>
</div>

<div class="fieldcontain ${hasErrors(bean: encuestaInstance, field: 'preguntasPuntaje', 'error')} ">
	<label for="preguntasPuntaje">
		<g:message code="encuesta.preguntasPuntaje.label" default="Preguntas Puntaje" />
		
	</label>
	<g:select name="preguntasPuntaje" from="${com.encuesta.PreguntaPuntaje.list()}" multiple="multiple" optionKey="id" size="5" value="${encuestaInstance?.preguntasPuntaje*.id}" class="many-to-many"/>
</div>

