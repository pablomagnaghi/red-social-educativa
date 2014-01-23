<%@ page import="com.fiuba.PublicacionGeneral" %>


<g:if test="${respuestassss}">
	<div class="fieldcontain ${hasErrors(bean: publicacionGeneralInstance, field: 'publicacionPadre', 'error')} ">
		<label for="publicacionPadre">
			<g:message code="publicacionGeneral.publicacionPadre.label" default="Publicacion Padre" />
			
		</label>
		<g:select id="publicacionPadre" name="publicacionPadre.id" from="${com.fiuba.PublicacionGeneral.list()}" 
			optionKey="id" value="${publicacionGeneralInstance?.publicacionPadre?.id}" class="many-to-one" noSelection="['null': '']"/>
	</div>
	
	<div class="fieldcontain ${hasErrors(bean: publicacionGeneralInstance, field: 'respuesta', 'error')} required">
		<label for="respuesta">
			<g:message code="publicacionGeneral.respuesta.label" default="Respuesta" />
			<span class="required-indicator">*</span>
		</label>
		<g:select id="respuesta" name="respuesta.id" from="${com.fiuba.PublicacionGeneral.list()}" 
			optionKey="id" required="" value="${publicacionGeneralInstance?.respuesta?.id}" class="many-to-one"/>
	</div>
</g:if>

<g:if test="${usuario}">
	<div>
		<g:hiddenField name="responsable" value="${usuario}"/>
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


<div class="fieldcontain ${hasErrors(bean: publicacionGeneralInstance, field: 'titulo', 'error')} ">
	<label for="titulo">
		<g:message code="publicacionGeneral.titulo.label" default="Titulo" />
		
	</label>
	<g:textField name="titulo" value="${publicacionGeneralInstance?.titulo}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: publicacionGeneralInstance, field: 'contenido', 'error')} ">
	<label for="contenido">
		<g:message code="publicacionGeneral.contenido.label" default="Contenido" />
		
	</label>
	<g:textField name="contenido" value="${publicacionGeneralInstance?.contenido}"/>
</div>















