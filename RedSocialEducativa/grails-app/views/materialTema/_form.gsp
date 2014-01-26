<%@ page import="com.fiuba.MaterialTema" %>



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

<div class="fieldcontain ${hasErrors(bean: materialTemaInstance, field: 'fecha', 'error')} ">
	<label for="fecha">
		<g:message code="materialTema.fecha.label" default="Fecha" />
		
	</label>
	<g:textField name="fecha" value="${materialTemaInstance?.fecha}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: materialTemaInstance, field: 'responsable', 'error')} ">
	<label for="responsable">
		<g:message code="materialTema.responsable.label" default="Responsable" />
		
	</label>
	<g:textField name="responsable" value="${materialTemaInstance?.responsable}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: materialTemaInstance, field: 'tema', 'error')} required">
	<label for="tema">
		<g:message code="materialTema.tema.label" default="Tema" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="tema" name="tema.id" from="${com.fiuba.Tema.list()}" optionKey="id" required="" value="${materialTemaInstance?.tema?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: materialTemaInstance, field: 'titulo', 'error')} ">
	<label for="titulo">
		<g:message code="materialTema.titulo.label" default="Titulo" />
		
	</label>
	<g:textField name="titulo" value="${materialTemaInstance?.titulo}"/>
</div>

