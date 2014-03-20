<div class="conversation">
	<div class="actions">
		<h6>Conversacion</h6>
	</div>
	<ul class="talk">
		<g:each in="${mensajes}" var="mensaje">
			<li style="border-bottom: 1px solid #D6D9E0;margin-bottom: 10px; padding-bottom: 10px;">
				<span class="name">
						${mensaje.emisor.nombres } ${mensaje.emisor.apellido }
				</span> <span class="time">
						${mensaje.fecha }
				</span> <span class="asunto"><h5>
							${mensaje.asunto }
						</h5>
				</span>
				<div class="message">
					<msg:htmlMessage args="${mensaje.cuerpo.replaceAll('\r\n','<br/>')}" />
				</div>
			</li>
		</g:each>
	</ul>
</div>