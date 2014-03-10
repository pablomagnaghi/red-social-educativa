<g:if test="${flash.message}">
	<div class="box-content alerts">
		<div class="alert alert-error">
			<button class="close" data-dismiss="alert" type="button"></button>
			<strong></strong> 
			${flash.message}
		</div>
	</div>    
</g:if>

<g:hasErrors bean="${cursoInstance}">
	<div class="box-content alerts">
		<div class="alert alert-error">
			<button class="close" data-dismiss="alert" type="button"></button>
			<strong>Revise el formulario</strong> 
		</div>
	</div>   
</g:hasErrors>

<!-- ASIGNATURA -->
<g:if test="${!hasErrors(bean: cursoInstance, field: 'asignatura', 'error')}">
	<div class="control-group">
		<label class="control-label" >Asignatura</label>			
		<div class="controls">
			<g:select id="asignatura" name="asignatura.id" from="${com.fiuba.Asignatura.list()}" 
				optionKey="id" value="${cursoInstance?.asignatura?.id}" class="many-to-one"/>
		</div>	
	</div>	
</g:if>
<g:else>
	<div class="control-group error">
		<label class="control-label" >Asignatura</label>			
		<div class="controls">
			<g:select id="asignatura" name="asignatura.id" from="${com.fiuba.Asignatura.list()}" 
				optionKey="id" required="" value="${cursoInstance?.asignatura?.id}" class="many-to-one"/>
			<span class="help-inline">
				<g:renderErrors bean="${cursoInstance}" as="list" field="asignatura"/>
			</span>
		</div>	
	</div>	 
</g:else>
<!-- NUMERO RELATIVO -->
<g:if test="${!hasErrors(bean: cursoInstance, field: 'nroRelativo', 'error')}">
	<div class="control-group">
		<label class="control-label" >Nro relativo</label>			
		<div class="controls">
			<g:textField name="nroRelativo" value="${cursoInstance?.nroRelativo}"/>
		</div>	
	</div>	
</g:if>
<g:else>
	<div class="control-group error">
		<label class="control-label" >Nro relativoe</label>			
		<div class="controls">
			<g:textField name="nroRelativo" value="${cursoInstance?.nroRelativo}"/>	
			<span class="help-inline">
				<g:renderErrors bean="${cursoInstance}" as="list" field="nroRelativo"/>
			</span>
		</div>	
	</div>	 
</g:else>
<!-- NOMBRE -->
<g:if test="${!hasErrors(bean: cursoInstance, field: 'nombre', 'error')}">
	<div class="control-group">
		<label class="control-label" >Nombre</label>			
		<div class="controls">
			<g:textField name="nombre" value="${cursoInstance?.nombre}"/>
		</div>	
	</div>	
</g:if>
<g:else>
	<div class="control-group error">
		<label class="control-label" >Nombre</label>			
		<div class="controls">
			<g:textField name="nombre" value="${cursoInstance?.nombre}"/>	
			<span class="help-inline">
				<g:renderErrors bean="${cursoInstance}" as="list" field="nombre"/>
			</span>
		</div>	
	</div>	 
</g:else>
<!-- CUATRIMESTRE DE DICTADO -->
<g:if test="${!hasErrors(bean: cursoInstance, field: 'cuatDict', 'error')}">
	<div class="control-group">
		<label class="control-label" >Cuat dict</label>			
		<div class="controls">
			<g:select name="cuatDict" from="${['1', '2', '1|2']}" value="${cursoInstance?.cuatDict}"/>
		</div>	
	</div>	
</g:if>
<g:else>
	<div class="control-group error">
		<label class="control-label" >Cuat dict</label>			
		<div class="controls">
			<g:select name="cuatDict" from="${['1', '2', '1|2']}" value="${cursoInstance?.cuatDict}"/>
			<span class="help-inline">
				<g:renderErrors bean="${cursoInstance}" as="list" field="Cuat dict"/>
			</span>
		</div>
	</div>	 
</g:else>
