<g:if test="${flash.message}">
	<div class="box-content alerts">
		<div class="alert alert-error"><button class="close" data-dismiss="alert" type="button"></button><strong></strong>${flash.message}</div>
	</div>
</g:if>
<g:hasErrors bean="${materialTemaInstance}">
	<div class="box-content alerts">
		<div class="alert alert-error"><button class="close" data-dismiss="alert" type="button"></button><strong>Revise el formulario</strong></div>
	</div>
</g:hasErrors>

<!-- TITULO -->
<g:if test="${!hasErrors(bean: materialTemaInstance, field: 'titulo', 'error')}">
	<div class="control-group">
		<label class="control-label" >Titulo</label>			
		<div class="controls"><g:textField name="titulo" value="${materialTemaInstance?.titulo}" style='width: 62%' maxlength="64"/></div>	
	</div>		
</g:if>
<g:else>
	<div class="control-group error">
		<label class="control-label" >Titulo</label>			
		<div class="controls">
			<g:textField name="titulo" value="${materialTemaInstance?.titulo}" style='width: 62%' maxlength="64"/>
			<span class="help-inline"><g:renderErrors bean="${materialTemaInstance}" as="list" field="titulo"/></span>
		</div>	
	</div>	 
</g:else>

<!-- CATEGORIA -->
<div class="control-group">
	<label class="control-label" >Categoria</label>			
	<div class="controls">
		<g:select id="categoria" name="categoria.id" from="${com.material.CategoriaMaterial.list()}" optionKey="id" 
			value="${materialTemaInstance?.categoria?.id}" class="many-to-one"/>
	</div>	
</div>	

<!-- AUTOR -->
<div class="control-group">
	<label class="control-label" >Autor</label>			
	<div class="controls">
		<g:textField name="autor" value="${materialTemaInstance?.autor}" style='width: 62%' maxlength="64"/>
	</div>	
</div>		

<!-- DESCRIPCION -->
<g:if test="${!hasErrors(bean: materialTemaInstance, field: 'descripcion', 'error')}">
	<div class="control-group">
		<label class="control-label" >Descripcion</label>			
		<div class="controls"><g:textArea name="descripcion" value="${materialTemaInstance?.descripcion}" style='width: 90%; height: 200px;'/></div>	
	</div>		
</g:if>
<g:else>
	<div class="control-group error">
		<label class="control-label" >Descripcion</label>			
		<div class="controls">
			<g:textArea name="Descripcion" value="${materialTemaInstance?.descripcion}" style='width: 90%; height: 200px;'/>	
			<span class="help-inline"><g:renderErrors bean="${materialTemaInstance}" as="list" field="descripcion"/></span>
		</div>	
	</div>	 
</g:else>
			
<g:hiddenField name="tema.id" value="${params.temaId}"/>
<g:hiddenField name="responsable" value="${mediador.usuario.nombres} ${mediador.usuario.apellido}-${mediador.jerarquia}"/>