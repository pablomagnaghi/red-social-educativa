<%@ page import="com.fiuba.PublicacionCurso" %>

<g:if test="${usuario}">
	<div><g:hiddenField name="responsable" value="${usuario}"/></div>
	<div><g:hiddenField name="dni" value="${usuario.username}"/></div>
</g:if>
<g:else>
	<div class="fieldcontain ${hasErrors(bean: publicacionCursoInstance, field: 'responsable', 'error')} ">
		<label for="responsable">
			<g:message code="publicacionCurso.responsable.label" default="Responsable" />
			
		</label>
		<g:textField name="responsable" value="${publicacionCursoInstance?.responsable}"/>
	</div>
</g:else>	

<g:if test="${params.pubInicialId}">
	<g:hiddenField name="titulo" value="${com.fiuba.PublicacionCurso.get(pubInicialId).titulo}"/>
</g:if>
<g:else>
	<div class="fieldcontain ${hasErrors(bean: publicacionCursoInstance, field: 'titulo', 'error')} ">
		<label for="titulo">
			<g:message code="publicacionCurso.titulo.label" default="Titulo" />
			
		</label>
		<g:textField name="titulo" value="${publicacionCursoInstance?.titulo}"/>
	</div>
</g:else>

<div class="fieldcontain ${hasErrors(bean: publicacionCursoInstance, field: 'contenido', 'error')} ">
	<label for="contenido">
		<g:message code="publicacionCurso.contenido.label" default="Contenido" />
		
	</label>
	<g:textField name="contenido" value="${publicacionCursoInstance?.contenido}"/>
</div>

<div><g:hiddenField name="fecha" value="${(new Date()).format(com.fiuba.Utilidades.FORMATO_FECHA)}"/></div>
<div><g:hiddenField name="hora" value="${(new Date()).getTimeString()}"/></div>
<div><g:hiddenField name="foro.id" value="${com.fiuba.ForoCurso.findByCuatrimestre(com.fiuba.Cuatrimestre.get(params.cuatrimestreId)).id}"/></div>