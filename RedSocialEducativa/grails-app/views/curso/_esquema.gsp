<div>
	<g:if test="${flash.message}">
		<div class="message" role="status">${flash.message}</div>
	</g:if>
</div>
<div>
	<hr>
		<h2><g:link controller="foroCurso" action="general" params="['cursoId': cursoId]">
			<g:message code="Foro del curso"/></g:link></h2>
</div>
<div>
	<hr>
		<h2><g:link controller="materialCurso" action="general" params="['cursoId': cursoId]">
			<g:message code="Materiales del curso"/></g:link></h2>
</div>
<div>
	<hr>
		<h2><g:link action="temas" params="['cursoId': cursoId]">
			<g:message code="Temas del curso"/></g:link></h2>
</div>

<div>
	<h2>Cartelera general</h2>
	<br>
	<g:if test="${noticiasCurso}">
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
							<p>Publicado: ${it.fecha} - ${it.hora} <p>
						</td>
						<td>${it.texto}</td>
					</tr>
				</g:if>
			</g:each>
		</tbody>
		</table>
	</g:if>
	<h5>Agregar PAGINACION</h5>
</div>

