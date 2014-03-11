<li>
	<a class="dropmenu" href="#"><i class="icon-folder-close-alt"></i><span class="hidden-tablet">Mis cursos como mediador</span> 
	<span class="label">${cursosMediador.size()}</span></a>
	<ul>
		<g:each in="${cursosMediador}" var="cursoMediador">
		<li><g:link controller="curso" action="mediador" params="['cursoId': cursoMediador.id]">
			<i class="icon-hdd"></i><span class="hidden-tablet">${cursoMediador}</span></g:link>
		</li>
		</g:each>
	</ul>
</li>

