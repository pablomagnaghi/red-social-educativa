<%@ page import="com.fiuba.MaterialCurso" %>

<div><g:hiddenField name="curso.id" value="${cursoId}"/></div>

<div><g:hiddenField name="fecha" value="${(new Date()).format("yyyy-MM-dd")}"/></div>

<div class="fieldcontain ${hasErrors(bean: materialCursoInstance, field: 'autor', 'error')} ">
	<label for="autor">
		<g:message code="materialCurso.autor.label" default="Autor" />
		
	</label>
	<g:textField name="autor" value="${materialCursoInstance?.autor}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: materialCursoInstance, field: 'categoria', 'error')} required">
	<label for="categoria">
		<g:message code="materialCurso.categoria.label" default="Categoria" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="categoria" name="categoria.id" from="${com.fiuba.CategoriaMaterial.list()}" optionKey="id" required="" value="${materialCursoInstance?.categoria?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: materialCursoInstance, field: 'descripcion', 'error')} ">
	<label for="descripcion">
		<g:message code="materialCurso.descripcion.label" default="Descripcion" />
		
	</label>
	<g:textField name="descripcion" value="${materialCursoInstance?.descripcion}"/>
</div>


