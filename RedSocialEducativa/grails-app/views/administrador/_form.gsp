<g:if test="${flash.message}">
	<div class="box-content alerts">
		<div class="alert alert-error">
			<button class="close" data-dismiss="alert" type="button"></button>
			<strong></strong> 
			${flash.message}
		</div>
	</div>    
</g:if>

<g:hasErrors bean="${administradorInstance}">
	<div class="box-content alerts">
		<div class="alert alert-error">
			<button class="close" data-dismiss="alert" type="button"></button>
			<strong>Revise el formulario</strong> 
		</div>
	</div>   
</g:hasErrors>

<!-- USUARIO -->
<div class="control-group">
	<label class="control-label" >Usuario</label>			
	<div class="controls">
		<g:select id="usuario" name="usuario.id" from="${varUsuarioService.obtenerCandidatos()}" optionKey="id" class="many-to-one"/>
	</div>	
</div>		
