<div class="control-group">
	<label class="control-label" >Categoria</label>			
	<div class="controls">
		<g:select id="categoria" name="categoria.id" from="${com.fiuba.CategoriaMaterial.list()}" 
			optionKey="id" value="${materialGrupoActividadInstance?.categoria?.id}" class="many-to-one"/>
	</div>	
</div>
<div class="control-group">
	<label class="control-label" >Autor</label>			
	<div class="controls">
		<g:textField name="autor" value="${materialGrupoActividadInstance?.autor}"/>
	</div>	
</div>
<div class="control-group">
	<label class="control-label" >Descripcion</label>			
	<div class="controls">
		<g:textField name="descripcion" value="${materialGrupoActividadInstance?.descripcion}"/>
	</div>	
</div>
