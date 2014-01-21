<%@ page import="com.fiuba.Tema" %>



<div class="fieldcontain ${hasErrors(bean: temaInstance, field: 'contenidos', 'error')} ">
	<label for="contenidos">
		<g:message code="tema.contenidos.label" default="Contenidos" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${temaInstance?.contenidos?}" var="c">
    <li><g:link controller="contenido" action="show" id="${c.id}">${c?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="contenido" action="create" params="['tema.id': temaInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'contenido.label', default: 'Contenido')])}</g:link>
</li>
</ul>

</div>

<div> <g:hiddenField name="curso.id"  value="${cursoId}"/></div>

<div class="fieldcontain ${hasErrors(bean: temaInstance, field: 'materiales', 'error')} ">
	<label for="materiales">
		<g:message code="tema.materiales.label" default="Materiales" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${temaInstance?.materiales?}" var="m">
    <li><g:link controller="materialTema" action="show" id="${m.id}">${m?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="materialTema" action="create" params="['tema.id': temaInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'materialTema.label', default: 'MaterialTema')])}</g:link>
</li>
</ul>

</div>



