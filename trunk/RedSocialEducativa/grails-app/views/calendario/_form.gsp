<g:if test="${flash.message}">
	<div class="box-content alerts">
		<div class="alert alert-error"><button class="close" data-dismiss="alert" type="button"></button><strong></strong> ${flash.message}</div>
	</div>    
</g:if>

<g:hasErrors bean="${calendarioInstance}">
	<div class="box-content alerts">
		<div class="alert alert-error"><button class="close" data-dismiss="alert" type="button"></button><strong>Revise el formulario</strong> </div>
	</div>   
</g:hasErrors>

<div class="control-group">
	<label class="control-label" >Inicio primer cuatrimestre</label>			
	<div class="controls">
		<g:datePicker name="fechaPrimerCuatrimestre" precision="day"  value="${fechaPrimerCuatrimestreDate}" />
	</div>	
</div>	
<div class="control-group">
	<label class="control-label">Inicio segundo cuatrimestre</label>			
	<div class="controls">
		<g:datePicker name="fechaSegundoCuatrimestre" precision="day"  value="${fechaSegundoCuatrimestreDate}" />
	</div>	
</div>	
