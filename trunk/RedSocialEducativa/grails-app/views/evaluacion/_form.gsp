<div class="fieldcontain ${hasErrors(bean: evaluacionInstance, field: 'aula', 'error')} ">
	<label for="aula">
		<g:message code="evaluacion.aula.label" default="Aula" />
		
	</label>
	<g:textField name="aula" value="${evaluacionInstance?.aula}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: evaluacionInstance, field: 'descripcion', 'error')} ">
	<label for="descripcion">
		<g:message code="evaluacion.descripcion.label" default="Descripcion" />
		
	</label>
	<g:textField name="descripcion" value="${evaluacionInstance?.descripcion}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: evaluacionInstance, field: 'fecha', 'error')} ">
	<label for="fecha">
		<g:message code="evaluacion.fecha.label" default="Fecha" />
		
	</label>
	<g:datePicker name="fechaDate" precision="minute"  value="${new Date()}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: evaluacionInstance, field: 'habilitada', 'error')} ">
	<label for="habilitada">
		<g:message code="evaluacion.habilitada.label" default="Habilitada" />
		
	</label>
	<g:checkBox name="habilitada" value="${evaluacionInstance?.habilitada}" />
</div>

<div class="fieldcontain ${hasErrors(bean: evaluacionInstance, field: 'obligatoria', 'error')} ">
	<label for="obligatoria">
		<g:message code="evaluacion.obligatoria.label" default="Obligatoria" />
		
	</label>
	<g:checkBox name="obligatoria" value="${evaluacionInstance?.obligatoria}" />
</div>

<div class="fieldcontain ${hasErrors(bean: evaluacionInstance, field: 'parcial', 'error')} ">
	<label for="parcial">
		<g:message code="evaluacion.parcial.label" default="Parcial" />
		
	</label>
	<g:checkBox name="parcial" value="${evaluacionInstance?.parcial}" />
</div>

<div><g:hiddenField name="curso.id" value="${params.cursoId}"/></div>
