<g:if test="${flash.message}">
	<div class="box-content alerts">
		<div class="alert alert-error">
			<button class="close" data-dismiss="alert" type="button"></button>
			<strong></strong> 
			${flash.message}
		</div>
	</div>    
</g:if>

<g:hasErrors bean="${asignaturaInstance}">
	<div class="box-content alerts">
		<div class="alert alert-error">
			<button class="close" data-dismiss="alert" type="button"></button>
			<strong>Revise el formulario</strong> 
		</div>
	</div>   
</g:hasErrors>

<!-- CODIGO -->
<g:if test="${!hasErrors(bean: asignaturaInstance, field: 'codigo', 'error')}">
	<div class="control-group">
		<label class="control-label" >Codigo</label>			
		<div class="controls">
			<g:textField name="codigo" value="${asignaturaInstance?.codigo}"/>
		</div>	
	</div>	
</g:if>
<g:else>
	<div class="control-group error">
		<label class="control-label" >Codigo</label>			
		<div class="controls">
			<g:textField name="codigo" value="${asignaturaInstance?.codigo}"/>
			<span class="help-inline">
				<g:renderErrors bean="${asignaturaInstance}" as="list" field="codigo"/>
			</span>
		</div>	
	</div>	 
</g:else>
<!-- NOMBRE -->
<g:if test="${!hasErrors(bean: asignaturaInstance, field: 'nombre', 'error')}">
	<div class="control-group">
		<label class="control-label" >Nombre</label>			
		<div class="controls">
			<g:textField name="nombre" value="${asignaturaInstance?.nombre}"/>
		</div>	
	</div>	
</g:if>
<g:else>
	<div class="control-group error">
		<label class="control-label" >Nombre</label>			
		<div class="controls">
			<g:textField name="nombre" value="${asignaturaInstance?.nombre}"/>	
			<span class="help-inline">
				<g:renderErrors bean="${asignaturaInstance}" as="list" field="nombre"/>
			</span>
		</div>	
	</div>	 
</g:else>
<!-- CREDITOS -->
<g:if test="${!hasErrors(bean: asignaturaInstance, field: 'creditos', 'error')}">
	<div class="control-group">
		<label class="control-label" >Creditos</label>			
		<div class="controls">
			<g:textField name="creditos" value="${asignaturaInstance?.creditos}"/>
		</div>	
	</div>	
</g:if>
<g:else>
	<div class="control-group error">
		<label class="control-label" >Creditos</label>			
		<div class="controls">
			<g:textField name="creditos" value="${asignaturaInstance?.creditos}"/>	
			<span class="help-inline">
				<g:renderErrors bean="${asignaturaInstance}" as="list" field="creditos"/>
			</span>
		</div>	
	</div>	 
</g:else>
<!-- CONTENIDOS MINIMOS -->
<g:if test="${!hasErrors(bean: asignaturaInstance, field: 'contenidosMinimos', 'error')}">
	<div class="control-group">
		<label class="control-label" >Contenidos Minimos</label>			
		<div class="controls">
			<g:textArea name="contenidosMinimos" value="${asignaturaInstance?.contenidosMinimos}" style='width: 90%; height: 200px;'/>
		</div>	
	</div>	
</g:if>
<g:else>
	<div class="control-group error">
		<label class="control-label" >Contenidos Minimos</label>			
		<div class="controls">
			<g:textArea name="contenidosMinimos" value="${asignaturaInstance?.contenidosMinimos}" style='width: 90%; height: 200px;'/>	
			<span class="help-inline">
				<g:renderErrors bean="${asignaturaInstance}" as="list" field="contenidosMinimos"/>
			</span>
		</div>
	</div>	 
</g:else>
