<div>
	<br><h2>Cartelera general</h2>
	<g:each in="${noticiasCurso}">
		<g:if test="${it.visibilidad}">
			<p>Noticia: ${it.titulo} - Fecha: ${it.fecha} - Mediador: ${it.mediador}</p>
			<p>[${it.texto}]</p>
			<br>
		</g:if>
    </g:each>
</div>