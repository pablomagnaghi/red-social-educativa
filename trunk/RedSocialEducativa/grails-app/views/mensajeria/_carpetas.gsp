<g:each in="${etiquetasCarpetas}" var="carpeta">
	<g:if test="${seleccionada == carpeta.key}">
		<li class="active li_clickeable droppable selectedFolder"
			id="${carpeta}"><g:remoteLink class="inbox-load"
				action="mostrarMensajes" params='[nombreCarpeta: "${carpeta}"]'
				update="[success:'inbox-content']" onSuccess="when_ready();actualizar('carpeta', '${carpeta }')">
				${carpeta.key}
				<g:if test="${carpeta.value > 0}">
		(${carpeta.value})</g:if>
			</g:remoteLink></li>
	</g:if>
	<g:else>
		<li class="li_clickeable droppable" id="${carpeta}"><g:remoteLink
				action="mostrarMensajes" params='[nombreCarpeta: "${carpeta}"]'
				update="[success:'inbox-content']" onSuccess="when_ready();actualizar('carpeta', '${carpeta }')">
				${carpeta.key}
				<g:if test="${carpeta.value > 0}">
		(${carpeta.value})</g:if>
			</g:remoteLink></li>
	</g:else>
</g:each>
<h6>
	Agregar Carpeta <a class="pull-right txt-color-darken"
		data-original-title="Add Another" data-placement="right" title=""
		rel="tooltip" href="javascript:void(0);" id="nueva_carpeta"><i
		class="fa fa-plus"></i></a>
</h6>