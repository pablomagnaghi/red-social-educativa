<%@ page import="com.fiuba.ForoGeneral" %>



<div class="fieldcontain ${hasErrors(bean: foroGeneralInstance, field: 'nombre', 'error')} ">
	<label for="nombre">
		<g:message code="foroGeneral.nombre.label" default="Nombre" />
		
	</label>
	<g:textField name="nombre" value="${foroGeneralInstance?.nombre}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: foroGeneralInstance, field: 'publicaciones', 'error')} ">
	<label for="publicaciones">
		<g:message code="foroGeneral.publicaciones.label" default="Publicaciones" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${foroGeneralInstance?.publicaciones?}" var="p">
    <li><g:link controller="publicacionGeneral" action="show" id="${p.id}">${p?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="publicacionGeneral" action="create" params="['foroGeneral.id': foroGeneralInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'publicacionGeneral.label', default: 'PublicacionGeneral')])}</g:link>
</li>
</ul>

</div>

