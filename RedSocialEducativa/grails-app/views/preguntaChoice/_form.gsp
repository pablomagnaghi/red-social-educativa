<g:if test="${flash.message}">
	<div class="box-content alerts">
		<div class="alert alert-error"><button class="close" data-dismiss="alert" type="button"></button><strong>${flash.message}</strong></div>
	</div>
</g:if>
<g:hasErrors bean="${preguntaChoiceInstance}">
	<div class="box-content alerts">
		<div class="alert alert-error"><button class="close" data-dismiss="alert" type="button"></button><strong>Revise el formulario</strong></div>
	</div>
</g:hasErrors>

<!-- PREGUNTA -->
<g:if test="${!hasErrors(bean: preguntaChoiceInstance, field: 'pregunta', 'error')}">
	<div class="control-group">
		<label class="control-label" >Pregunta</label>			
		<div class="controls"><g:textField name="pregunta" value="${preguntaChoiceInstance?.pregunta}" style='width: 62%' maxlength="64"/></div>	
	</div>		
</g:if>
<g:else>
	<div class="control-group error">
		<label class="control-label" >Pregunta</label>			
		<div class="controls">
			<g:textField name="pregunta" value="${preguntaChoiceInstance?.pregunta}" style='width: 62%' maxlength="64"/>
			<span class="help-inline"><g:renderErrors bean="${preguntaChoiceInstance}" as="list" field="pregunta"/></span>
		</div>	
	</div>	 
</g:else>

<!-- OPCIONES -->
<g:if test="${!hasErrors(bean: preguntaChoiceInstance, field: 'opciones', 'error')}">
	<div class="control-group">
		<label class="control-label" >Opciones</label>			
		<div class="controls"><g:field name="opciones" type="number" value="${preguntaChoiceInstance.opciones}"/></div>	
	</div>		
</g:if>
<g:else>
	<div class="control-group error">
		<label class="control-label" >Opciones</label>			
		<div class="controls">
			<g:field name="opciones" type="number" value="${preguntaChoiceInstance.opciones}"/>
			<span class="help-inline"><g:renderErrors bean="${preguntaChoiceInstance}" as="list" field="opciones"/></span>
		</div>	
	</div>	 
</g:else>
		
<g:hiddenField name="encuesta.id" value="${params.encuestaId}"/>
