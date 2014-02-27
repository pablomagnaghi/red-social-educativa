<div class="span7">
	<ul class="messagesList">
	<g:if test="${conversaciones!= null }">
		<g:if test="${!conversaciones.empty }">
			<g:each in="${conversaciones}" var="conversacion">
				<g:set var="mensaje" value="${conversacion.mensajes.last()}" />
				<g:if test="${mensaje.leido == false}">
					<li conversationId="${conversacion.id}" class="unread draggable">
				</g:if>
				<g:else>
					<li conversationId="${conversacion.id}" class="draggable">
				</g:else>
				<span class="from">
					<input type="checkbox" id="${conversacion.id}"
						class="checkbox style-2 checkBoxConv">
					
					${mensaje.emisor.nombres}
					${mensaje.emisor.apellido}
				</span>
				<span class="title" style="width: 54%;">
					${mensaje.asunto} - ${mensaje.getCuerpoResumido()}...
				</span>
				<span class="date">
					22.30
				</span>
				</li>
			</g:each>
		</g:if>
	</g:if>
	<g:else>
		<g:if test="${!mensajes.empty}">
			<g:each in="${mensajes}" var="mensaje">
			<li onclick="mostrarMensaje('${mensaje.id}')">
				<span class="from">
					<input type="checkbox" id="${mensaje.id}"
						class="checkbox style-2"> 
					${mensaje.emisor.nombres}
					${mensaje.emisor.apellido}
				</span>
				<span class="title" style="width: 54%;">
					${mensaje.asunto} - ${mensaje.getCuerpoResumido()}...
				</span>
				<span class="date">
					22.30
				</span>
			</li>
			</g:each>
		</g:if>
		<g:else>
			<div style="margin-left: 340px; margin-top: 50px;">No hay
				mensajes para mostrar</div>
		</g:else>
	</g:else>
	</ul>
</div>