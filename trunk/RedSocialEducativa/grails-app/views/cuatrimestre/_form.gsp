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
	
<ul class="one-to-many">
<g:each in="${cuatrimestreInstance?.grupos?}" var="g">
    <li><g:link controller="grupoCurso" action="show" id="${g.id}">${g?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="grupoCurso" action="create" params="['cuatrimestre.id': cuatrimestreInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'grupoCurso.label', default: 'GrupoCurso')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: cuatrimestreInstance, field: 'habGrupos', 'error')} ">
	<label for="habGrupos">
		<g:message code="cuatrimestre.habGrupos.label" default="Hab Grupos" />
		
	</label>
	<g:checkBox name="habGrupos" value="${cuatrimestreInstance?.habGrupos}" />
</div>

<div class="fieldcontain ${hasErrors(bean: cuatrimestreInstance, field: 'noticiasCurso', 'error')} ">
	<label for="noticiasCurso">
		<g:message code="cuatrimestre.noticiasCurso.label" default="Noticias Curso" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${cuatrimestreInstance?.noticiasCurso?}" var="n">
    <li><g:link controller="noticiaCurso" action="show" id="${n.id}">${n?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="noticiaCurso" action="create" params="['cuatrimestre.id': cuatrimestreInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'noticiaCurso.label', default: 'NoticiaCurso')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: cuatrimestreInstance, field: 'nroUltGrupo', 'error')} required">
	<label for="nroUltGrupo">
		<g:message code="cuatrimestre.nroUltGrupo.label" default="Nro Ult Grupo" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="nroUltGrupo" type="number" value="${cuatrimestreInstance.nroUltGrupo}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: cuatrimestreInstance, field: 'numero', 'error')} required">
	<label for="numero">
		<g:message code="cuatrimestre.numero.label" default="Numero" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="numero" type="number" value="${cuatrimestreInstance.numero}" required=""/>
</div>

