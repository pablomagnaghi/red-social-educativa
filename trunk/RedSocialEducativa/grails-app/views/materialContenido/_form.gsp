<%@ page import="com.fiuba.MaterialContenido" %>

<div>
	<g:hiddenField name="contenido.id" value="${contenidoId}"/>
</div>

<div>
	<g:hiddenField name="fecha" value="${(new Date()).format("yyyy-mm-dd")}"/>
</div>


<div class="fieldcontain ${hasErrors(bean: materialContenidoInstance, field: 'autor', 'error')} ">
	<label for="autor">
		<g:message code="materialContenido.autor.label" default="Autor" />
		
	</label>
	<g:textField name="autor" value="${materialContenidoInstance?.autor}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: materialContenidoInstance, field: 'categoria', 'error')} required">
	<label for="categoria">
		<g:message code="materialContenido.categoria.label" default="Categoria" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="categoria" name="categoria.id" from="${com.fiuba.Categoria.list()}" optionKey="id" required="" value="${materialContenidoInstance?.categoria?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: materialContenidoInstance, field: 'descripcion', 'error')} ">
	<label for="descripcion">
		<g:message code="materialContenido.descripcion.label" default="Descripcion" />
		
	</label>
	<g:textField name="descripcion" value="${materialContenidoInstance?.descripcion}"/>
</div>



