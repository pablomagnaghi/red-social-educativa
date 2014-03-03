<div class="control-group">
	<label class="control-label" >Autor</label>			
	<div class="controls">
		<g:textField name="autor" value="${materialCursoInstance?.autor}"/>
	</div>	
</div>								
<div class="control-group">
	<label class="control-label" >Categoria</label>			
	<div class="controls">
		<g:select id="categoria" name="categoria.id" from="${com.fiuba.CategoriaMaterial.list()}" optionKey="id" 
			value="${materialCursoInstance?.categoria?.id}" class="many-to-one"/>
	</div>	
</div>			
<div class="control-group">
	<label class="control-label" >Descripcion</label>			
	<div class="controls">
		<g:textField name="descripcion" value="${materialCursoInstance?.descripcion}"/>
	</div>	
</div>				
<g:hiddenField name="curso.id" value="${params.cursoId}"/>
