<div class="span9" id="contenidoMensajes">
	<ul class="messagesList" carpetaSeleccionada="${carpetaSeleccionada }">
	<g:if test="${conversaciones!= null }">
		<g:if test="${!conversaciones.empty }">
			<g:each in="${conversaciones}" var="conversacion">
				<g:set var="mensaje" value="${conversacion.getLastMessage(varUsuarioService.usuarioActual())}" />
				<g:if test="${mensaje.leido == false && mensaje.emisor != varUsuarioService.usuarioActual()}">
					<li conversationId="${conversacion.id}" mensajeId="${mensaje.id}" class="unread draggable">
				</g:if>
				<g:else>
					<li conversationId="${conversacion.id}" mensajeId="${mensaje.id}" class="draggable">
				</g:else>
				<span style="display: inline-flex;">
					<input type="checkbox" id="${conversacion.id}"
					class="checkbox style-2 checkBoxConv" style="margin-bottom: 10px;">
				</span>
				<span class="from showConv">
					
					${mensaje.emisor.nombres}
					${mensaje.emisor.apellido}
				</span>
				<span class="title showConv" style="width: 49%;">
					${mensaje.asunto} - ${mensaje.getCuerpoResumido()}...
				</span>
				<span class="date showConv">
					${mensaje.getFechaYHora() }
				</span>
				</li>
			</g:each>
		</g:if>
		<g:else>
			<div style="margin-left: 275px; margin-top: 50px;">No hay
				mensajes para mostrar</div>
		</g:else>
	</g:if>
	<g:else>
		<g:if test="${mensajes != null && !mensajes.empty}">
			<g:if test="${'Borradores'.equals(carpetaSeleccionada)}">
				<g:each in="${mensajes}" var="mensaje">
				<li onclick="redactarBorrador('${mensaje.id}', 'Borradores')">
					<span style="display: inline-flex;">
						<input type="checkbox" id="${mensaje.id}"
							class="checkbox style-2">
					</span> 
					<span class="from">
						${mensaje.emisor.nombres}
						${mensaje.emisor.apellido}   <strong>[Borradores]</strong>
					</span>
					<span class="title" style="width: 49%;">
						${mensaje.asunto} - ${mensaje.getCuerpoResumido()}...
					</span>
					<span class="date">
						${mensaje.getFechaYHora() }
					</span>
				</li>
				</g:each>
			</g:if>
			<g:else>
				<g:each in="${mensajes}" var="mensaje">
				<li onclick="mostrarMensaje('${mensaje.id}')">
					<span style="display: inline-flex;">
						<input type="checkbox" id="${mensaje.id}"
							class="checkbox style-2">
					</span> 
					<span class="from">
						${mensaje.emisor.nombres}
						${mensaje.emisor.apellido}
					</span>
					<span class="title" style="width: 49%;">
						${mensaje.asunto} - ${mensaje.getCuerpoResumido()}...
					</span>
					<span class="date">
						${mensaje.getFechaYHora() }
					</span>
				</li>
				</g:each>
			</g:else>
		</g:if>
		<g:else>
			<div style="margin-left: 275px; margin-top: 50px;">No hay
				mensajes para mostrar</div>
		</g:else>
	</g:else>
	</ul>
</div>