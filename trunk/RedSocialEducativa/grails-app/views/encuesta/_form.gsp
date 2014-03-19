<g:if test="${flash.message}">
	<div class="box-content alerts">
		<div class="alert alert-error"><button class="close" data-dismiss="alert" type="button"></button><strong>${flash.message}</strong></div>
	</div>
</g:if>
<g:hasErrors bean="${encuestaInstance}">
	<div class="box-content alerts">
		<div class="alert alert-error"><button class="close" data-dismiss="alert" type="button"></button><strong>Revise el formulario</strong></div>
		
	</div>
</g:hasErrors>

<!-- NOMBRE -->
<g:if test="${!hasErrors(bean: encuestaInstance, field: 'nombre', 'error')}">
	<div class="control-group">
		<label class="control-label" >Nombre</label>			
		<div class="controls"><g:textField name="nombre" value="${encuestaInstance?.nombre}" style='width: 62%' maxlength="64"/></div>	
	</div>		
</g:if>
<g:else>
	<div class="control-group error">
		<label class="control-label" >Nombre</label>			
		<div class="controls">
			<g:textField name="nombre" value="${encuestaInstance?.nombre}" style='width: 62%' maxlength="64"/>
			<span class="help-inline"><g:renderErrors bean="${encuestaInstance}" as="list" field="nombre"/></span>
		</div>	
	</div>	 
</g:else>

<g:hiddenField name="curso.id" value="${params.cursoId}"/>
