<g:if test="${noticiasCurso}">
	<h2>Cartelera general</h2>
	<br>
	<table>
		<thead>
			<tr>
				<td>Autor</td>
				<td>Noticia</td>
			</tr>
		</thead>
		<tbody>
			<g:each in="${noticiasCurso}">
				<g:if test="${it.visibilidad}">
					<tr>
						<td>
							<p>Mediador: ${it.mediador}</p>
							<p>Publicado: ${it.fecha}-${it.hora}<p>
						</td>
						<td>${it.texto}</td>
					</tr>
				</g:if>
			</g:each>
		</tbody>
	</table>
	<h5>Agregar PAGINACION</h5>
</g:if>