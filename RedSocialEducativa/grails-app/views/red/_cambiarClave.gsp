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
				<div class="box-content alerts">
				    <div class="alert alert-error">
				        <button class="close" data-dismiss="alert" type="button"></button>
				        <strong>${flash.message}</strong>
				    </div>
				</div>    
			</g:if>
            <g:form class="form-horizontal" controller="red" action="revisarClave" id="${params.id}">
	            <fieldset>
					 <div class="control-group">
		               	<label class="control-label">Contraseña *</label>
		                <div class="controls">
		                    <g:field type="password" name="password" maxlength="16" />
	                    </div>
					</div>
					<div class="control-group">
		               	<label class="control-label">Confirmar Contraseña *</label>
		                <div class="controls">
		                    <g:field type="password" name="passwordConfirmado" maxlength="16" />
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
