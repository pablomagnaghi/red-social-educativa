<div class="control-group">
	<label class="control-label" >Nombre</label>			
	<div class="controls">
		<g:textField name="nombre" value="${cursoInstance?.nombre}"/>
	</div>	
</div>								
<div class="control-group">
	<label class="control-label" >Cuat dict</label>			
	<div class="controls">
		<g:select name="cuatDict" from="${['1', '2', '1|2']}" value="${cursoInstance?.cuatDict}"/>
	</div>	
</div>			
