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
			<input class="input-large span12" name='username' type="text" placeholder="DNI" />
			<input class="input-large span12" name='apellido' type="text" placeholder="Apellido" />
			<input class="input-large span12" name='nombres' type="text" placeholder="Nombres" />
			<input class="input-large span12" name='legajo' type="text" placeholder="Legajo" />
			<input class="input-large span12" name='padron' type="text" placeholder="Padron" />
			<input class="input-large span12" name='email' type="text" placeholder="Email" />
			<input class="input-large span12" name='password' type="password" placeholder="password" />
			<input class="input-large span12" name='passwordConfirmado' type="password" placeholder="Confirmar password" />
			<button type="submit" class="btn btn-primary span12">Abrir una cuenta</button>	
		</fieldset>	
	</g:form>
</div>

