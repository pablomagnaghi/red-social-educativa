<div class="conversation">
	<div class="actions">
		<h6>Conversacion</h6>
	</div>
	<ul class="talk">
	<g:each in="${mensajes}" var="mensaje">
		<li><!-- <img alt="avatar" src="img/avatar.jpg" class="avatar"> -->
		<span class="quickMenu">
			<a class="glyphicons share" href="#" onclick="$('#form_reply_${mensaje.id}').show()"><i></i></a>
		</span>
		<span class="name">${mensaje.emisor.nombres } ${mensaje.emisor.apellido }</span> <span class="time">${mensaje.fecha }</span>
		<span class="asunto"><h5>${mensaje.asunto }</h5></span>
		<div class="message">${mensaje.cuerpo }</div>
		<form action="" method="post" class="replyForm" style="display:none" id="form_reply_${mensaje.id }">
			<fieldset>
				<textarea placeholder="Click here to reply" rows="12" name="body"
					id="message" class="input-xlarge span12" tabindex="3"
					style="overflow: hidden; word-wrap: break-word; resize: horizontal; height: 263px;"></textarea>
				<div class="actions">
					<button class="btn btn-success" type="submit" tabindex="3">Enviar Mensaje</button>
				</div>
			</fieldset>
		</form>
		</li>
	</g:each>
	</ul>
</div>
