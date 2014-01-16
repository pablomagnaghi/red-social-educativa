<%@ page import="com.fiuba.NoticiaRed" %>

<g:if test="${noticiaRedInstance?.administrador}">
	<div> <g:hiddenField name="administrador.id"  value="${noticiaRedInstance?.administrador.id}"/></div>				
</g:if>
<g:else>
	<div> <g:hiddenField name="administrador.id"  value="${com.fiuba.Administrador.findByUsuario(usuario).id}"/></div>

</g:else>			

<div class="fieldcontain ${hasErrors(bean: noticiaRedInstance, field: 'fecha', 'error')} required">
	<label for="fecha">
		<g:message code="noticiaRed.fecha.label" default="Fecha" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="fecha" precision="day"  value="${noticiaRedInstance?.fecha}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: noticiaRedInstance, field: 'texto', 'error')} ">
	<label for="texto">
		<g:message code="noticiaRed.texto.label" default="Texto" />
		
	</label>
	<g:textArea name="texto" value="${noticiaRedInstance?.texto}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: noticiaRedInstance, field: 'titulo', 'error')} ">
	<label for="titulo">
		<g:message code="noticiaRed.titulo.label" default="Titulo" />
		
	</label>
	<g:textField name="titulo" value="${noticiaRedInstance?.titulo}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: noticiaRedInstance, field: 'visibilidad', 'error')} ">
	<label for="visibilidad">
		<g:message code="noticiaRed.visibilidad.label" default="Visibilidad" />
		
	</label>
	<g:checkBox name="visibilidad" value="${noticiaRedInstance?.visibilidad}" />
</div>

