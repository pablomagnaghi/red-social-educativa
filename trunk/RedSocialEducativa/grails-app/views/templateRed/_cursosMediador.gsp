<li>
	<a class="dropmenu" href="#"><i class="icon-folder-close-alt"></i><span class="hidden-tablet">Cursos de mediador</span> 
	<span class="label">${cursosMediador.size()}</span></a>
	<ul>
		<g:each in="${cursosMediador}" var="cursoMediador">
		<li><g:link controller="curso" action="mediador" params="['cursoId': cursoMediador.curso.id]">
			<i class="icon-folder-open-alt"></i><span class="hidden-tablet">
				${com.fiuba.Asignatura.get(cursoMediador.curso.asignatura.id).codigo} - ${cursoMediador.curso}</span></g:link>
		</li>
		</g:each>
	</ul>
</li>

