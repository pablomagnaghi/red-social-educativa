<div class="btn-group">
	<button class="btn btn-large btn-primary">Tareas generales</button>
	<button class="btn btn-large dropdown-toggle btn-primary" data-toggle="dropdown"><span class="caret"></span></button>
	<ul class="dropdown-menu">
		<li><g:link controller="materialCurso" action="index" params="['cursoId': params.cursoId]">
			<i class="icon-table"></i> Material del curso</g:link></li>
		<li><g:link controller="tema" action="index" params="['cursoId': params.cursoId]">
			<i class="icon-table"></i> Temas del curso</g:link></li>
		<li><g:link controller="evaluacion" action="index" params="['cursoId': params.cursoId]">
			<i class="icon-table"></i> Evaluaciones del curso</g:link></li>
		<li><g:link controller="aprendiz" action="aprendicesCurso" params="['cursoId': params.cursoId]">
			<i class="icon-user"></i> Aprendices del curso</g:link>
		<li><g:link controller="cuatrimestre" action="consolidar" params="['cursoId': params.cursoId]">
			<i class="icon-refresh"></i>
			<g:if test="${cuatrimestres}"> Consolidar cuat ${cuatrimestres.first()}</g:if>
			<g:else>Crear cuatrimestre</g:else></g:link></li>
		<li><g:link controller="cuatrimestre" action="indexHistoriales" params="['cursoId': params.cursoId]">
			<i class="icon-table"></i> Historial de cuatrimestres</g:link>	
	</ul>
</div>
<g:if test="${cuatrimestre}">
	<div class="btn-group">
		<button class="btn btn-large btn-primary">Tareas cuatrimestrales</button>
		<button class="btn btn-large dropdown-toggle btn-primary" data-toggle="dropdown"><span class="caret"></span></button>
		<ul class="dropdown-menu">
			<li><g:link controller="noticiaCurso" action="index" params="['cursoId': params.cursoId, 'cuatrimestreId': cuatrimestre?.id]">
				<i class="icon-dashboard"></i> Cartelera</g:link></li>
			<li><g:link controller="actividad" action="index" params="['cursoId': params.cursoId, 'cuatrimestreId': cuatrimestre?.id]">
				<i class="icon-table"></i> Actividades del cuatrimestre</g:link></li>
			<li><g:link controller="foroCurso" action="general" params="['cursoId': params.cursoId, 'cuatrimestreId': cuatrimestre?.id]">
				<i class="icon-table"></i> Foro de cursado</g:link></li>
			<li><g:link controller="aprendiz" action="index" params="['cursoId': params.cursoId, 'cuatrimestreId': cuatrimestre?.id]">
				<i class="icon-user"></i> Aprendices</g:link></li>
			<li><g:link controller="aprendiz" action="estadisticas" params="['cursoId': params.cursoId, 'cuatrimestreId': cuatrimestre?.id]">
				<i class="icon-user"></i> Estadisticas</g:link></li>	
		</ul>
	</div>
</g:if>
<div class="btn-group">
	<button class="btn btn-large btn-primary">Mi perfil</button>
	<button class="btn btn-large dropdown-toggle btn-primary" data-toggle="dropdown"><span class="caret"></span></button>
	<ul class="dropdown-menu">
		<g:if test="${mediador.jerarquia == '1-Profesor'}">
			<li><g:link controller="mediador" action="index" params="['cursoId': params.cursoId]">
			<i class="icon-user"></i> ABM mediadores</g:link></li>
		</g:if>	
		<li class="divider"></li>
		<li><g:link controller="mediador" action="cambiarEstado" id="${mediador.id}" params="['cursoId': params.cursoId]" 
			onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"> 
			<i class="icon-off"></i> Dejar el curso</g:link>
	</ul>
</div>
