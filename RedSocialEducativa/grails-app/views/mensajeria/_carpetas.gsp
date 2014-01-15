<g:each in="${etiquetasCarpetas}" var="carpeta">
	<li class="li_clickeable"><p>${carpeta}</p></li>
</g:each>
<li id='nueva_carpeta'>
	<g:img dir="images" file="newFolder.gif"/>
	<span> Nueva Carpeta </span>
</li>
