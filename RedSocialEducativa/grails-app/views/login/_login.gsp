<div class="login-box">
	<h3>Bienvenido a la red social FIUBA</h3>
	<p>Mantente actualizado con la facultad</p>
	<form class="form-horizontal" action='${postUrl}' method='POST' id='loginForm' autocomplete='off'>
		<fieldset>								
			<input class="input-large span12" name='j_username' id='username' type="text" placeholder="username" />
			<input class="input-large span12" name='j_password' id='password' type="password" placeholder="password" />
			<div class="clearfix"></div>
				<p id="remember_me_holder">
					<label class="remember" for="remember"><input type="checkbox" name='${rememberMeParameter}' id='remember_me' 
						<g:if test='${hasCookie}'>checked='checked'</g:if>/> Recordarme <a href="#" class="btn-setting">&nbsp;&nbsp;¿Olvidaste tu contraseña?</a>
					</label>	
				</p>	
			<div class="clearfix"></div>
								
			<button type="submit" class="btn btn-primary span12">Iniciar sesión</button>
		</fieldset>	
	</form>
	<hr />
	<g:form class="form-horizontal" controller="red" action="solicitarMembresia">
		<fieldset>								
			<button type="submit" class="btn btn-success span6">Crear una cuenta</button>
		</fieldset>	
	</g:form>
	<g:if test='${flash.message}'>
		<div class='login_message'>${flash.message}</div>
	</g:if>
</div>				