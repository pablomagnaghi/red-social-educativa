<div class="row-fluid">
    <div class="box span12">
        <div class="box-header">
            <h2><i class="icon-pencil"></i>Solicitud de membresía</h2>
            <div class="box-icon">
                <g:link class="list" controller="login" action="auth"><i class="icon-home"></i></g:link>
            </div>
        </div>
        <div class="box-content">
        		
			<h4>Los campos indicados por el (*) son obligatorios.</h4>
			<g:if test="${flash.message}">
				<div class="box-content alerts">
				    <div class="alert alert-error">
				        <button class="close" data-dismiss="alert" type="button"></button>
				        <strong>${flash.message}</strong>
				    </div>
				</div>    
			</g:if>
            <g:form class="form-horizontal" controller="red" action="revisarDatosUsuario">
	            <fieldset>
	            	<!-- DNI -->
	            	 <g:if test="${!hasErrors(bean: usuarioInstance, field: 'dni', 'error')}">
		                <div class="control-group">
		                    <label class="control-label">DNI *</label>
		                    <div class="controls">
		                        <g:textField name="dni" value="${usuarioInstance?.dni}"/>
		                    </div>
						</div>
					</g:if>
					<g:else>
						<div class="control-group error">
						    <label class="control-label">DNI *</label>
						    <div class="controls">
						        <g:textField name="dni" value="${usuarioInstance?.dni}"/>
						        <span class="help-inline">
						        	 <g:renderErrors bean="${usuarioInstance}" as="list" field="dni"/>
						        </span>
						    </div>
						</div>    
					</g:else>
					<!-- APELLIDO -->
					 <g:if test="${!hasErrors(bean: usuarioInstance, field: 'apellido', 'error')}">
		                <div class="control-group">
		                    <label class="control-label">Apellido *</label>
		                    <div class="controls">
		                    	<g:textField name="apellido" value="${usuarioInstance?.apellido}"/> 
		                    </div>
						</div> 
					</g:if>
					<g:else>
						<div class="control-group error">
						    <label class="control-label">Apellido *</label>
						    <div class="controls">
						        <g:textField name="apellido" value="${usuarioInstance?.apellido}"/> 
						        <span class="help-inline">
						        	 <g:renderErrors bean="${usuarioInstance}" as="list" field="apellido"/>
						        </span>
						    </div>
						</div>   
					</g:else>
					<!-- NOMBRES -->
					<g:if test="${!hasErrors(bean: usuarioInstance, field: 'nombres', 'error')}">
		                <div class="control-group">
		                    <label class="control-label">Nombres *</label>
		                    <div class="controls">
		                    	<g:textField name="nombres" value="${usuarioInstance?.nombres}"/> 
		                    </div>
		                </div>    
					</g:if>
					<g:else>
						<div class="control-group error">
						    <label class="control-label">Nombres *</label>
						    <div class="controls">
						        <g:textField name="nombres" value="${usuarioInstance?.nombres}"/> 
						        <span class="help-inline">
						        	 <g:renderErrors bean="${usuarioInstance}" as="list" field="nombres"/>
						        </span>
						    </div>
						</div>   
					</g:else>
					<div class="control-group">
	                    <label class="control-label">Legajo</label>
	                    <div class="controls">
	                    	<g:textField name="legajo" value="${usuarioInstance?.legajo}"/> 
	                    </div>
					</div> 
					<div class="control-group">
	                    <label class="control-label" for="focusedInput">Padron</label>
	                    <div class="controls">
	                   		<g:textField name="padron" value="${usuarioInstance?.padron}"/> 
	                    </div>
					</div> 
					<!-- EMAIL -->
					<g:if test="${!hasErrors(bean: usuarioInstance, field: 'email', 'error')}">
		                <div class="control-group">
		                    <label class="control-label">Email *</label>
		                    <div class="controls">
		                    	<g:textField name="email" value="${usuarioInstance?.email}"/>                     
		                    </div>
						</div>    
					</g:if>
					<g:else>
						<div class="control-group error">
						    <label class="control-label">Email *</label>
						    <div class="controls">
						        <g:textField name="email" value="${usuarioInstance?.email}"/> 
						        <span class="help-inline">
						        	 <g:renderErrors bean="${usuarioInstance}" as="list" field="email"/>
						        </span>
						    </div>
						</div>   
					</g:else>
					<!-- NOMBRE DE USUARIO -->
					<g:if test="${!hasErrors(bean: usuarioInstance, field: 'username', 'error')}">
		                <div class="control-group">
		                    <label class="control-label">Nombre de usuario *</label>
		                    <div class="controls">
		                        <g:textField name="username" value="${usuarioInstance?.username}"/>
		                    </div>
						</div>    
					</g:if>
					<g:else>
						<div class="control-group error">
						    <label class="control-label" for="inputError">Nombre de usuario *</label>
						    <div class="controls">
						        <g:textField name="username" value="${usuarioInstance?.username}"/>
						        <span class="help-inline">
						        	 <g:renderErrors bean="${usuarioInstance}" as="list" field="username"/>
						        </span>
						    </div>
						</div>   
					</g:else>
					<!-- CONTRASEÑA -->
						<g:if test="${!hasErrors(bean: usuarioInstance, field: 'password', 'error')}">
		                <div class="control-group">
		                    <label class="control-label" for="focusedInput">Contraseña *</label>
		                    <div class="controls">
		                    	<g:field type="password" name="password" value="${usuarioInstance?.password}" maxlength="12" />
	                    	</div>
						</div>    
					</g:if>
					<g:else>
						<div class="control-group error">
						    <label class="control-label">Contraseña *</label>
						    <div class="controls">
						       <g:field type="password" name="password" value="${usuarioInstance?.password}" maxlength="12" />
						        <span class="help-inline">
						        	 <g:renderErrors bean="${usuarioInstance}" as="list" field="password"/>
						        </span>
						    </div>
						</div>   
					</g:else>
					<div class="control-group">
	                    <label class="control-label" for="focusedInput">Confirmar contraseña *</label>
	                    <div class="controls">
	                    	<g:field type="password" name="passwordConfirmado" maxlength="12" />
	                    </div>
					</div> 
					<div class="form-actions">
						<button type="submit" class="btn btn-primary">Enviar datos</button>
                	</div>	
					<div><g:hiddenField name="enabled" value="${false}"/></div>
	            </fieldset>
            </g:form>
        </div>
    </div>
    <!--/span-->
</div>
<!--/row-->
