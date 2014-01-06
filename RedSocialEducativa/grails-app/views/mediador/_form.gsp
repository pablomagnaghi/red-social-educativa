<%@ page import="com.fiuba.Mediador" %>



<div class="fieldcontain ${hasErrors(bean: mediadorInstance, field: 'cursos', 'error')} ">
	<label for="cursos">
		<g:message code="mediador.cursos.label" default="Cursos" />
		
	</label>
	
</div>

<div class="fieldcontain ${hasErrors(bean: mediadorInstance, field: 'jerarquia', 'error')} ">
	<label for="jerarquia">
		<g:message code="mediador.jerarquia.label" default="Jerarquia" />
		
	</label>
	<g:textField name="jerarquia" value="${mediadorInstance?.jerarquia}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: mediadorInstance, field: 'membresia', 'error')} required">
	<label for="membresia">
		<g:message code="mediador.membresia.label" default="Membresia" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="membresia" name="membresia.id" from="${com.fiuba.Membresia.list()}" optionKey="id" required="" value="${mediadorInstance?.membresia?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: mediadorInstance, field: 'rol', 'error')} required">
	<label for="rol">
		<g:message code="mediador.rol.label" default="Rol" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="rol" name="rol.id" from="${com.fiuba.Rol.list()}" optionKey="id" required="" value="${mediadorInstance?.rol?.id}" class="many-to-one"/>
</div>

