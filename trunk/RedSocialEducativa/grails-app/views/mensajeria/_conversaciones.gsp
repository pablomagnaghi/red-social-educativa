<g:if test="${!conversaciones.empty }">
	<g:each in="${conversaciones}" var="conversacion">
		<msg:showConversation data="${conversacion}" />
	</g:each>
</g:if>
<g:else>
	<div style="margin-left: 340px; margin-top: 50px;">
		No hay mensajes para mostrar
	</div>
</g:else>