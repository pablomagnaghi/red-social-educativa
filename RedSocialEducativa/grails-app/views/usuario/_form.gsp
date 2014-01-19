<%@ page import="com.fiuba.Usuario" %>

<div id="show-usuario" class="content scaffold-show" role="main">
	<h1><g:message code="Datos del usuario" args="[entityName]" /></h1>
	<g:if test="${flash.message}">
		<div class="message" role="status">${flash.message}</div>
	</g:if>

	<ol class="property-list usuario">
			
		<g:if test="${usuarioInstance?.username}">
			<li class="fieldcontain">
				<span id="username-label" class="property-label"><g:message code="usuario.username.label" default="Username" /></span>
						
				<span class="property-value" aria-labelledby="username-label"><g:fieldValue bean="${usuarioInstance}" field="username"/></span>
						
			</li>
		</g:if>
	
		<g:if test="${usuarioInstance?.apellido}">
			<li class="fieldcontain">
				<span id="apellido-label" class="property-label"><g:message code="usuario.apellido.label" default="Apellido" /></span> 
				
				<span class="property-value" aria-labelledby="apellido-label"> <g:fieldValue bean="${usuarioInstance}" field="apellido" /></span>
			</li>
		</g:if>
	
		<g:if test="${usuarioInstance?.nombres}">
			<li class="fieldcontain">
				<span id="nombres-label" class="property-label"><g:message code="usuario.nombres.label" default="Nombres" /></span> 
				<span class="property-value" aria-labelledby="nombres-label"><g:fieldValue bean="${usuarioInstance}" field="nombres" /></span>
			</li>
		</g:if>
	
		<g:if test="${usuarioInstance?.email}">
			<li class="fieldcontain">
				<span id="email-label" class="property-label"><g:message code="usuario.email.label" default="Email" /></span> 
				<span class="property-value" aria-labelledby="email-label"><g:fieldValue bean="${usuarioInstance}" field="email" /></span>
			</li>
		</g:if>
	
		<g:if test="${usuarioInstance?.fechaMemb}">
			<li class="fieldcontain">
				<span id="fechaMemb-label" class="property-label"><g:message code="usuario.fechaMemb.label" default="Fecha Memb" /></span> 
				<span class="property-value" aria-labelledby="fechaMemb-label"><g:formatDate date="${usuarioInstance?.fechaMemb}" /></span>
			</li>
		</g:if>
	
		<g:if test="${usuarioInstance?.fechaSolicitud}">
			<li class="fieldcontain">
				<span id="fechaSolicitud-label" class="property-label"><g:message code="usuario.fechaSolicitud.label" default="Fecha Solicitud" /></span> 
				<span class="property-value" aria-labelledby="fechaSolicitud-label"><g:formatDate date="${usuarioInstance?.fechaSolicitud}" /></span>
			</li>
		</g:if>
	
		<g:if test="${usuarioInstance?.legajo}">
			<li class="fieldcontain">
				<span id="legajo-label" class="property-label"><g:message code="usuario.legajo.label" default="Legajo" /></span> 
				<span class="property-value" aria-labelledby="legajo-label"><g:fieldValue bean="${usuarioInstance}" field="legajo" /></span>
			</li>
		</g:if>
	
		<g:if test="${usuarioInstance?.padron}">
			<li class="fieldcontain">
				<span id="padron-label" class="property-label"><g:message code="usuario.padron.label" default="Padron" /></span>
				<span class="property-value" aria-labelledby="padron-label"><g:fieldValue bean="${usuarioInstance}" field="padron"/></span>
			</li>
		</g:if>
	</ol>
</div>		