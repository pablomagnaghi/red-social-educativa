<div style="width: 150px; float: left">
	<ul id="lista_carpetas" style="list-style-type:none;">
		<g:render template="carpetas" model="['etiquetasCarpetas' : etiquetasCarpetas, 'seleccionada': carpetaSeleccionada]"></g:render>
	</ul>
</div>
<div id="lista_conversaciones" style="float: left">
	<g:render template="conversaciones" model="['conversaciones' : conversaciones]"></g:render>
</div>