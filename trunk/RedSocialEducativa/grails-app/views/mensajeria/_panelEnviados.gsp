<div class="inbox-side-bar">
	<g:remoteLink action="redactarMensaje"
				update="[success:'contenidoMensajes']" onSuccess="when_ready();" id="compose-mail"
				class="btn btn-primary btn-block">
				<span class="glyphicon glyphicon-envelope"></span>
				<strong>Redactar</strong>
	</g:remoteLink>
	<h6>
		Carpetas <a class="pull-right txt-color-darken"
			data-original-title="Refresh" data-placement="right" title=""
			rel="tooltip" href="javascript:void(0);"><i class="fa fa-refresh"></i></a>
	</h6>
	<ul class="inbox-menu-lg" id="lista_carpetas">
		<g:render template="carpetas"
			model="['etiquetasCarpetas' : etiquetasCarpetas, 'seleccionada' : carpetaSeleccionada]"></g:render>
	</ul>
</div>
<div id="contenidoMensajes" class="table-wrap custom-scroll animated fast fadeInRight"
	style="height: 950px; opacity: 1;">
	<g:if test="${!mensajes.empty }">
	<table id="inbox-table" class="table table-striped table-hover">
		<tbody>
			<g:each in="${mensajes}" var="mensaje">
			<tr onclick="mostrarMensaje('${mensaje.id}')">
				<td class='inbox-table-icon'>
						<div class='checkbox'>
							<label> <input type='checkbox' class='checkbox style-2'> 
							<span></span> 
							</label> 
						</div>
					</td>
				<td class='inbox-data-from hidden-xs hidden-sm'> 
						<div>${mensaje.emisor.nombres} ${mensaje.emisor.apellido }</div> 
					</td>
				<td class='inbox-data-message'> 
						<div> 
							<span>${mensaje.asunto }</span>${mensaje.getCuerpoResumido() }  ...
						</div> 
					</td>
				<td class='inbox-data-date hidden-xs'> 
						<div>22.30</div> 
				</td>
			</g:each>
		</tbody>
	</table>
	</g:if>
	<g:else>
	<div style="margin-left: 340px; margin-top: 50px;">
		No hay mensajes para mostrar
	</div>
</g:else>
</div>
