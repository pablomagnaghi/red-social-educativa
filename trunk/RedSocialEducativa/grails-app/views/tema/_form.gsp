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

<div class="fieldcontain ${hasErrors(bean: temaInstance, field: 'curso', 'error')} required">
	<label for="curso">
		<g:message code="tema.curso.label" default="Curso" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="curso" name="curso.id" from="${com.fiuba.Curso.list()}" optionKey="id" required="" value="${temaInstance?.curso?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: temaInstance, field: 'foro', 'error')} required">
	<label for="foro">
		<g:message code="tema.foro.label" default="Foro" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="foro" name="foro.id" from="${com.fiuba.ForoTema.list()}" optionKey="id" required="" value="${temaInstance?.foro?.id}" class="many-to-one"/>
</div>

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

<div class="fieldcontain ${hasErrors(bean: temaInstance, field: 'titulo', 'error')} ">
	<label for="titulo">
		<g:message code="tema.titulo.label" default="Titulo" />
		
	</label>
	<g:textField name="titulo" value="${temaInstance?.titulo}"/>
</div>
