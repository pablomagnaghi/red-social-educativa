<%@ page import="com.fiuba.NoticiaRed" %>

<div><g:hiddenField name="administrador.id" value="${com.fiuba.Administrador.findByUsuario(usuario).id}"/></div>

<div class="fieldcontain ${hasErrors(bean: noticiaRedInstance, field: 'visibilidad', 'error')} ">
	<label for="visibilidad">
		<g:message code="noticiaRed.visibilidad.label" default="Visibilidad" />
	</label>
	<g:checkBox name="visibilidad" value="${noticiaRedInstance?.visibilidad}" />
</div>

<div class="fieldcontain ${hasErrors(bean: noticiaRedInstance, field: 'texto', 'error')} ">
	<label for="texto">
		<g:message code="noticiaRed.texto.label" default="Texto" />
	</label>
	<g:textArea name="texto" value="${noticiaRedInstance?.texto}"/>
</div>

<div><g:hiddenField name="fecha" value="${(new Date()).format("yyyy-mm-dd")}"/></div>

<div><g:hiddenField name="hora" value="${(new Date()).getTimeString()}"/></div>