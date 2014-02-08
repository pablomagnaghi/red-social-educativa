<%@ page import="com.fiuba.Aprendiz" %>
<div class="fieldcontain ${hasErrors(bean: aprendizInstance, field: 'usuario', 'error')} required">
	<label for="usuario"> <g:message code="aprendiz.usuario.label" default="Usuario" /> 
		<span class="required-indicator">*</span>
	</label>
	<g:select id="usuario" name="usuario.id" from="${com.fiuba.Miembro.list().usuario}" optionKey="id" required=""
		value="${aprendizInstance?.usuario?.id}" class="many-to-one" />
</div>
<div>
	<g:hiddenField name="ultVisita" value="${aprendizInstance?.ultVisita}" />
</div>
<div>
	<g:hiddenField name="descMaterial" value="0" />
</div>

<div>
	<g:hiddenField name="msjEnviados" value="0" />
</div>

<div>
	<g:hiddenField name="msjLeidos" value="0" />
</div>

<div>
	<g:hiddenField name="participa" value="${true}" />
</div>

<div>
	<g:hiddenField name="pubForos" value="0" />
</div>

<div>
	<g:hiddenField name="rol.id" value="${com.fiuba.Rol.findByAuthority('ROL_APRENDIZ').id}" />
</div>

<div>
	<g:hiddenField name="cuatrimestre.id" value="${cuatrimestreId}" />
</div>



