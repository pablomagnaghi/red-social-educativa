<div class="control-group">
	<label class="control-label" >Aula</label>			
	<div class="controls">
		<g:textField name="aula" value="${evaluacionInstance?.aula}"/>
	</div>	
</div>
<div class="control-group">
	<label class="control-label" >Fecha</label>			
	<div class="controls">
		<g:datePicker name="fechaDate" precision="minute"  value="${new Date()}"  />
	</div>	
</div>
<g:if test="${!evaluacionInstance?.habilitada}">
	<div class="control-group">
		<label class="control-label" >Habilitada</label>			
		<div class="controls">
			<g:checkBox name="habilitada" value="${evaluacionInstance?.habilitada}" />
		</div>	
	</div>			
</g:if>
<g:else>
	<g:hiddenField name="habilitada" value="${evaluacionInstance.habilitada}"/>
</g:else>					
							
