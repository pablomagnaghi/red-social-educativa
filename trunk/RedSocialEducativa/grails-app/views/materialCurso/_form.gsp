<%@ page import="com.fiuba.MaterialCurso" %>



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
	<g:select id="categoria" name="categoria.id" from="${com.fiuba.Categoria.list()}" optionKey="id" required="" value="${materialCursoInstance?.categoria?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: materialCursoInstance, field: 'curso', 'error')} required">
	<label for="curso">
		<g:message code="materialCurso.curso.label" default="Curso" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="curso" name="curso.id" from="${com.fiuba.Curso.list()}" optionKey="id" required="" value="${materialCursoInstance?.curso?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: materialCursoInstance, field: 'descripcion', 'error')} ">
	<label for="descripcion">
		<g:message code="materialCurso.descripcion.label" default="Descripcion" />
		
	</label>
	<g:textField name="descripcion" value="${materialCursoInstance?.descripcion}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: materialCursoInstance, field: 'fecha', 'error')} ">
	<label for="fecha">
		<g:message code="materialCurso.fecha.label" default="Fecha" />
		
	</label>
	<g:textField name="fecha" value="${materialCursoInstance?.fecha}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: materialCursoInstance, field: 'responsable', 'error')} ">
	<label for="responsable">
		<g:message code="materialCurso.responsable.label" default="Responsable" />
		
	</label>
	<g:textField name="responsable" value="${materialCursoInstance?.responsable}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: materialCursoInstance, field: 'titulo', 'error')} ">
	<label for="titulo">
		<g:message code="materialCurso.titulo.label" default="Titulo" />
		
	</label>
	<g:textField name="titulo" value="${materialCursoInstance?.titulo}"/>
</div>

