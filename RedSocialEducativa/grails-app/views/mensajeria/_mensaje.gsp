<ul class="talk">
	<li
		style="border-bottom: 1px solid #D6D9E0; margin-bottom: 10px; padding-bottom: 10px;">
		<span class="name"> ${mensaje.emisor.nombres } ${mensaje.emisor.apellido }
	</span><br /> <span class="time"> Para: <g:each in="${keyReplyAll }"
				var='destinatario'>
				${destinatario }
				<br />
			</g:each>
	</span> <span class="time"> ${mensaje.fecha }
	</span> <span class="asunto"><h5>
				${mensaje.asunto }
			</h5></span>
		<div class="message">
			<msg:htmlMessage args="${mensaje.cuerpo.replaceAll('\r\n','<br/>')}" />
		</div>

	</li>
</ul>
