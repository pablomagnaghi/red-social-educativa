<%@ page import="com.fiuba.GrupoActividad" %>

<div class="fieldcontain ${hasErrors(bean: grupoActividadInstance, field: 'nombre', 'error')} ">
	<label for="nombre">
		<g:message code="grupoActividad.nombre.label" default="Nombre" />
		
	</label>
	<g:textField name="nombre" value="${grupoActividadInstance?.nombre}"/>
</div>

<div><g:hiddenField name="actividad.id" value="${params.actividadId}"/></div>
<div><g:hiddenField name="numero" value="${numGrupo}"/></div>


