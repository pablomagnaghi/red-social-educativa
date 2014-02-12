<%@ page import="com.fiuba.NoticiaCurso" %>

<div class="fieldcontain ${hasErrors(bean: noticiaCursoInstance, field: 'visibilidad', 'error')} ">
	<label for="visibilidad">
		<g:message code="noticiaCurso.visibilidad.label" default="Visibilidad" />
		
	</label>
	<g:checkBox name="visibilidad" value="${noticiaCursoInstance?.visibilidad}" />
</div>

<div class="fieldcontain ${hasErrors(bean: noticiaCursoInstance, field: 'texto', 'error')} ">
	<label for="texto">
		<g:message code="noticiaCurso.texto.label" default="Texto" />
		
	</label>
	<g:textArea name="texto" value="${noticiaCursoInstance?.texto}"/>
</div>

<div><g:hiddenField name="fecha" value="${(new Date()).format(com.fiuba.Utilidades.FORMATO_FECHA)}"/></div>
<div><g:hiddenField name="hora" value="${(new Date()).getTimeString()}"/></div>
