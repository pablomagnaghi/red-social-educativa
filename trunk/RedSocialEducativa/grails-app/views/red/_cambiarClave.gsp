<div class="row-fluid">
    <div class="box span12">
        <div class="box-header">
            <h2><i class="icon-edit"></i>Cambio de contraseña</h2>
            <div class="box-icon">
                <g:link class="list" controller="login" action="auth"><i class="icon-home"></i></g:link>
            </div>
        </div>
        <div class="box-content">
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
            <g:form class="form-horizontal" controller="red" action="revisarClave" id="${params.id}">
	            <fieldset>
					<div class="control-group">
	                    <label class="control-label" for="focusedInput">Password</label>
	                    <div class="controls">
	                        <input class="input-large focused" id="focusedInput" name="password" type="password" maxlength="12" />
	                    </div>
					</div> 
					<div class="control-group">
	                    <label class="control-label" for="focusedInput">Confirmar Password</label>
	                    <div class="controls">
	                        <input class="input-large focused" id="focusedInput" name="passwordConfirmado" type="password" maxlength="12" />
	                    </div>
					</div> 
					<div class="form-actions">
						<button type="submit" class="btn btn-primary">Cambiar</button>
                	</div>	
					<div><g:hiddenField name="enabled" value="${false}"/></div>
	            </fieldset>
            </g:form>
        </div>
    </div>
    <!--/span-->
</div>
<!--/row-->
