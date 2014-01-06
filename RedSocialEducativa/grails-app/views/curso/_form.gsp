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
	
<ul class="one-to-many">
<g:each in="${cursoInstance?.cuatrimestres?}" var="c">
    <li><g:link controller="cuatrimestre" action="show" id="${c.id}">${c?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="cuatrimestre" action="create" params="['curso.id': cursoInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'cuatrimestre.label', default: 'Cuatrimestre')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: cursoInstance, field: 'materia', 'error')} required">
	<label for="materia">
		<g:message code="curso.materia.label" default="Materia" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="materia" name="materia.id" from="${com.fiuba.Materia.list()}" optionKey="id" required="" value="${cursoInstance?.materia?.id}" class="many-to-one"/>
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

