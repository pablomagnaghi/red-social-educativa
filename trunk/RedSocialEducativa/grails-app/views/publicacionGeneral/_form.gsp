<%@ page import="com.fiuba.PublicacionGeneral" %>

<g:if test="${params.pubInicialId}">
	<div><g:hiddenField name="titulo" value="${com.fiuba.PublicacionGeneral.get(params.pubInicialId).titulo}"/></div>
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

<div><g:hiddenField name="responsable" value="${usuario}"/></div>
<div><g:hiddenField name="dni" value="${usuario.username}"/></div>
<div><g:hiddenField name="foro.id" value="${com.fiuba.ForoGeneral.first().id}"/></div>

