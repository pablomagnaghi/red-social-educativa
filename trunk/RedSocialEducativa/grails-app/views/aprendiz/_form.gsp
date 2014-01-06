<%@ page import="com.fiuba.Aprendiz" %>



<div class="fieldcontain ${hasErrors(bean: aprendizInstance, field: 'cuatrismestres', 'error')} ">
	<label for="cuatrismestres">
		<g:message code="aprendiz.cuatrismestres.label" default="Cuatrismestres" />
		
	</label>
	
</div>

<div class="fieldcontain ${hasErrors(bean: aprendizInstance, field: 'descMaterial', 'error')} required">
	<label for="descMaterial">
		<g:message code="aprendiz.descMaterial.label" default="Desc Material" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="descMaterial" type="number" value="${aprendizInstance.descMaterial}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: aprendizInstance, field: 'membresia', 'error')} required">
	<label for="membresia">
		<g:message code="aprendiz.membresia.label" default="Membresia" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="membresia" name="membresia.id" from="${com.fiuba.Membresia.list()}" optionKey="id" required="" value="${aprendizInstance?.membresia?.id}" class="many-to-one"/>
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

<div class="fieldcontain ${hasErrors(bean: aprendizInstance, field: 'ultVisita', 'error')} required">
	<label for="ultVisita">
		<g:message code="aprendiz.ultVisita.label" default="Ult Visita" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="ultVisita" precision="day"  value="${aprendizInstance?.ultVisita}"  />
</div>

