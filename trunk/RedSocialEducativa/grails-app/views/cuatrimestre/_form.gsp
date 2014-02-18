<%@ page import="com.fiuba.Cuatrimestre" %>

<div class="fieldcontain ${hasErrors(bean: cuatrimestreInstance, field: 'habGrupos', 'error')} ">
	<label for="habGrupos">
		<g:message code="cuatrimestre.habGrupos.label" default="Hab Grupos" />
		
	</label>
	<g:checkBox name="habGrupos" value="${cuatrimestreInstance?.habGrupos}" />
</div>

<div><g:hiddenField name="curso.id" value="${params.cursoId}"/>
</div><div><g:hiddenField name="anio" value="${anio}"/></div>
<div><g:hiddenField name="numero" value="${numero}"/></div>
<div><g:hiddenField name="nroUltGrupo" value="0"/></div>	
