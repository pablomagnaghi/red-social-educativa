<g:if test="${flash.message}">
	<div class="box-content alerts">
		<div class="alert alert-error"><button class="close" data-dismiss="alert" type="button"></button><strong>${flash.message}</strong></div>
	</div>
</g:if>
<g:hasErrors bean="${actividadInstance}">
	<div class="box-content alerts">
		<div class="alert alert-error"><button class="close" data-dismiss="alert" type="button"></button><strong>Revise el formulario</strong></div>										
	</div>
</g:hasErrors>

<!-- TITULO -->
<g:if test="${!hasErrors(bean:actividadInstance, field: 'titulo', 'error')}">
	<div class="control-group">
		<label class="control-label">Titulo</label>			
		<div class="controls"><g:textField name="titulo" value="${actividadInstance?.titulo}" style='width: 62%' maxlength="64"/></div>	
	</div>		
</g:if>
<g:else>
	<div class="control-group error">
		<label class="control-label" >Titulo</label>			
		<div class="controls">
			<g:textField name="titulo" value="${actividadInstance?.titulo}" style='width: 62%' maxlength="64"/>
			<span class="help-inline"><g:renderErrors bean="${actividadInstance}" as="list" field="titulo"/></span>
		</div>	
	</div>	 
</g:else>
<!-- CATEGORIA -->
<div class="control-group">
	<label class="control-label">Categoria</label>			
	<div class="controls"><g:select id="categoria" name="categoria.id" from="${com.cursado.CategoriaActividad.list()}" optionKey="id"
		value="${actividadInstance?.categoria?.id}" class="many-to-one"/></div>	
</div>		
<!-- FECHA FINALIZACION -->
<div class="control-group">
	<label class="control-label" >Fecha finalizacion</label>			
	<div class="controls">
		<g:datePicker name="fechaFinalizacionDate" precision="day" value="${new Date()}"  />
	</div>	
</div>
<!-- GRUPAL -->			            	
<div class="control-group">
	<label class="control-label" >Grupal</label>			
	<div class="controls">
		<g:checkBox name="grupal" value="${actividadInstance?.grupal}" />
	</div>	
</div>
<!-- EVALUABLE -->		
<div class="control-group">
	<label class="control-label" >Evaluable</label>			
	<div class="controls">
		<g:checkBox name="evaluable" value="${actividadInstance?.evaluable}" />
	</div>	
</div>
<!-- VISIBILIDAD -->
<div class="control-group">
	<label class="control-label" >Visibilidad</label>			
	<div class="controls">
		<g:checkBox name="visibilidad" value="${actividadInstance?.visibilidad}" />
	</div>	
</div>
<!-- OBJETIVO -->
<g:if test="${!hasErrors(bean:actividadInstance, field: 'objetivo', 'error')}">
	<div class="control-group">
		<label class="control-label">Objetivo</label>			
		<div class="controls"><g:textArea name="objetivo" value="${actividadInstance?.objetivo}" style='width: 90%; height: 200px;'/></div>	
	</div>		
</g:if>
<g:else>
	<div class="control-group error">
		<label class="control-label">Objetivo</label>			
		<div class="controls">
			<g:textArea name="objetivo" value="${actividadInstance?.objetivo}" style='width: 90%; height: 200px;'/>
			<span class="help-inline"><g:renderErrors bean="${actividadInstance}" as="list" field="objetivo"/></span>
		</div>	
	</div>	 
</g:else>

<g:hiddenField name="cuatrimestre.id" value="${params.cuatrimestreId}"/>
