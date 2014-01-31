<%@ page import="com.fiuba.MaterialGrupo" %>



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
	<g:select id="categoria" name="categoria.id" from="${com.fiuba.CategoriaMaterial.list()}" optionKey="id" required="" value="${materialGrupoInstance?.categoria?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: materialGrupoInstance, field: 'descripcion', 'error')} ">
	<label for="descripcion">
		<g:message code="materialGrupo.descripcion.label" default="Descripcion" />
		
	</label>
	<g:textField name="descripcion" value="${materialGrupoInstance?.descripcion}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: materialGrupoInstance, field: 'fecha', 'error')} ">
	<label for="fecha">
		<g:message code="materialGrupo.fecha.label" default="Fecha" />
		
	</label>
	<g:textField name="fecha" value="${materialGrupoInstance?.fecha}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: materialGrupoInstance, field: 'grupo', 'error')} required">
	<label for="grupo">
		<g:message code="materialGrupo.grupo.label" default="Grupo" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="grupo" name="grupo.id" from="${com.fiuba.GrupoCurso.list()}" optionKey="id" required="" value="${materialGrupoInstance?.grupo?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: materialGrupoInstance, field: 'responsable', 'error')} ">
	<label for="responsable">
		<g:message code="materialGrupo.responsable.label" default="Responsable" />
		
	</label>
	<g:textField name="responsable" value="${materialGrupoInstance?.responsable}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: materialGrupoInstance, field: 'titulo', 'error')} ">
	<label for="titulo">
		<g:message code="materialGrupo.titulo.label" default="Titulo" />
		
	</label>
	<g:textField name="titulo" value="${materialGrupoInstance?.titulo}"/>
</div>

