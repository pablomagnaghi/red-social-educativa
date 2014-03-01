<li>
	<a class="dropmenu" href="#"><i class="icon-folder-close-alt"></i><span class="hidden-tablet">Mis cursos como aprendiz</span> 
	<span class="label">${cursosAprendiz.size()}</span></a>
	<ul>
		<g:each in="${cursosAprendiz}" var="cursoAprendiz">
		<li><g:link controller="curso" action="aprendiz" params="['cursoId': cursoAprendiz.id]">
			<i class="icon-hdd"></i><span class="hidden-tablet"> ${com.fiuba.Asignatura.get(cursoAprendiz.materia.id)}-${cursoAprendiz}</span></g:link>
		</li>
		</g:each>
	</ul>
</li>


