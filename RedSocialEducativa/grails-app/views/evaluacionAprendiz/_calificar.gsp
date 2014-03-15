<div class="box-header">
	<h2><i class="icon-plus"></i>Recalificacion del aprendiz ${evaluacionAprendizInstance.aprendiz} en la evaluación ${evaluacionAprendizInstance.evaluacion}</h2>
	<div class="box-icon"><g:link action="mostrarEvaluacion" params="['cursoId': params.cursoId, 'evaluacionId': params.evaluacionId]">
		<i class="icon-table"></i></g:link></div>
</div>
<g:if test="${flash.message}">
	<div class="box-content alerts">
		<div class="alert alert-error"><button class="close" data-dismiss="alert" type="button"></button><strong>${flash.message}</strong></div>
	</div>
</g:if>
<g:hasErrors bean="${evaluacionAprendizInstance}">
	<div class="box-content alerts">
		<div class="alert alert-error"><button class="close" data-dismiss="alert" type="button"></button><strong>Revise el formulario</strong></div>
		
	</div>
</g:hasErrors>
<div class="box-content">
	<g:form class="form-horizontal" action="guardarCalificacion" id="${params.id}" 
		params="['cursoId': params.cursoId, 'evaluacionId': params.evaluacionId, 'aprendizId': params.aprendizId]"> 
		<g:hiddenField name="evaluacion.id" value="${evaluacionAprendizInstance.evaluacion.id}"/>
		<g:hiddenField name="aprendiz.id" value="${evaluacionAprendizInstance.aprendiz.id}"/>
		<fieldset>		
			<div class="control-group">
				<label class="control-label" >Nota</label>			
				<div class="controls">
					<g:field name="nota" type="number" min="0" max="10" value="${evaluacionAprendizInstance.nota}" style="width: 10%"/>			
				</div>	
			</div>
			<div class="form-actions">
				<button type="submit" class="btn btn-primary">Recalificar</button>
			</div>		    
		</fieldset>
	</g:form> 
</div>
