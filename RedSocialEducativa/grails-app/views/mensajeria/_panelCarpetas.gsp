<div class="inbox-side-bar">
	<g:remoteLink action="redactarMensaje"
		update="[success:'contenidoMensajes']"
		onSuccess="when_ready();actualizar('redactar', null)"
		id="compose-mail" class="btn btn-primary btn-block">
		<span class="glyphicon glyphicon-envelope"></span>
		<strong>Redactar</strong>
	</g:remoteLink>
	<ul class="inbox-menu-lg" id="lista_carpetas">
		<g:each in="${etiquetasCarpetas}" var="carpeta">
			<g:if test="${carpetaSeleccionada == carpeta.key}">
				<li class="active li_clickeable droppable selectedFolder"
					id="${carpeta.key}"><g:link class="inbox-load"
						action="mostrarMensajes" params='[nombreCarpeta: "${carpeta.key}"]'>
						${carpeta.key}
						<g:if test="${carpeta.value > 0}">
							<span class="badge pull-right inbox-badge">${carpeta.value}</span>
						</g:if>
					</g:link>
				</li>
			</g:if>
			<g:else>
				<li class="li_clickeable droppable" id="${carpeta.key}"><g:link
						action="mostrarMensajes" params='[nombreCarpeta: "${carpeta.key}"]'>
						${carpeta.key}
						<g:if test="${carpeta.value > 0}">
							<span class="badge pull-right inbox-badge">${carpeta.value}</span>
						</g:if>
					</g:link>
				</li>
			</g:else>
		</g:each>
		<h6>
			Agregar Carpeta <a class="pull-right txt-color-darken"
				data-original-title="Add Another" data-placement="right" title=""
				rel="tooltip" href="javascript:void(0);" id="nueva_carpeta"><i
				class="fa fa-plus"></i></a>
		</h6>
	</ul>
</div>

<div id="div_nueva_carpeta" class="center_div_carpeta"
	style="display: none">
	<div id="wid-id-0" class="jarviswidget jarviswidget-sortable"
		role="widget">

		<header role="heading">
			<div class="jarviswidget-ctrls" role="menu">
				<a data-placement="bottom" title=""
					onclick="cerrar_form_nueva_carpeta()" rel="tooltip"
					class="button-icon jarviswidget-delete-btn"
					href="javascript:void(0);" data-original-title="Delete"> <i
					class="fa fa-times"></i>
				</a>
			</div>

			<h2>Crear Carpeta</h2>

			<span class="jarviswidget-loader" style="display: none;"> <i
				class="fa fa-refresh fa-spin"></i>
			</span>
		</header>

		<!-- widget div-->
		<div role="content" style="height: 160px">

			<!-- widget edit box -->
			<form action="nuevaCarpeta" onsubmit="return validarNombre()">

				<div class="jarviswidget-editbox" style="display: block;">
					<!-- This area used as dropdown edit box -->
					<input type="text" name="nombre" id="nombre" class="form-control">
					<span class="note">Nombre de carpeta</span>
					<div id="carpetaNuevaError" class="message" role="status"
						style="display: none">No se ha especificado ningún nombre.</div>
				</div>
				<!-- end widget edit box -->
				<div style="padding-left: 57px;">
					<button class="btn btn-default btn-lg"
						onclick="cerrar_form_nueva_carpeta()">Cancelar</button>
					<button type="submit" name="Guardar" class="btn btn-default btn-lg"
						id="guardar_carpeta">Guardar</button>
				</div>
			</form>
			<!-- end widget content -->

		</div>
		<!-- end widget div -->

	</div>
</div>
