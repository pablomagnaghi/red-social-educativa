<div class="control-group">
	<label class="control-label" >Categoria</label>			
	<div class="controls">
		<g:select id="categoria" name="categoria.id" from="${com.fiuba.CategoriaMaterial.list()}" optionKey="id"
			value="${materialContenidoInstance?.categoria?.id}" class="many-to-one"/>
	</div>	
</div>
<div class="control-group">
	<label class="control-label" >Autor</label>			
	<div class="controls">
		<g:textField name="autor" value="${materialContenidoInstance?.autor}"/>
	</div>	
</div>
<div class="control-group">
	<label class="control-label" >Descripcion</label>			
	<div class="controls">
		<g:textField name="descripcion" value="${materialContenidoInstance?.descripcion}"/>
	</div>	
</div>
