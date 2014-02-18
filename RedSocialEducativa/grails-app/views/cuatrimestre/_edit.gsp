<%@ page import="com.fiuba.Cuatrimestre" %>



<div class="fieldcontain ${hasErrors(bean: cuatrimestreInstance, field: 'actividades', 'error')} ">
	<label for="actividades">
		<g:message code="cuatrimestre.actividades.label" default="Actividades" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${cuatrimestreInstance?.actividades?}" var="a">
    <li><g:link controller="actividad" action="show" id="${a.id}">${a?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="actividad" action="create" params="['cuatrimestre.id': cuatrimestreInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'actividad.label', default: 'Actividad')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: cuatrimestreInstance, field: 'anio', 'error')} required">
	<label for="anio">
		<g:message code="cuatrimestre.anio.label" default="Anio" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="anio" type="number" value="${cuatrimestreInstance.anio}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: cuatrimestreInstance, field: 'aprendices', 'error')} ">
	<label for="aprendices">
		<g:message code="cuatrimestre.aprendices.label" default="Aprendices" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${cuatrimestreInstance?.aprendices?}" var="a">
    <li><g:link controller="aprendiz" action="show" id="${a.id}">${a?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="aprendiz" action="create" params="['cuatrimestre.id': cuatrimestreInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'aprendiz.label', default: 'Aprendiz')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: cuatrimestreInstance, field: 'curso', 'error')} required">
	<label for="curso">
		<g:message code="cuatrimestre.curso.label" default="Curso" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="curso" name="curso.id" from="${com.fiuba.Curso.list()}" optionKey="id" required="" value="${cuatrimestreInstance?.curso?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: cuatrimestreInstance, field: 'foro', 'error')} required">
	<label for="foro">
		<g:message code="cuatrimestre.foro.label" default="Foro" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="foro" name="foro.id" from="${com.fiuba.ForoCurso.list()}" optionKey="id" required="" value="${cuatrimestreInstance?.foro?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: cuatrimestreInstance, field: 'grupos', 'error')} ">
	<label for="grupos">
		<g:message code="cuatrimestre.grupos.label" default="Grupos" />
		
	</label>
	
