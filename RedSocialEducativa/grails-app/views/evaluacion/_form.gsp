<%@ page import="com.fiuba.Evaluacion" %>

<div> <g:hiddenField name="curso.id" value="${cursoId}"/></div>

<div class="fieldcontain ${hasErrors(bean: evaluacionInstance, field: 'aprendices', 'error')} ">
	<label for="aprendices">
		<g:message code="evaluacion.aprendices.label" default="Aprendices" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${evaluacionInstance?.aprendices?}" var="a">
    <li><g:link controller="evaluacionAprendiz" action="show" id="${a.id}">${a?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="evaluacionAprendiz" action="create" params="['evaluacion.id': evaluacionInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'evaluacionAprendiz.label', default: 'EvaluacionAprendiz')])}</g:link>
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
	<g:textField name="fecha" value="${evaluacionInstance?.fecha}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: evaluacionInstance, field: 'habilitada', 'error')} ">
	<label for="habilitada">
		<g:message code="evaluacion.habilitada.label" default="Habilitada" />
		
	</label>
	<g:checkBox name="habilitada" value="${evaluacionInstance?.habilitada}" />
</div>

<div class="fieldcontain ${hasErrors(bean: evaluacionInstance, field: 'horario', 'error')} ">
	<label for="horario">
		<g:message code="evaluacion.horario.label" default="Horario" />
		
	</label>
	<g:textField name="horario" value="${evaluacionInstance?.horario}"/>
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

