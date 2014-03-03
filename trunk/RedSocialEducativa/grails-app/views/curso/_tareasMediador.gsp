<div class="row-fluid">
	<div class="box span12">
		<div class="box-header">
			<h2><i class="icon-font"></i><span class="break"></span>Mis tareas</h2>
		</div>
		<div class="box-content">	
			<dl>
				<dt>Generales</dt>
					<dd><g:link controller="materialCurso" action="index" params="['cursoId': params.cursoId]">Material</g:link></dd>
					<dd><g:link controller="tema" action="index" params="['cursoId': params.cursoId]">Temas</g:link></dd>
					<dd><g:link controller="evaluacion" action="index" params="['cursoId': params.cursoId]">Evaluaciones</g:link></dd>		
					<dd><g:link controller="cuatrimestre" action="create" params="['cursoId': params.cursoId]">Consolidar cuatrimestre ${cuatrimestres.first()}</g:link></dd>				
					<dd><g:link controller="cuatrimestre" action="indexHistoriales" params="['cursoId': params.cursoId]">Historial de cuatrimestres</g:link></dd>				
			</dl>
			<g:if test="${cuatrimestre?.id}">
				<dl>
					<dt>Cuatrimestrales</dt>
						<dd><g:link controller="aprendiz" action="index" params="['cursoId': params.cursoId, 'cuatrimestreId': cuatrimestre?.id]">Aprendices</g:link></dd>
						<dd><g:link controller="noticiaCurso" action="index" params="['cursoId': params.cursoId, 'cuatrimestreId': cuatrimestre?.id]">Cartelera</g:link></dd>	
						<dd><g:link controller="foroCurso" action="general" params="['cursoId': params.cursoId, 'cuatrimestreId': cuatrimestre?.id]">Foro de cursada</g:link></dd>	
						<dd><g:link controller="aprendiz" action="estadisticas" params="['cursoId': params.cursoId, 'cuatrimestreId': cuatrimestre?.id]">Estadisticas</g:link></dd>	
						<dd><g:link controller="actividad" action="index" params="['cursoId': params.cursoId, 'cuatrimestreId': cuatrimestre?.id]">Actividades</g:link></dd>		
				</dl>
			</g:if>                        	 
		</div>						
	</div>
</div>	