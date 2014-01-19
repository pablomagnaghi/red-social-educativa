<%@ page import="com.fiuba.NoticiaCurso" %>

<div> <g:hiddenField name="curso.id"  value="${cursoId}"/></div>

<div> <g:hiddenField name="mediador.id" value="${com.fiuba.Mediador.findByUsuario(usuario).id}"/></div>

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

<div>
	<g:hiddenField name="fecha" value="${(new Date()).getDateString()}"/>
</div>

<div>
	<g:hiddenField name="hora" value="${(new Date()).getTimeString()}"/>
</div>
