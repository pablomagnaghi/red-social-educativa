<div class="login-box">
	<g:if test='${flash.message}'>
		<div class='login_message'>${flash.message}</div>
	</g:if>
	<h3>Bienvenido a la red social educativa FIUBA</h3>
	<p>Mantente actualizado con la facultad</p>
	<form class="form-horizontal" action='${postUrl}' method='POST' id='loginForm' autocomplete='off'>
		<fieldset>								
			<input class="input-large span12" name='j_username' id='username' type="text" placeholder="username" />
			<input class="input-large span12" name='j_password' id='password' type="password" placeholder="password" />
			<div class="clearfix"></div>
				<p id="remember_me_holder">
					<label class="remember" for="remember"><input type="checkbox" name='${rememberMeParameter}' id='remember_me' 
						<g:if test='${hasCookie}'>checked='checked'</g:if>/> Recordarme</label>
				</p>	
			<div class="clearfix"></div>
								
			<button type="submit" class="btn btn-primary span12">Iniciar sesión</button>
		</fieldset>	
	</form>
	<hr />
	<h3>¿Olvidaste tu contraseña? <small><a href="#">Obtener una nueva.</a></small></h3>
</div>				