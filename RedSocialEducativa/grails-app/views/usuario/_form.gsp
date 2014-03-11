<h4>Los campos indicados por el (*) son obligatorios.</h4>
<g:if test="${flash.message}">
	<div class="box-content alerts">
		<div class="alert alert-error"><button class="close" data-dismiss="alert" type="button"></button><strong>${flash.message}</strong></div>
	</div>    
</g:if>

<!-- DNI -->
<g:if test="${!hasErrors(bean: usuarioInstance, field: 'dni', 'error')}">
	<div class="control-group">
		<label class="control-label">DNI *</label>
		<div class="controls"><g:textField name="dni" value="${usuarioInstance?.dni}" maxlength="8"/></div>
	</div>
</g:if>
<g:else>
	<div class="control-group error">
		<label class="control-label">DNI *</label>
		<div class="controls">
			<g:textField name="dni" value="${usuarioInstance?.dni}" maxlength="8"/>
			<span class="help-inline"><g:renderErrors bean="${usuarioInstance}" as="list" field="dni"/></span>
		</div>
	</div>    
</g:else>
<!-- APELLIDO -->
<g:if test="${!hasErrors(bean: usuarioInstance, field: 'apellido', 'error')}">
	<div class="control-group">
		<label class="control-label">Apellido *</label>
			<div class="controls"><g:textField name="apellido" value="${usuarioInstance?.apellido}"/> </div>
	</div> 
</g:if>
<g:else>
	<div class="control-group error">
		<label class="control-label">Apellido *</label>
		<div class="controls">
			<g:textField name="apellido" value="${usuarioInstance?.apellido}" />
			<span class="help-inline"> <g:renderErrors bean="${usuarioInstance}" as="list" field="apellido" /></span>
		</div>
	</div>
</g:else>
<!-- NOMBRES -->
<g:if test="${!hasErrors(bean: usuarioInstance, field: 'nombres', 'error')}">
	<div class="control-group">
		<label class="control-label">Nombres *</label>
		<div class="controls"><g:textField name="nombres" value="${usuarioInstance?.nombres}" /></div>
	</div>
</g:if>
<g:else>
	<div class="control-group error">
		<label class="control-label">Nombres *</label>
		<div class="controls">
			<g:textField name="nombres" value="${usuarioInstance?.nombres}" />
			<span class="help-inline"> <g:renderErrors bean="${usuarioInstance}" as="list" field="nombres" /></span>
		</div>
	</div>
</g:else>
<!-- LEGAJO -->
<g:if
	test="${!hasErrors(bean: usuarioInstance, field: 'legajo', 'error')}">
	<div class="control-group">
		<label class="control-label">Legajo</label>
		<div class="controls">
			<g:textField name="legajo" value="${usuarioInstance?.legajo}" maxlength="9" />
		</div>
	</div>
</g:if>
<g:else>
	<div class="control-group error">
		<label class="control-label">Legajo</label>
		<div class="controls">
			<g:textField name="legajo" value="${usuarioInstance?.legajo}" maxlength="9" />
			<span class="help-inline"> <g:renderErrors bean="${usuarioInstance}" as="list" field="legajo" /></span>
		</div>
	</div>
</g:else>
<!-- PADRON -->
<g:if
	test="${!hasErrors(bean: usuarioInstance, field: 'padron', 'error')}">
	<div class="control-group">
		<label class="control-label">Padron</label>
		<div class="controls"><g:textField name="padron" value="${usuarioInstance?.padron}" maxlength="6" /></div>
	</div>
</g:if>
<g:else>
	<div class="control-group error">
		<label class="control-label">Padron</label>
		<div class="controls">
			<g:textField name="padron" value="${usuarioInstance?.padron}" maxlength="6" />
			<span class="help-inline"> <g:renderErrors bean="${usuarioInstance}" as="list" field="padron" /></span>
		</div>
	</div>
</g:else>
<!-- EMAIL -->
<g:if test="${!hasErrors(bean: usuarioInstance, field: 'email', 'error')}">
	<div class="control-group">
		<label class="control-label">Email *</label>
		<div class="controls"><g:textField name="email" value="${usuarioInstance?.email}" /></div>
	</div>
</g:if>
<g:else>
	<div class="control-group error">
		<label class="control-label">Email *</label>
		<div class="controls">
			<g:textField name="email" value="${usuarioInstance?.email}" />
			<span class="help-inline"> <g:renderErrors bean="${usuarioInstance}" as="list" field="email" /></span>
		</div>
	</div>
</g:else>
