<div class="span" style="width: 200px; margin-left: 0px; margin-right: 14px;">
	<g:remoteLink action="redactarMensaje"
		update="[success:'contenidoMensajes']"
		onSuccess="when_ready();actualizar('redactar', null)"
		id="compose-mail" class="btn btn-primary btn-block">
		<span class="glyphicon glyphicon-envelope"></span>
		<strong>Redactar</strong>
	</g:remoteLink>
	<ul class="list" id="lista_carpetas" style="margin-left: 0px">
		<g:each in="${etiquetasCarpetas}" var="carpeta">
			<g:if test="${carpetaSeleccionada == carpeta.key}">
				<li class="active li_clickeable droppable selectedFolder"
					id="${carpeta.key}">
					<g:link class="inbox-load"
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
		<li id="li_nueva_carpeta">
			<a 	data-original-title="Add Another" data-placement="right" title=""
				rel="tooltip" href="javascript:void(0);" id="nueva_carpeta">Agregar Carpeta 
					<span class="pull-right">
					<i class="fa fa-plus"></i>
					</span>
				</a>
		</li>
	</ul>
</div>

<div id="div_nueva_carpeta" class="center_div_carpeta"
	style="display: none">
	<div ondesktop="span4" ontablet="span6" class="box span4" style="width: 330px;">
		<div class="box-header">
			<h2>
				<i class="icon-list"></i>Nueva Carpeta
			</h2>
			<div class="box-icon">
				<a href="#" onclick="cerrar_form_nueva_carpeta()"><i class="icon-remove"></i></a>
			</div>
		</div>
		<div class="box-content" style="height: 160px">
			<form action="nuevaCarpeta" onsubmit="return validarNombre()">
				<div class="control-group">
					<label for="focusedInput" class="control-label">Nombre de carpeta</label>
					<div class="controls">
						<input type="text" name="nombre" id="nombre" class="input-xlarge focused">
					</div>
					<div id="carpetaNuevaError" class="message" role="status"
						style="display: none">No se ha especificado ningún nombre.</div>
				</div>
				<div class="form-actions" style="height: 72px; background-color: #FFFFFF">
					<button class="btn btn-primary" type="submit" id="guardar_carpeta">Guardar</button>
					<button class="btn" onclick="cerrar_form_nueva_carpeta()">Cancelar</button>
				</div>
			</form>
		</div>
	</div>
</div>
