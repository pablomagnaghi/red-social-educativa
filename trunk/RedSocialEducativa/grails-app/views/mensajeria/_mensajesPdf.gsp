<body>
<g:each in='${mensajes }' var="mensaje">
	<div>
		De:
		${mensaje.emisor.username}
		| asunto:
		${mensaje.asunto}
		| fecha:
		${mensaje.fecha }
	</div>
	<div >
		<div>
			De: <input  style="width: 90%"
				value="${mensaje.emisor.nombres} ${mensaje.emisor.apellido} &lt;${mensaje.emisor.email}&gt;" />
		</div>
		<div>
			Para: <input  style="width: 90%"
				value="${mensaje.para }" />
		</div>
		<div>
			Asunto: <input  style="width: 90%"
				value="${mensaje.asunto }" />
		</div>
		<div>
			<g:textArea  name="mensaje" value="${mensaje.cuerpo }" />
		</div>
	</div>
</g:each>
</body>