<%@ page import="com.fiuba.MaterialGrupoActividad" %>

<div><g:hiddenField name="grupo.id" value="${grupoActividadId}"/></div>

<div><g:hiddenField name="fecha" value="${(new Date()).format("yyyy-MM-dd")}"/></div>

<div class="fieldcontain ${hasErrors(bean: materialGrupoActividadInstance, field: 'autor', 'error')} ">
	<label for="autor">
		<g:message code="materialGrupoActividad.autor.label" default="Autor" />
		
	</label>
	<g:textField name="autor" value="${materialGrupoActividadInstance?.autor}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: materialGrupoActividadInstance, field: 'categoria', 'error')} required">
	<label for="categoria">
		<g:message code="materialGrupoActividad.categoria.label" default="Categoria" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="categoria" name="categoria.id" from="${com.fiuba.CategoriaMaterial.list()}" optionKey="id" required="" value="${materialGrupoActividadInstance?.categoria?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: materialGrupoActividadInstance, field: 'descripcion', 'error')} ">
	<label for="descripcion">
		<g:message code="materialGrupoActividad.descripcion.label" default="Descripcion" />
		
	</label>
	<g:textField name="descripcion" value="${materialGrupoActividadInstance?.descripcion}"/>
</div>


