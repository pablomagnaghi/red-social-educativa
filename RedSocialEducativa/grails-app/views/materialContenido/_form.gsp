<%@ page import="com.fiuba.MaterialContenido" %>



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

<div class="fieldcontain ${hasErrors(bean: materialContenidoInstance, field: 'contenido', 'error')} required">
	<label for="contenido">
		<g:message code="materialContenido.contenido.label" default="Contenido" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="contenido" name="contenido.id" from="${com.fiuba.Contenido.list()}" optionKey="id" required="" value="${materialContenidoInstance?.contenido?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: materialContenidoInstance, field: 'descripcion', 'error')} ">
	<label for="descripcion">
		<g:message code="materialContenido.descripcion.label" default="Descripcion" />
		
	</label>
	<g:textField name="descripcion" value="${materialContenidoInstance?.descripcion}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: materialContenidoInstance, field: 'fecha', 'error')} ">
	<label for="fecha">
		<g:message code="materialContenido.fecha.label" default="Fecha" />
		
	</label>
	<g:textField name="fecha" value="${materialContenidoInstance?.fecha}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: materialContenidoInstance, field: 'responsable', 'error')} ">
	<label for="responsable">
		<g:message code="materialContenido.responsable.label" default="Responsable" />
		
	</label>
	<g:textField name="responsable" value="${materialContenidoInstance?.responsable}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: materialContenidoInstance, field: 'titulo', 'error')} ">
	<label for="titulo">
		<g:message code="materialContenido.titulo.label" default="Titulo" />
		
	</label>
	<g:textField name="titulo" value="${materialContenidoInstance?.titulo}"/>
</div>

