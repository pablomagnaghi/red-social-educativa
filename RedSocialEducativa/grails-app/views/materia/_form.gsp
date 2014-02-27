<div class="control-group">
	<label class="control-label" >Codigo</label>			
	<div class="controls">
		<g:textField name="codigo" value="${materiaInstance?.codigo}"/>
	</div>	
</div>								
<div class="control-group">
	<label class="control-label" >Nombre</label>			
	<div class="controls">
		<g:textField name="nombre" value="${materiaInstance?.nombre}"/>
	</div>	
</div>			
<div class="control-group">
	<label class="control-label" >Creditos</label>			
	<div class="controls">
		<g:field name="creditos" type="number" value="${materiaInstance.creditos}" />
	</div>	
</div>				
<div class="control-group">
	<label class="control-label" >Contenidos Minimos</label>			
	<div class="controls">
		<g:textArea name="contenidosMinimos" value="${materiaInstance?.contenidosMinimos}" />
	</div>	
</div>				
