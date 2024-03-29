<g:if test="${flash.message}">
	<div class="box-content alerts">
		<div class="alert alert-error"><button class="close" data-dismiss="alert" type="button"></button><strong></strong>${flash.message}</div>
	</div>
</g:if>
<g:hasErrors bean="${publicacionGeneralInstance}">
	<div class="box-content alerts">
		<div class="alert alert-error"><button class="close" data-dismiss="alert" type="button"></button><strong>Revise el formulario</strong></div>
	</div>
</g:hasErrors>

<g:hiddenField name="responsable" value="${usuario.nombres} ${usuario.apellido}"/>
<g:hiddenField name="dni" value="${usuario.dni}"/>
<g:hiddenField name="foro.id" value="${com.foro.ForoGeneral.first().id}"/>

<g:if test="${!params.pubInicialId || com.foro.PublicacionGeneral.get(params.pubInicialId)?.id == publicacionGeneralInstance.id}">
	<!-- TITULO -->
	<g:if test="${!hasErrors(bean: publicacionGeneralInstance, field: 'titulo', 'error')}">
		<div class="control-group">
			<label class="control-label" >Titulo</label>			
			<div class="controls"><g:textField name="titulo" value="${publicacionGeneralInstance?.titulo}" style='width: 62%' maxlength="64"/></div>	
		</div>		
	</g:if>
	<g:else>
		<div class="control-group error">
			<label class="control-label" >Titulo</label>			
			<div class="controls">
				<g:textField name="titulo" value="${publicacionGeneralInstance?.titulo}" style='width: 62%' maxlength="64"/>
				<span class="help-inline"><g:renderErrors bean="${publicacionGeneralInstance}" as="list" field="titulo"/></span>
			</div>	
		</div>	 
	</g:else>	
</g:if>
<g:else>
	<g:hiddenField name="titulo" value="${com.foro.PublicacionGeneral.get(params.pubInicialId).titulo}"/>	
</g:else>

<!-- CONTENIDO -->
<g:if test="${!hasErrors(bean: publicacionGeneralInstance, field: 'contenido', 'error')}">
	<div class="control-group">
		<label class="control-label" >Contenido</label>			
		<div class="controls"><g:textArea name="contenido" value="${publicacionGeneralInstance?.contenido}" style='width: 90%; height: 200px;'/></div>	
	</div>		
</g:if>
<g:else>
	<div class="control-group error">
		<label class="control-label" >Contenido</label>			
		<div class="controls">
			<g:textArea name="contenido" value="${publicacionGeneralInstance?.contenido}" style='width: 90%; height: 200px;'/>	
			<span class="help-inline"><g:renderErrors bean="${publicacionGeneralInstance}" as="list" field="contenido"/></span>
		</div>	
	</div>	 
</g:else>
