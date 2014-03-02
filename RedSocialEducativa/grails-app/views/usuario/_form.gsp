<div class="control-group">
	<label class="control-label" >DNI</label>			
	<div class="controls">
		<g:textField name="username" value="${usuarioInstance?.username}"/>
	</div>	
</div>								
<div class="control-group">
	<label class="control-label" >Apellido</label>			
	<div class="controls">
		<g:textField name="apellido" value="${usuarioInstance?.apellido}"/>
	</div>	
</div>
<div class="control-group">
	<label class="control-label" >Nombres</label>			
	<div class="controls">
		<g:textField name="nombres" value="${usuarioInstance?.nombres}"/>
	</div>	
</div>
<div class="control-group">
	<label class="control-label" >Email</label>			
	<div class="controls">
		<g:field type="email" name="email" value="${usuarioInstance?.email}"/>
	</div>	
</div>
<div class="control-group">
	<label class="control-label" for="focusedInput">Password</label>
	<div class="controls">
		<input class="input-large focused" id="focusedInput" name="password" type="password" value="${usuarioInstance?.password}" maxlength="12" />
	</div>
</div> 