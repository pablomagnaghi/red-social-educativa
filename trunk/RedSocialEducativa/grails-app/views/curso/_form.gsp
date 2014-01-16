<%@ page import="com.fiuba.Curso" %>

<div class="fieldcontain ${hasErrors(bean: cursoInstance, field: 'cuatDict', 'error')} ">
	<label for="cuatDict">
		<g:message code="curso.cuatDict.label" default="Cuat Dict" />
		
	</label>
	<g:textField name="cuatDict" value="${cursoInstance?.cuatDict}"/>
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
	
	<ul class="one-to-many">
	<g:each in="${cursoInstance?.mediadores?}" var="m">
	    <li><g:link controller="mediador" action="show" id="${m.id}">${m?.encodeAsHTML()}</g:link></li>
	</g:each>
	<li class="add">
	<g:link controller="mediador" action="create" params="['curso.id': cursoInstance?.id]">
		${message(code: 'default.add.label', args: [message(code: 'mediador.label', default: 'Mediador')])}</g:link>
	</li>
	</ul>
</div>

<div class="fieldcontain ${hasErrors(bean: cursoInstance, field: 'nroRelativo', 'error')} required">
	<label for="nroRelativo">
		<g:message code="curso.nroRelativo.label" default="Nro Relativo" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="nroRelativo" type="number" value="${cursoInstance.nroRelativo}" required=""/>
</div>

