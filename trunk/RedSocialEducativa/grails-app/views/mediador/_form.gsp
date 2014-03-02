<div class="control-group">
	<label class="control-label" >Usuario</label>			
	<div class="controls">
		<g:select id="usuario" name="usuario.id" from="${usuarios}" optionKey="id" class="many-to-one"/>
	</div>	
</div>		
<div class="control-group">
	<label class="control-label" >Curso</label>			
	<div class="controls">
		<g:select id="curso" name="curso.id" from="${com.fiuba.Curso.list()}" optionKey="id" class="many-to-one"/>
	</div>	
</div>								
<div class="control-group">
	<label class="control-label" >Jerarquia</label>			
	<div class="controls">
		<g:select name="jerarquia" from="${['JTP', 'AP', 'AS', 'Colaborador']}" />
	</div>	
</div>			
<div><g:hiddenField name="rol.id" value="${com.fiuba.Rol.findByAuthority(com.fiuba.Utilidades.ROL_MEDIADOR).id}"/></div>
