<div class="control-group">
	<label class="control-label" >Tema</label>			
	<div class="controls">
		<g:select id="tema" name="tema.id" from="${temas}" optionKey="id" required="" 
		value="${temaActividadInstance?.tema?.id}" class="many-to-one"/>
	</div>	
</div>
