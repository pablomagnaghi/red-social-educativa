<div class="row-fluid">
	<div class="box span12">
		<div class="box-header">
			<h2><i class="icon-plus"></i>Calificacion</h2>
		<div class="box-icon">
			<a href="#" class="btn-setting"><i class="icon-wrench"></i></a>
			<a href="#" class="btn-minimize"><i class="icon-chevron-up"></i></a>
			<a href="#" class="btn-close"><i class="icon-remove"></i></a>
		</div>
		</div>
		<h2>Curso: ${com.fiuba.Curso.get(params.cursoId)}</h2>
		<h2>Curso Id: ${params.cursoId}</h2>  			
		<h3>evaluacion: ${params.evaluacionId}</h3>			
								
		<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
		</g:if>    
		<g:hasErrors bean="${evaluacionAprendizInstance}">
			<ul class="errors" role="alert">
				<g:eachError bean="${evaluacionAprendizInstance}" var="error">
						<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
		</g:hasErrors>
		<div class="box-content">
			<g:form class="form-horizontal" action="guardarCalificacion" id="${params.id}" params="['cursoId': params.cursoId, 'evaluacionId': params.evaluacionId]"> 
				<g:hiddenField name="evaluacion.id" value="${evaluacionAprendizInstance.evaluacion.id}"/>
				<g:hiddenField name="aprendiz.id" value="${evaluacionAprendizInstance.aprendiz.id}"/>
				<fieldset>		
					<div class="control-group">
						<label class="control-label" >Nota</label>			
						<div class="controls">
							<g:field name="nota" type="number" min="0" value="${evaluacionAprendizInstance.nota}"/>
						</div>	
					</div>
					<div class="form-actions">
						<button type="submit" class="btn btn-primary">Calificar</button>
					</div>		    
				</fieldset>
		 	</g:form> 
		</div>
	</div>
</div>
