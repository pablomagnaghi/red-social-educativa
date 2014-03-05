<div class="control-group">
	<label class="control-label" >Aprendiz</label>			
	<div class="controls">
		<g:select id="aprendiz" name="aprendiz.id" from="${aprendices}" optionKey="id" required="" 
			value="${evaluacionAprendizInstance?.aprendiz?.id}" class="many-to-one"/>
	</div>	
</div>