<%@ page import="com.fiuba.GrupoCurso" %>

<div><g:hiddenField name="cuatrimestre.id" value="${cuatrimestreId}"/></div>

<div><g:hiddenField name="numero" value="${numGrupo}"/></div>

<div class="fieldcontain ${hasErrors(bean: grupoCursoInstance, field: 'nombre', 'error')} ">
	<label for="nombre">
		<g:message code="grupoCurso.nombre.label" default="Nombre" />
		
	</label>
	<g:textField name="nombre" value="${grupoCursoInstance?.nombre}"/>
</div>

