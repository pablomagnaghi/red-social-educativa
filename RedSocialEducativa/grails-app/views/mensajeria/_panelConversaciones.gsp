<div class="table-wrap custom-scroll animated fast fadeInRight"
	id="contenidoMensajes" style="height: 950px; opacity: 1;">
	<table id="inbox-table" class="table table-striped table-hover">
		<g:if test="${conversaciones!= null }">
			<tbody id="listaConversaciones">
				<g:if test="${!conversaciones.empty }">
					<g:each in="${conversaciones}" var="conversacion">
						<g:set var="mensaje" value="${conversacion.mensajes.last()}" />
						<g:if test="${mensaje.leido == false}">
							<tr conversationId="${conversacion.id}" class="unread draggable">
						</g:if>
						<g:else>
							<tr conversationId="${conversacion.id}" class="draggable">
						</g:else>
						<td class="inbox-table-icon">
							<div class="checkbox">
								<label> <input type="checkbox" id="${conversacion.id}"
									class="checkbox style-2 checkBoxConv"> <span></span>
								</label>
							</div>
						</td>
						<td class="inbox-data-from hidden-xs hidden-sm showConv">
							<div>
								${mensaje.emisor.nombres}

								${mensaje.emisor.apellido}
							</div>
						<td class="inbox-data-message showConv">
							<div>
								<span> ${mensaje.asunto}
								</span>
								${mensaje.getCuerpoResumido()}
								...
							</div>
						</td>
						<td class="inbox-data-date hidden-xs showConv">
							<div>22.30</div>
						</td>
						</tr>
					</g:each>
				</g:if>
				<g:else>
					<div style="margin-left: 340px; margin-top: 50px;">No hay
						mensajes para mostrar</div>
				</g:else>
			</tbody>
		</g:if>
		<g:else>
			<g:if test="${!mensajes.empty}">
				<table id="inbox-table" class="table table-striped table-hover">
					<tbody>
						<g:each in="${mensajes}" var="mensaje">
							<tr onclick="mostrarMensaje('${mensaje.id}')">
								<td class='inbox-table-icon'>
									<div class='checkbox'>
										<label> <input type='checkbox'
											class='checkbox style-2'> <span></span>
										</label>
									</div>
								</td>
								<td class='inbox-data-from hidden-xs hidden-sm'>
									<div>
										${mensaje.emisor.nombres}
										${mensaje.emisor.apellido }
									</div>
								</td>
								<td class='inbox-data-message'>
									<div>
										<span> ${mensaje.asunto }
										</span>
										${mensaje.getCuerpoResumido() }
										...
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
				<div style="margin-left: 340px; margin-top: 50px;">No hay
					mensajes para mostrar</div>
			</g:else>
		</g:else>
	</table>

</div>