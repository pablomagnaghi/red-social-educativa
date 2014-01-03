<%@ page import="com.fiuba.Curso" %>



<div class="fieldcontain ${hasErrors(bean: cursoInstance, field: 'cuatDict', 'error')} ">
	<label for="cuatDict">
		<g:message code="curso.cuatDict.label" default="Cuat Dict" />
		
	</label>
	<g:textField name="cuatDict" value="${cursoInstance?.cuatDict}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: cursoInstance, field: 'cuatrimestres', 'error')} ">
	<label for="cuatrimestres">
		<g:message code="curso.cuatrimestres.label" default="Cuatrimestres" />
		
	</label>
	<g:select name="cuatrimestres" from="${com.fiuba.Cuatrimestre.list()}" multiple="multiple" optionKey="id" size="5" value="${cursoInstance?.cuatrimestres*.id}" class="many-to-many"/>
</div>

<div class="fieldcontain ${hasErrors(bean: cursoInstance, field: 'mediadores', 'error')} ">
	<label for="mediadores">
		<g:message code="curso.mediadores.label" default="Mediadores" />
		
	</label>
	<g:select name="mediadores" from="${com.fiuba.Mediador.list()}" multiple="multiple" optionKey="id" size="5" value="${cursoInstance?.mediadores*.id}" class="many-to-many"/>
</div>

<div class="fieldcontain ${hasErrors(bean: cursoInstance, field: 'nroRelativo', 'error')} required">
	<label for="nroRelativo">
		<g:message code="curso.nroRelativo.label" default="Nro Relativo" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="nroRelativo" type="number" value="${cursoInstance.nroRelativo}" required=""/>
</div>

