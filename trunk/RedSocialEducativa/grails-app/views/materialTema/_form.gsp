<%@ page import="com.fiuba.MaterialTema" %>

<div>
	<g:hiddenField name="tema.id" value="${temaId}"/>
</div>

<div>
	<g:hiddenField name="fecha" value="${(new Date()).format("yyyy-mm-dd")}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: materialTemaInstance, field: 'autor', 'error')} ">
	<label for="autor">
		<g:message code="materialTema.autor.label" default="Autor" />
		
	</label>
	<g:textField name="autor" value="${materialTemaInstance?.autor}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: materialTemaInstance, field: 'categoria', 'error')} required">
	<label for="categoria">
		<g:message code="materialTema.categoria.label" default="Categoria" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="categoria" name="categoria.id" from="${com.fiuba.Categoria.list()}" optionKey="id" required="" value="${materialTemaInstance?.categoria?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: materialTemaInstance, field: 'descripcion', 'error')} ">
	<label for="descripcion">
		<g:message code="materialTema.descripcion.label" default="Descripcion" />
		
	</label>
	<g:textField name="descripcion" value="${materialTemaInstance?.descripcion}"/>
</div>





