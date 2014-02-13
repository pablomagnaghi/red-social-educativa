<%@ page import="com.fiuba.MaterialActividad" %>

<div class="fieldcontain ${hasErrors(bean: materialActividadInstance, field: 'autor', 'error')} ">
	<label for="autor">
		<g:message code="materialActividad.autor.label" default="Autor" />
		
	</label>
	<g:textField name="autor" value="${materialActividadInstance?.autor}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: materialActividadInstance, field: 'categoria', 'error')} required">
	<label for="categoria">
		<g:message code="materialActividad.categoria.label" default="Categoria" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="categoria" name="categoria.id" from="${com.fiuba.CategoriaMaterial.list()}" optionKey="id" required="" value="${materialActividadInstance?.categoria?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: materialActividadInstance, field: 'descripcion', 'error')} ">
	<label for="descripcion">
		<g:message code="materialActividad.descripcion.label" default="Descripcion" />
		
	</label>
	<g:textField name="descripcion" value="${materialActividadInstance?.descripcion}"/>
</div>

<div><g:hiddenField name="actividad.id" value="${params.actividadId}"/></div>
<div><g:hiddenField name="fecha" value="${(new Date()).format(com.fiuba.Utilidades.FORMATO_FECHA)}"/></div>
