<g:each in="${etiquetasCarpetas}" var="carpeta">
	<li class="li_clickeable">
		<g:remoteLink action="mostrarMensajes" params='[nombreCarpeta: "${carpeta}"]' 
		update="[success:'lista_conversaciones']">${carpeta}</g:remoteLink>
	</li>
</g:each>
<li id='nueva_carpeta'>
	<g:img dir="images" file="newFolder.gif"/>
	<span> Nueva Carpeta </span>
</li>
