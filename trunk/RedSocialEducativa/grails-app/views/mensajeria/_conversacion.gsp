<div class="table-wrap custom-scroll animated fast fadeInRight"
	style="height: 950px; margin-left: 0px; opacity: 1;">
	<div class="table-wrap custom-scroll animated fast fadeInRight"
		style="height: 950px; margin-left: 0px; opacity: 1;">
		<table id="inbox-table" class="table table-striped table-hover">
			<tbody>
				<g:each in="${mensajes}" var="mensaje">
					<g:if test="${carpeta.nombre != "Borradores" }">
						<tr onclick="mostrarMensajeEnConversacion('${mensaje.id}')" height="15px">
					</g:if>
					<g:else>
						<tr onclick="mostrarMensajeBorradores('${mensaje.id}')" height="15px">
					</g:else>
						<td class='inbox-data-from hidden-xs hidden-sm'>
							<div>
								${mensaje.emisor.nombres}
								${mensaje.emisor.apellido }
							</div>
						</td>
						<td class='inbox-data-message'>
							<div>
								<span>
									${mensaje.asunto }
								</span>
								${mensaje.getCuerpoResumido() }
								...
							</div>
						</td>
						<td class='inbox-data-date hidden-xs'>
							<div>22.30</div>
						</td>
					</tr>
					<tr>
						<td id="conversacion-${conversacionId}" colspan="3"></td>
					</tr>
				</g:each>
			</tbody>
		</table>
	</div>


	<script type="text/javascript">
		// DO NOT REMOVE : GLOBAL FUNCTIONS!
		//pageSetUp();

		// PAGE RELATED SCRIPTS

		$(".table-wrap [rel=tooltip]").tooltip();

		$(".replythis").click(
				function() {
					loadURL("ajax/email-reply.html",
							$('#inbox-content &gt; .table-wrap'));
				})
	</script>
</div>