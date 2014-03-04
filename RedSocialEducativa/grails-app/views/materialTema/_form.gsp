<div class="control-group">
	<label class="control-label" >Categoria</label>			
	<div class="controls">
		<g:select id="categoria" name="categoria.id" from="${com.fiuba.CategoriaMaterial.list()}" optionKey="id"
			value="${materialTemaInstance?.categoria?.id}" class="many-to-one"/>
	</div>	
</div>
<div class="control-group">
	<label class="control-label" >Autor</label>			
	<div class="controls">
		<g:textField name="autor" value="${materialTemaInstance?.autor}"/>
	</div>	
</div>
<div class="control-group">
	<label class="control-label" >Descripcion</label>			
	<div class="controls">
		<g:textField name="descripcion" value="${materialTemaInstance?.descripcion}"/>
	</div>	
</div>
