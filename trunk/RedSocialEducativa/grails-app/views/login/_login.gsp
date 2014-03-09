<div class="login-box">
	<h3>Bienvenido a la red social FIUBA</h3>
	<p>Entorno virtual de enseñanza y aprendizaje</p>
	<form class="form-horizontal" action='${postUrl}' method='POST' id='loginForm' autocomplete='off'>
		<fieldset>								
			<input class="input-large span12" name='j_username' id='username' type="text" placeholder="nombre de usuario"  maxlength="16"/>
			<input class="input-large span12" name='j_password' id='password' type="password" placeholder="contraseña"  maxlength="16"/>
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
	<g:if test='${params.size() == 4 && flash.message}'>
		<div class="box-content alerts">
    		<div class="alert alert-error">
				<button class="close" data-dismiss="alert" type="button"></button>
				${flash.message}
		    </div>
		</div> 
	</g:if>
	<hr />
	<g:form class="form-horizontal" controller="red" action="solicitarMembresia">
		<fieldset>								
			<button type="submit" class="btn btn-success span6">Crear una cuenta</button>
		</fieldset>	
	</g:form>
	<g:if test='${params.size() == 2 && flash.message}'>
		<div class="box-content alerts">
    		<div class="alert alert-info">
				<button class="close" data-dismiss="alert" type="button"></button>
				<strong></strong> 
				${flash.message}
		    </div>
		</div>    
	</g:if>
</div>				