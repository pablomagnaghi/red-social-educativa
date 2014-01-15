<%@ page import="com.fiuba.Mediador" %>

<div class="fieldcontain ${hasErrors(bean: mediadorInstance, field: 'noticiasCurso', 'error')} ">
	<label for="noticiasCurso">
		<g:message code="mediador.noticiasCurso.label" default="Noticias Curso" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${mediadorInstance?.noticiasCurso?}" var="n">
    <li><g:link controller="noticiaCurso" action="show" id="${n.id}">${n?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="noticiaCurso" action="create" params="['mediador.id': mediadorInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'noticiaCurso.label', default: 'NoticiaCurso')])}</g:link>
</li>
</ul>

</div>

