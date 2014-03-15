<g:if test="${flash.message}">
	<div class="box-content alerts">
		<div class="alert alert-error"><button class="close" data-dismiss="alert" type="button"></button><strong>${flash.message}</strong></div>
	</div>
</g:if>
<g:hasErrors bean="${evaluacionInstance}">
	<div class="box-content alerts">
		<div class="alert alert-error"><button class="close" data-dismiss="alert" type="button"></button><strong>Revise el formulario</strong></div>										
	</div>
</g:hasErrors>

<!-- NOMBRE -->
<g:if test="${!hasErrors(bean:evaluacionInstance, field: 'nombre', 'error')}">
	<div class="control-group">
		<label class="control-label" >Nombre</label>			
		<div class="controls"><g:textField name="nombre" value="${evaluacionInstance?.nombre}" style='width: 62%' maxlength="64"/></div>	
	</div>		
</g:if>
<g:else>
	<div class="control-group error">
		<label class="control-label" >Nombre</label>			
		<div class="controls">
			<g:textField name="nombre" value="${evaluacionInstance?.nombre}" style='width: 62%' maxlength="64"/>
			<span class="help-inline"><g:renderErrors bean="${evaluacionInstance}" as="list" field="nombre"/></span>
		</div>	
	</div>	 
</g:else>
<!-- FECHA -->
<div class="control-group">
	<label class="control-label" >Fecha</label>			
	<div class="controls">
		<g:datePicker name="fechaDate" precision="day" value="${new Date()}"  />
	</div>	
</div>
<!-- HORARIO -->
<div class="control-group">
	<label class="control-label" >Horario</label>			
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
<!-- OBLIGATORIA -->
<div class="control-group">
	<label class="control-label" >Obligatoria</label>			
	<div class="controls"><g:checkBox name="obligatoria" value="${evaluacionInstance?.obligatoria}" /></div>	
</div>		
<!-- HABILITADA -->
<div class="control-group">
	<label class="control-label" >Habilitada</label>			
	<div class="controls">
		<g:checkBox name="habilitada" value="${evaluacionInstance?.habilitada}" />
	</div>	
</div>	

<g:hiddenField name="curso.id" value="${params.cursoId}"/>	
						