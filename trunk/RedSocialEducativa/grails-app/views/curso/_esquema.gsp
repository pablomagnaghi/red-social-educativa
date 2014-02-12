
<div>
	<g:if test="${flash.message}">
		<div class="message" role="status">${flash.message}</div>
	</g:if>
</div>
<div>
	<g:if test="${!dictaCuatrimestre}">
		<p>Este curso se dicta durante solo durante el cuatrimestre ${com.fiuba.Curso.get(cursoId).cuatDict}</p>
	</g:if>	
	<g:else>
		<g:if test="${cuatrimestreId}">
			<g:link controller="foroCurso" action="general" params="['cursoId': cursoId, 'cuatrimestreId': cuatrimestreId]">
				<g:message code="Foro del curso"/></g:link>
		</g:if>	
		<g:else>
			<p>Las opciones del cuatrimestre de cursada se visualizara en breve</p>
		</g:else>
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

