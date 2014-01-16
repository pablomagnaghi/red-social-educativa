<%@ page import="com.fiuba.NoticiaCurso" %>



<div class="fieldcontain ${hasErrors(bean: noticiaCursoInstance, field: 'curso', 'error')} required">
	<label for="curso">
		<g:message code="noticiaCurso.curso.label" default="Curso" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="curso" name="curso.id" from="${com.fiuba.Curso.list()}" optionKey="id" required="" value="${noticiaCursoInstance?.curso?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: noticiaCursoInstance, field: 'fecha', 'error')} required">
	<label for="fecha">
		<g:message code="noticiaCurso.fecha.label" default="Fecha" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="fecha" precision="day"  value="${noticiaCursoInstance?.fecha}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: noticiaCursoInstance, field: 'mediador', 'error')} required">
	<label for="mediador">
		<g:message code="noticiaCurso.mediador.label" default="Mediador" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="mediador" name="mediador.id" from="${com.fiuba.Mediador.list()}" optionKey="id" required="" value="${noticiaCursoInstance?.mediador?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: noticiaCursoInstance, field: 'texto', 'error')} ">
	<label for="texto">
		<g:message code="noticiaCurso.texto.label" default="Texto" />
		
	</label>
	<g:textField name="texto" value="${noticiaCursoInstance?.texto}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: noticiaCursoInstance, field: 'titulo', 'error')} ">
	<label for="titulo">
		<g:message code="noticiaCurso.titulo.label" default="Titulo" />
		
	</label>
	<g:textField name="titulo" value="${noticiaCursoInstance?.titulo}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: noticiaCursoInstance, field: 'visibilidad', 'error')} ">
	<label for="visibilidad">
		<g:message code="noticiaCurso.visibilidad.label" default="Visibilidad" />
		
	</label>
	<g:checkBox name="visibilidad" value="${noticiaCursoInstance?.visibilidad}" />
</div>

