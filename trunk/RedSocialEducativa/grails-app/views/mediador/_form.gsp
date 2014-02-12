<%@ page import="com.fiuba.Mediador" %>

<div class="fieldcontain ${hasErrors(bean: mediadorInstance, field: 'curso', 'error')} required">
	<label for="curso">
		<g:message code="mediador.curso.label" default="Curso" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="curso" name="curso.id" from="${com.fiuba.Curso.list()}" optionKey="id" required="" value="${mediadorInstance?.curso?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: mediadorInstance, field: 'jerarquia', 'error')} ">
	<label for="jerarquia">
		<g:message code="mediador.jerarquia.label" default="Jerarquia" />
		
	</label>
	<g:select name="jerarquia" from="${['JTP', 'AP', 'AS', 'Colaborador"']}" />
</div>

<div class="fieldcontain ${hasErrors(bean: mediadorInstance, field: 'usuario', 'error')} required">
	<label for="usuario">
		<g:message code="mediador.usuario.label" default="Usuario" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="usuario" name="usuario.id" from="${com.fiuba.Miembro.list().usuario}" optionKey="id" required="" value="${mediadorInstance?.usuario?.id}" class="many-to-one"/>
</div>

<div><g:hiddenField name="rol.id" value="${com.fiuba.Rol.findByAuthority(com.fiuba.Utilidades.ROL_MEDIADOR).id}"/></div>