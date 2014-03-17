<div>
	<g:link controller="materialCurso" action="index" params="['cursoId': params.cursoId]">
		<g:message code="Material del curso" /></g:link>
</div>
<div>
	<g:link controller="tema" action="index" params="['cursoId': params.cursoId]">
		<g:message code="Temas del curso" /></g:link>
</div>
<div>
	<g:link controller="evaluacion" action="evaluacionesCurso" params="['cursoId': params.cursoId]">
	<g:message code="Evaluaciones del curso" /></g:link>
</div>
<div>
	<g:link action="evaluacionesAprendiz" controller="evaluacion" params="['cursoId': params.cursoId]">
	<g:message code="Mis evaluaciones en el curso" /></g:link>
</div>
<div>
	<g:link controller="aprendiz" action="cambiarEstado" id="${aprendiz.id}" 
		params="['cursoId': params.cursoId,  'cuatrimestreId': cuatrimestre?.id]" 
		onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"> 
		DEJAR DE PARTICIPAR </g:link>
</div>

<g:if test="${aprendiz.cursando}">	
	<div>
		<g:link controller="actividad" action="index"
			params="['cursoId': params.cursoId, 'cuatrimestreId': cuatrimestre?.id]">
			<g:message code="Actividades del cuatrimestre" /></g:link>
	</div>	
	<div>
		<g:link controller="actividad" action="actividadesAprendiz"
			params="['cursoId': params.cursoId, 'cuatrimestreId': cuatrimestre?.id]">
			<g:message code="Mis actividades" /></g:link>
	</div>	
	<div>
		<g:link controller="foroCurso" action="general" params="['cursoId': params.cursoId, 'cuatrimestreId': cuatrimestre?.id]">
			<g:message code="Foro del curso"/></g:link>
	</div>
</g:if>
<g:else>
	<p>Usted curso la materia durante el cuatrimestre: ${aprendiz.cuatrimestre.anio} - ${aprendiz.cuatrimestre.numero}</p>
</g:else>	 	    
										
									
				