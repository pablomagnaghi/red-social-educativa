<div class="btn-group">
	<button class="btn btn-large btn-primary">Tareas generales</button>
	<button class="btn btn-large dropdown-toggle btn-primary" data-toggle="dropdown"><span class="caret"></span></button>
	<ul class="dropdown-menu">
		<li><g:link controller="materialCurso" action="index" params="['cursoId': params.cursoId]">
			<i class="icon-table"></i> Material del curso</g:link></li>
		<li><g:link controller="tema" action="index" params="['cursoId': params.cursoId]">
			<i class="icon-table"></i> Temas del curso</g:link></li>
		<li><g:link controller="evaluacion" action="evaluacionesCurso" params="['cursoId': params.cursoId]">
			<i class="icon-table"></i> Evaluaciones del curso</g:link></li>
		<li><g:link controller="encuesta" action="encuestasCurso" params="['cursoId': params.cursoId]">
			<i class="icon-table"></i> Encuestas del curso</g:link></li>	
	</ul>
</div>
<g:if test="${aprendiz.cursando}">	
	<div class="btn-group">
		<button class="btn btn-large btn-primary">Tareas cuatrimestrales</button>
		<button class="btn btn-large dropdown-toggle btn-primary" data-toggle="dropdown"><span class="caret"></span></button>
		<ul class="dropdown-menu">
			<li><g:link controller="actividad" action="index" params="['cursoId': params.cursoId, 'cuatrimestreId': cuatrimestre?.id]">
				<i class="icon-table"></i> Actividades del cuatrimestre</g:link></li>
			<li><g:link controller="foroCurso" action="general" params="['cursoId': params.cursoId, 'cuatrimestreId': cuatrimestre?.id]">
				<i class="icon-table"></i> Foro de cursado</g:link></li>
		</ul>
	</div>
</g:if>
<div class="btn-group">
	<button class="btn btn-large btn-primary">Mi perfil</button>
	<button class="btn btn-large dropdown-toggle btn-primary" data-toggle="dropdown"><span class="caret"></span></button>
	<ul class="dropdown-menu">
		<li><g:link controller="evaluacion" action="evaluacionesAprendiz" params="['cursoId': params.cursoId]">
			<i class="icon-user"></i> Mis evaluaciones</g:link></li>
		<g:if test="${aprendiz.cursando}">		
			<li><g:link controller="actividad" action="actividadesAprendiz" params="['cursoId': params.cursoId, 'cuatrimestreId': cuatrimestre?.id]">
				<i class="icon-user"></i> Mis actividades</g:link></li>
		</g:if>
		<li><g:link controller="encuesta" action="misEncuestas" params="['cursoId': params.cursoId]">
			<i class="icon-user"></i> Mis encuestas</g:link></li>
		<li class="divider"></li>
		<li><g:link controller="aprendiz" action="cambiarEstado" id="${aprendiz.id}" 
			params="['cursoId': params.cursoId,  'cuatrimestreId': cuatrimestre?.id]" 
			onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"> 
			<i class="icon-off"></i> Dejar el curso</g:link>
	</ul>
</div>
											