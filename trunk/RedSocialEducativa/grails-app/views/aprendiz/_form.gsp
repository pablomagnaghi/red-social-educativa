<g:if test="${flash.message}">
	<div class="box-content alerts">
		<div class="alert alert-error"><button class="close" data-dismiss="alert" type="button"></button><strong></strong> ${flash.message}</div>
	</div>    
</g:if>

<div class="control-group">
	<label class="control-label" >Usuario</label>			
	<div class="controls">
		<g:select id="usuario" name="usuario.id" from="${varUsuarioService.obtenerCandidatos()}" optionKey="id" required=""
			value="${aprendizInstance?.usuario?.id}" class="many-to-one" />
	</div>	
</div>