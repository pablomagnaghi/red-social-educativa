<%@ page import="com.fiuba.PublicacionGeneral" %>

<g:if test="${usuario}">
	<div>
		<g:hiddenField name="responsable" value="${usuario}"/>
	</div>
	<div>
		<g:hiddenField name="dni" value="${usuario.username}"/>
	</div>
</g:if>
<g:else>
	<div class="fieldcontain ${hasErrors(bean: publicacionGeneralInstance, field: 'responsable', 'error')} ">
		<label for="responsable">
			<g:message code="publicacionGeneral.responsable.label" default="Responsable" />
			
		</label>
		<g:textField name="responsable" value="${publicacionGeneralInstance?.responsable}"/>
	</div>
</g:else>	

<div>
	<g:hiddenField name="fecha" value="${(new Date()).format("yyyy-mm-dd")}"/>
</div>

<div>
	<g:hiddenField name="hora" value="${(new Date()).getTimeString()}"/>
</div>

<div>
	<g:hiddenField name="foro.id" value="${com.fiuba.ForoGeneral.first().id}"/>
</div>

<g:if test="${pubInicialId}">
	<g:hiddenField name="titulo" value="${com.fiuba.PublicacionGeneral.get(pubInicialId).titulo}"/>
</g:if>
<g:else>
	<div class="fieldcontain ${hasErrors(bean: publicacionGeneralInstance, field: 'titulo', 'error')} ">
		<label for="titulo">
			<g:message code="publicacionGeneral.titulo.label" default="Titulo" />
			
		</label>
		<g:textField name="titulo" value="${publicacionGeneralInstance?.titulo}"/>
	</div>
</g:else>

<div class="fieldcontain ${hasErrors(bean: publicacionGeneralInstance, field: 'contenido', 'error')} ">
	<label for="contenido">
		<g:message code="publicacionGeneral.contenido.label" default="Contenido" />
		
	</label>
	<g:textField name="contenido" value="${publicacionGeneralInstance?.contenido}"/>
</div>

