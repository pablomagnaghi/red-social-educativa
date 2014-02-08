<div>
	<g:if test="${flash.message}">
		<div class="message" role="status">${flash.message}</div>
	</g:if>
</div>
<div>
	<g:if test="${cuatrimestre}">
		<g:link controller="foroCurso" action="general" params="['cuatrimestreId': cuatrimestre.id]">
			<g:message code="Foro del curso"/></g:link>
	</g:if>	
	<g:else>
		<p>El curso no se dicta durante este cuatrimestre</p>
		<p>Este curso se dicta durante el (primer/segundo) cuatrimestre</p>
	</g:else>
</div>
<div>
	<g:link action="material" params="['cursoId': cursoId]">
		<g:message code="Material del curso"/></g:link>
</div>
<div>
	<g:link action="temas" params="['cursoId': cursoId]">
		<g:message code="Temas del curso"/></g:link>
</div>

<div>
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
							<p>Publicado: ${it.fecha} - ${it.hora} <p>
						</td>
						<td>${it.texto}</td>
					</tr>
				</g:if>
			</g:each>
		</tbody>
		</table>
	<h5>Agregar PAGINACION</h5>
	</g:if>
	
</div>

