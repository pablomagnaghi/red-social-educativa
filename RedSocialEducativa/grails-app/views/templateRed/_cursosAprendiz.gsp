<li>
	<a class="dropmenu" href="#"><i class="icon-folder-close-alt"></i><span class="hidden-tablet">Cursos de aprendiz</span> 
	<span class="label">${cursosAprendiz.size()}</span></a>
	<ul>
		<g:each in="${cursosAprendiz}" var="cursoAprendiz">
		<li><g:link controller="curso" action="aprendiz" params="['cursoId': cursoAprendiz.cuatrimestre.curso.id]">
			<i class="icon-folder-open-alt"></i><span class="hidden-tablet">
				${com.cursado.Asignatura.get(cursoAprendiz.cuatrimestre.curso.asignatura.id).codigo} - ${cursoAprendiz.cuatrimestre.curso}</span></g:link>
		</li>
		</g:each>
	</ul>
</li>


