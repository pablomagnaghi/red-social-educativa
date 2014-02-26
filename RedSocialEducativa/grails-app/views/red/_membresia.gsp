<div class="membresia-box">
	<h3>¿Eres nuevo en la Red Social FIUBA? Regístrate</h3>
		<g:if test="${flash.message}">
		  	<div class="message" role="status">${flash.message}</div>
		</g:if>
	<g:hasErrors bean="${usuarioInstance}">
		<ul class="errors" role="alert">
			<g:eachError bean="${usuarioInstance}" var="error">
			<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>>
				<g:message error="${error}"/></li>
			</g:eachError>
		</ul>
	</g:hasErrors>
	<g:form class="form-horizontal" controller="red" action="revisarDatosUsuario">
		<fieldset>	
			<div class="control-group">
				<label class="control-label" >DNI</label>			
				<div class="controls">
					<g:textField name="username" type="number" value="${usuarioInstance?.username}" />
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
				<label class="control-label" >Legajo</label>			
				<div class="controls">
					<g:textField name="legajo" type="number" value="${usuarioInstance?.legajo}"/>
					<div>Si aun no tiene, no completar</div>
				</div>	
			</div>		
			<div class="control-group">
				<label class="control-label" >Padron</label>			
				<div class="controls">
					<g:textField name="padron" type="number" value="${usuarioInstance?.padron}"/>
					<div>Si aun no tiene, no completar</div>
				</div>	
			</div>	
			<div class="control-group">
				<label class="control-label" >Email</label>			
				<div class="controls">
					<g:textField name="email" value="${usuarioInstance?.email}" />
				</div>	
			</div>					
			<div class="control-group">
				<label class="control-label" >Password</label>			
				<div class="controls">
					<g:passwordField name="password" maxlength="12" />
				</div>	
			</div>				
			<div class="control-group">
				<label class="control-label" >Confirmar password</label>			
				<div class="controls">
					<g:passwordField name="passwordConfirmado" maxlength="12" />
				</div>	
			</div>	
			<button type="submit" class="btn btn-primary span12">Abrir una cuenta</button>	
		</fieldset>	
	</g:form>
</div>

