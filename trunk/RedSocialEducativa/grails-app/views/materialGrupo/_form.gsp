<%@ page import="com.fiuba.MaterialGrupo" %>

<div><g:hiddenField name="grupo.id" value="${grupoId}"/></div>

<div><g:hiddenField name="fecha" value="${(new Date()).format("yyyy-MM-dd")}"/></div>

<div class="fieldcontain ${hasErrors(bean: materialGrupoInstance, field: 'autor', 'error')} ">
	<label for="autor">
		<g:message code="materialGrupo.autor.label" default="Autor" />
		
	</label>
	<g:textField name="autor" value="${materialGrupoInstance?.autor}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: materialGrupoInstance, field: 'categoria', 'error')} required">
	<label for="categoria">
		<g:message code="materialGrupo.categoria.label" default="Categoria" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="categoria" name="categoria.id" from="${com.fiuba.CategoriaActividad.list()}" optionKey="id" required="" value="${materialGrupoInstance?.categoria?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: materialGrupoInstance, field: 'descripcion', 'error')} ">
	<label for="descripcion">
		<g:message code="materialGrupo.descripcion.label" default="Descripcion" />
		
	</label>
	<g:textField name="descripcion" value="${materialGrupoInstance?.descripcion}"/>
</div>



