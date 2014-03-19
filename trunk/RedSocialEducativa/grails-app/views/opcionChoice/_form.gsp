<g:if test="${flash.message}">
	<div class="box-content alerts">
		<div class="alert alert-error"><button class="close" data-dismiss="alert" type="button"></button><strong>${flash.message}</strong></div>
	</div>
</g:if>
<g:hasErrors bean="${opcionChoiceInstance}">
	<div class="box-content alerts">
		<div class="alert alert-error"><button class="close" data-dismiss="alert" type="button"></button><strong>Revise el formulario</strong></div>
	</div>
</g:hasErrors>
<!-- OPCION -->
<g:if test="${!hasErrors(bean: opcionChoiceInstance, field: 'opcion', 'error')}">
	<div class="control-group">
		<label class="control-label" >Opcion</label>			
		<div class="controls"><g:textArea name="opcion" value="${opcionChoiceInstance?.opcion}" style='width: 90%; height: 100px;'/></div>
	</div>		
</g:if>
<g:else>
	<div class="control-group error">
		<label class="control-label" >Opcion</label>			
		<div class="controls">
			<g:textArea name="opcion" value="${opcionChoiceInstance?.opcion}" style='width: 90%; height: 100px;'/>
			<span class="help-inline"><g:renderErrors bean="${opcionChoiceInstance}" as="list" field="opcion"/></span>
		</div>	
	</div>	 
</g:else>

<g:hiddenField name="pregunta.id" value="${params.preguntaId}"/>
