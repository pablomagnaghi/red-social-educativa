<%@ page import="com.fiuba.Cuatrimestre" %>



<div class="fieldcontain ${hasErrors(bean: cuatrimestreInstance, field: 'anio', 'error')} required">
	<label for="anio">
		<g:message code="cuatrimestre.anio.label" default="Anio" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="anio" type="number" value="${cuatrimestreInstance.anio}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: cuatrimestreInstance, field: 'aprendices', 'error')} ">
	<label for="aprendices">
		<g:message code="cuatrimestre.aprendices.label" default="Aprendices" />
		
	</label>
	<g:select name="aprendices" from="${com.fiuba.Aprendiz.list()}" multiple="multiple" optionKey="id" size="5" value="${cuatrimestreInstance?.aprendices*.id}" class="many-to-many"/>
</div>

<div class="fieldcontain ${hasErrors(bean: cuatrimestreInstance, field: 'curso', 'error')} required">
	<label for="curso">
		<g:message code="cuatrimestre.curso.label" default="Curso" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="curso" name="curso.id" from="${com.fiuba.Curso.list()}" optionKey="id" required="" value="${cuatrimestreInstance?.curso?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: cuatrimestreInstance, field: 'habGrupos', 'error')} ">
	<label for="habGrupos">
		<g:message code="cuatrimestre.habGrupos.label" default="Hab Grupos" />
		
	</label>
	<g:checkBox name="habGrupos" value="${cuatrimestreInstance?.habGrupos}" />
</div>

<div class="fieldcontain ${hasErrors(bean: cuatrimestreInstance, field: 'nroUltGrupo', 'error')} required">
	<label for="nroUltGrupo">
		<g:message code="cuatrimestre.nroUltGrupo.label" default="Nro Ult Grupo" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="nroUltGrupo" type="number" value="${cuatrimestreInstance.nroUltGrupo}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: cuatrimestreInstance, field: 'numero', 'error')} required">
	<label for="numero">
		<g:message code="cuatrimestre.numero.label" default="Numero" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="numero" type="number" value="${cuatrimestreInstance.numero}" required=""/>
</div>

