<div class="control-group">
	<label class="control-label" >Usuario</label>			
	<div class="controls">
		<g:select id="usuario" name="usuario.id" from="${varUsuarioService.obtenerCandidatos()}" optionKey="id" required=""
			value="${aprendizInstance?.usuario?.id}" class="many-to-one" />
	</div>	
</div>