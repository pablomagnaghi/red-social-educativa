<g:if test="${flash.message}">
	<div class="box-content alerts">
		<div class="alert alert-error"><button class="close" data-dismiss="alert" type="button"></button><strong></strong>${flash.message}</div>
	</div>
</g:if>
<g:hasErrors bean="${materialCursoInstance}">
	<div class="box-content alerts">
		<div class="alert alert-error"><button class="close" data-dismiss="alert" type="button"></button><strong>Revise el formulario</strong></div>
	</div>
</g:hasErrors>

<!-- TITULO -->
<g:if test="${!hasErrors(bean: materialCursoInstance, field: 'titulo', 'error')}">
	<div class="control-group">
		<label class="control-label" >Titulo</label>			
		<div class="controls"><g:textField name="titulo" value="${materialCursoInstance?.titulo}" style='width: 62%' maxlength="64"/></div>	
	</div>		
</g:if>
<g:else>
	<div class="control-group error">
		<label class="control-label" >Titulo</label>			
		<div class="controls">
			<g:textField name="titulo" value="${materialCursoInstance?.titulo}" style='width: 62%' maxlength="64"/>
			<span class="help-inline"><g:renderErrors bean="${materialCursoInstance}" as="list" field="titulo"/></span>
		</div>	
	</div>	 
</g:else>

<!-- CATEGORIA -->
<div class="control-group">
	<label class="control-label" >Categoria</label>			
	<div class="controls">
		<g:select id="categoria" name="categoria.id" from="${com.material.CategoriaMaterial.list()}" optionKey="id" 
			value="${materialCursoInstance?.categoria?.id}" class="many-to-one"/>
	</div>	
</div>	

<!-- AUTOR -->
<div class="control-group">
	<label class="control-label" >Autor</label>			
	<div class="controls">
		<g:textField name="autor" value="${materialCursoInstance?.autor}" style='width: 62%' maxlength="64"/>
	</div>	
</div>		

<!-- DESCRIPCION -->
<g:if test="${!hasErrors(bean: materialCursoInstance, field: 'descripcion', 'error')}">
	<div class="control-group">
		<label class="control-label" >Descripcion</label>			
		<div class="controls"><g:textArea name="descripcion" value="${materialCursoInstance?.descripcion}" style='width: 90%; height: 200px;'/></div>	
	</div>		
</g:if>
<g:else>
	<div class="control-group error">
		<label class="control-label" >Descripcion</label>			
		<div class="controls">
			<g:textArea name="Descripcion" value="${materialCursoInstance?.descripcion}" style='width: 90%; height: 200px;'/>	
			<span class="help-inline"><g:renderErrors bean="${materialCursoInstance}" as="list" field="descripcion"/></span>
		</div>	
	</div>	 
</g:else>
			
<g:hiddenField name="curso.id" value="${params.cursoId}"/>
<g:hiddenField name="responsable" value="${mediador.usuario.nombres} ${mediador.usuario.apellido}-${mediador?.jerarquia}"/>