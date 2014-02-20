<%@ page import="com.fiuba.Evaluacion" %>

<div class="fieldcontain ${hasErrors(bean: evaluacionInstance, field: 'aprendices', 'error')} ">
	<label for="aprendices">
		<g:message code="evaluacion.aprendices.label" default="Aprendices" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${evaluacionInstance?.aprendices?}" var="a">
    <li><g:link controller="evaluacionAprendiz" action="show" id="${a.id}" 
		params="['cursoId': params.cursoId, 'evaluacionId': evaluacionInstance?.id]">${a?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="evaluacionAprendiz" action="create" params="['cursoId': params.cursoId, 'evaluacionId': evaluacionInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'evaluacionAprendiz.label', default: 'EvaluacionAprendiz')])}</g:link>
</li>
</ul>

</div>

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

<div><g:hiddenField name="curso.id" value="${params.cursoId}"/></div>

