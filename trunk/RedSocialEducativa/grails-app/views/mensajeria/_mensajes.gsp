
<div class="body">
	<g:each in='${mensajes }' var="mensaje">
		<div id="encabezadoMensaje-" ${mensaje.id } class="encabezado"
			onclick="desplegarMensaje('${mensaje.id}')">
			De:
			${mensaje.emisor.username}
			| asunto:
			${mensaje.asunto}
			| fecha:
			${mensaje.fecha }
		</div>
		<div id="cuerpoMensaje-${mensaje.id}" style="display: none">
			<div>
				De: <input id="de-${mensaje.id}" disabled style="width: 90%"
					value="${mensaje.emisor.nombres} ${mensaje.emisor.apellido} <${mensaje.emisor.email}>" />
			</div>
			<div>
				Para: <input id="para-${mensaje.id}" disabled style="width: 90%"
					value="${mensaje.para }" />
			</div>
			<div>
				Asunto: <input id="asunto-${mensaje.id}" disabled style="width: 90%"
					value="${mensaje.asunto }" />
			</div>
			<div>
				<g:textArea disabled="true" name="mensaje"
					id="cuerpo-${mensaje.id }" value="${mensaje.cuerpo }" />
			</div>
			<fieldset class="buttons" id="botones-respuesta-${mensaje.id }">
				<button id="responder-${mensaje.id}"
					onclick="responderMensaje('${mensaje.id}')">Responder</button>
				<button id="responderATodos-${mensaje.id}"
					onclick="responderATodos('${mensaje.id}')">Responder a
					Todos</button>
				<button id="reenviar-${mensaje.id}"
					onclick="reenviarMensaje('${mensaje.id}')">Reenviar
					Mensaje</button>
			</fieldset>
			<div id="respuestaMensaje-${mensaje.id }" style="display: none">
				<g:formRemote name="form-respuesta-${mensaje.id }"
					url="[action: 'responderMensaje', controller: 'mensajeria']"
					onSuccess="mensajeEnviado('${mensaje.id}')">
					<input name="respuestaId" type="hidden" value="${mensaje.id }" />
					<div>
						Para: <input name="respuestaPara"
							id="respuesta-para-${mensaje.id}" style="width: 90%" value="" />
					</div>
					<div>
						Asunto: <input name="respuestaAsunto"
							id="respuesta-asunto-${mensaje.id}" style="width: 90%" value="" />
					</div>
					<div>
						<g:textArea name="respuestaCuerpo"
							id="respuesta-mensaje-${mensaje.id }" value="" />
					</div>
					<g:submitButton name="Enviar" id="enviar_respuesta" />
					<button id="cancelarRespuesta-${mensaje.id}"
						onclick="cancelarRespuesta('${mensaje.id}')">Cancelar</button>
				</g:formRemote>
			</div>
		</div>
	</g:each>

</div>