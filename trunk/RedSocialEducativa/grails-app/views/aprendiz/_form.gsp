<%@ page import="com.fiuba.Aprendiz" %>



<div class="fieldcontain ${hasErrors(bean: aprendizInstance, field: 'ultVisita', 'error')} ">
	<label for="ultVisita">
		<g:message code="aprendiz.ultVisita.label" default="Ult Visita" />
		
	</label>
	<g:datePicker name="ultVisita" precision="day"  value="${aprendizInstance?.ultVisita}" default="none" noSelection="['': '']" />
</div>

<div class="fieldcontain ${hasErrors(bean: aprendizInstance, field: 'curso', 'error')} required">
	<label for="curso">
		<g:message code="aprendiz.curso.label" default="Curso" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="curso" name="curso.id" from="${com.fiuba.Curso.list()}" optionKey="id" required="" value="${aprendizInstance?.curso?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: aprendizInstance, field: 'descMaterial', 'error')} required">
	<label for="descMaterial">
		<g:message code="aprendiz.descMaterial.label" default="Desc Material" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="descMaterial" type="number" value="${aprendizInstance.descMaterial}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: aprendizInstance, field: 'msjEnviados', 'error')} required">
	<label for="msjEnviados">
		<g:message code="aprendiz.msjEnviados.label" default="Msj Enviados" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="msjEnviados" type="number" value="${aprendizInstance.msjEnviados}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: aprendizInstance, field: 'msjLeidos', 'error')} required">
	<label for="msjLeidos">
		<g:message code="aprendiz.msjLeidos.label" default="Msj Leidos" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="msjLeidos" type="number" value="${aprendizInstance.msjLeidos}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: aprendizInstance, field: 'participa', 'error')} ">
	<label for="participa">
		<g:message code="aprendiz.participa.label" default="Participa" />
		
	</label>
	<g:checkBox name="participa" value="${aprendizInstance?.participa}" />
</div>

<div class="fieldcontain ${hasErrors(bean: aprendizInstance, field: 'pubForos', 'error')} required">
	<label for="pubForos">
		<g:message code="aprendiz.pubForos.label" default="Pub Foros" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="pubForos" type="number" value="${aprendizInstance.pubForos}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: aprendizInstance, field: 'rol', 'error')} required">
	<label for="rol">
		<g:message code="aprendiz.rol.label" default="Rol" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="rol" name="rol.id" from="${com.fiuba.Rol.list()}" optionKey="id" required="" value="${aprendizInstance?.rol?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: aprendizInstance, field: 'usuario', 'error')} required">
	<label for="usuario">
		<g:message code="aprendiz.usuario.label" default="Usuario" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="usuario" name="usuario.id" from="${com.fiuba.Usuario.list()}" optionKey="id" required="" value="${aprendizInstance?.usuario?.id}" class="many-to-one"/>
</div>

