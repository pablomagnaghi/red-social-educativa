<%@ page import="com.fiuba.Curso" %>

<div class="fieldcontain ${hasErrors(bean: cursoInstance, field: 'cuatDict', 'error')} ">
	<label for="cuatDict">
		<g:message code="curso.cuatDict.label" default="Cuat Dict" />
		
	</label>
	<g:textField name="cuatDict" value="${cursoInstance?.cuatDict}"/>
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

<div class="fieldcontain ${hasErrors(bean: cursoInstance, field: 'nombre', 'error')} ">
	<label for="nombre">
		<g:message code="curso.nombre.label" default="Nombre" />

	</label>
	<g:textField name="nombre" value="${cursoInstance?.nombre}" />
</div>














