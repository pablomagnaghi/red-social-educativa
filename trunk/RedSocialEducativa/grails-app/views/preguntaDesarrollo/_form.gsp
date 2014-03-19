<g:if test="${flash.message}">
	<div class="box-content alerts">
		<div class="alert alert-error"><button class="close" data-dismiss="alert" type="button"></button><strong>${flash.message}</strong></div>
	</div>
</g:if>
<g:hasErrors bean="${preguntaDesarrolloInstance}">
	<div class="box-content alerts">
		<div class="alert alert-error"><button class="close" data-dismiss="alert" type="button"></button><strong>Revise el formulario</strong></div>
	</div>
</g:hasErrors>
<!-- PREGUNTA -->
<g:if test="${!hasErrors(bean: preguntaDesarrolloInstance, field: 'pregunta', 'error')}">
	<div class="control-group">
		<label class="control-label" >Pregunta</label>			
		<div class="controls"><g:textArea name="pregunta" value="${preguntaDesarrolloInstance?.pregunta}" style='width: 90%; height: 100px;'/></div>
	</div>		
</g:if>
<g:else>
	<div class="control-group error">
		<label class="control-label" >Pregunta</label>			
		<div class="controls">
			<g:textArea name="pregunta" value="${preguntaDesarrolloInstance?.pregunta}" style='width: 90%; height: 100px;'/>
			<span class="help-inline"><g:renderErrors bean="${preguntaDesarrolloInstance}" as="list" field="pregunta"/></span>
		</div>	
	</div>	 
</g:else>

<g:hiddenField name="encuesta.id" value="${params.encuestaId}"/>
