<!-- FECHA -->
<div class="control-group">
	<label class="control-label" >Fecha</label>			
	<div class="controls">
		<g:datePicker name="fechaDate" precision="day" value="${new Date()}"  />
	</div>	
</div>

<!-- HORARIO -->
<div class="control-group">
	<label class="control-label" >horario</label>			
	<div class="controls">
		<g:select name="horario" from="${['0700', '0730', '0800', '0830', '0900', '0930', '1000', '1030', '1100', '1130', '1200',
			'1230', '1300', '1330', '1400', '1430', '1500', '1530', '1600', '1630', '1700', '1730', '1800', '1830', '1900', '1930',
			'2000', '2030', '2100', '2130', '2200']}" />
	</div>	
</div>		

<!-- AULA -->
<div class="control-group">
	<label class="control-label" >Aula (opcional)</label>			
	<div class="controls">
		<g:textField name="aula" value="${evaluacionInstance?.aula}" maxlength="16" />
	</div>	
</div>

<!-- HABILITADA -->
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
	
<g:hiddenField name="curso.id" value="${params.cursoId}"/>	
							
							
							
							
							