<%@ page import="com.fiuba.Contenido" %>



<div class="fieldcontain ${hasErrors(bean: contenidoInstance, field: 'materiales', 'error')} ">
	<label for="materiales">
		<g:message code="contenido.materiales.label" default="Materiales" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${contenidoInstance?.materiales?}" var="m">
    <li><g:link controller="materialContenido" action="show" id="${m.id}">${m?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="materialContenido" action="create" params="['contenido.id': contenidoInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'materialContenido.label', default: 'MaterialContenido')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: contenidoInstance, field: 'tema', 'error')} required">
	<label for="tema">
		<g:message code="contenido.tema.label" default="Tema" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="tema" name="tema.id" from="${com.fiuba.Tema.list()}" optionKey="id" required="" value="${contenidoInstance?.tema?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: contenidoInstance, field: 'titulo', 'error')} ">
	<label for="titulo">
		<g:message code="contenido.titulo.label" default="Titulo" />
		
	</label>
	<g:textField name="titulo" value="${contenidoInstance?.titulo}"/>
</div>

