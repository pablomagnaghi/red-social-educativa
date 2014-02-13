<div role="navigation" class="navbar navbar-default navbar-static-top">
	<div class="container">
		<div class="navbar-header">
			<button data-target=".navbar-collapse" data-toggle="collapse"
				class="navbar-toggle" type="button">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
		</div>
		<div class="navbar-collapse collapse">
			<g:img file="logo.png" />
			<ul class="nav navbar-nav navbar-right">
				<sec:ifLoggedIn>
				Bienvenido <sec:username /> (<g:link controller='logout'>Salir</g:link>)
			</sec:ifLoggedIn>
				<sec:ifNotLoggedIn>
					<div>
						<g:link class="list" action="solicitarMembresia">
							<g:message code="Solicitar Membresía" />
						</g:link>
					</div>
					<div id="loginForm">
						<form method="POST" class="form-inline" role="form"
							action="${resource(file: 'j_spring_security_check')}">
							<div class="form-group">
								<label class="sr-only" for="exampleInputEmail2">DNI:</label> <input
									type="text" class="form-control text_" name="j_username"
									id="username" placeholder="Introduzca DNI">
							</div>
							<div class="form-group">
								<label class="sr-only" for="exampleInputPassword2">Password:</label>
								<input type="password" class="form-control text_"
									name='j_password' id="password" placeholder="Password">
							</div>
							<div class="checkbox">
								<label> <input type="checkbox" class='chk'
									id='remember_me' name='_spring_security_remember_me'>
									Recordarme
								</label>
							</div>
							<button type="submit" class="btn btn-default" name="login">Acceder</button>
						</form>
					</div>
				</sec:ifNotLoggedIn>
			</ul>
		</div>
		<!--/.nav-collapse -->
	</div>
</div>