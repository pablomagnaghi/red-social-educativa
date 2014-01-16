<g:each in="${etiquetasCarpetas}" var="carpeta">
	<g:if test="${seleccionada == carpeta.key}">
    	 <li class="selectedFolder li_clickeable droppable selectedFolder" id="${carpeta}">
	</g:if>
	<g:else>
	    <li class="li_clickeable droppable" id="${carpeta}">
	</g:else>
		<g:remoteLink action="mostrarMensajes" params='[nombreCarpeta: "${carpeta}"]' 
		update="[success:'panel_mensajes']" onSuccess="when_ready();">${carpeta.key} <g:if test="${carpeta.value > 0}">
		(${carpeta.value})</g:if>
		</g:remoteLink>
	</li>
</g:each>
<li id='nueva_carpeta'>
	<g:img dir="images" file="newFolder.gif"/>
	<span> Nueva Carpeta </span>
</li>
