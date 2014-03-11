<g:if test="${flash.message}">
	<div class="box-content alerts">
		<div class="alert alert-error"><button class="close" data-dismiss="alert" type="button"></button><strong></strong>${flash.message}</div>
	</div>
</g:if>
<g:hasErrors bean="${noticiaRedInstance}">
	<div class="box-content alerts">
		<div class="alert alert-error"><button class="close" data-dismiss="alert" type="button"></button><strong>Revise el formulario</strong></div>
	</div>
</g:hasErrors>

<!-- TITULO -->
<g:if test="${!hasErrors(bean: noticiaRedInstance, field: 'titulo', 'error')}">
	<div class="control-group">
		<label class="control-label" >Titulo</label>			
		<div class="controls"><g:textField name="titulo" value="${noticiaRedInstance?.titulo}" style='width: 62%' maxlength="64"/></div>	
	</div>		
</g:if>
<g:else>
	<div class="control-group error">
		<label class="control-label" >Titulo</label>			
		<div class="controls">
			<g:textField name="titulo" value="${noticiaRedInstance?.titulo}" style='width: 62%' maxlength="64"/>
			<span class="help-inline"><g:renderErrors bean="${noticiaRedInstance}" as="list" field="titulo"/></span>
		</div>	
	</div>	 
</g:else>

<!-- VISIBILIDAD -->
<div class="control-group">
	<label class="control-label" >Visibilidad</label>			
	<div class="controls"><g:checkBox name="visibilidad" value="${noticiaRedInstance?.visibilidad}" /></div>	
</div>	

<!-- TEXTO -->
<g:if test="${!hasErrors(bean: noticiaRedInstance, field: 'texto', 'error')}">
	<div class="control-group">
		<label class="control-label" >Texto</label>			
		<div class="controls"><g:textArea name="texto" value="${noticiaRedInstance?.texto}" style='width: 90%; height: 200px;'/></div>	
	</div>		
</g:if>
<g:else>
	<div class="control-group error">
		<label class="control-label" >Texto</label>			
		<div class="controls">
			<g:textArea name="texto" value="${noticiaRedInstance?.texto}" style='width: 90%; height: 200px;'/>	
			<span class="help-inline"><g:renderErrors bean="${noticiaRedInstance}" as="list" field="texto"/></span>
		</div>	
	</div>	 
</g:else>
