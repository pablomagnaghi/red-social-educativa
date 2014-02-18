<div class="inbox-side-bar">
	<g:remoteLink action="redactarMensaje"
				update="[success:'contenidoMensajes']" onSuccess="when_ready();actualizar('redactar', null)" id="compose-mail"
				class="btn btn-primary btn-block">
				<span class="glyphicon glyphicon-envelope"></span>
				<strong>Redactar</strong>
	</g:remoteLink>
	
	<ul class="inbox-menu-lg" id="lista_carpetas">
		<g:render template="carpetas"
			model="['etiquetasCarpetas' : etiquetasCarpetas, 'seleccionada' : carpetaSeleccionada]"></g:render>
	</ul>
</div>
<div id="contenidoMensajes" class="table-wrap custom-scroll animated fast fadeInRight"
	style="height: 950px; opacity: 1;">
		<g:render template="conversaciones"
				model="['conversaciones' : conversaciones]"></g:render>
</div>
