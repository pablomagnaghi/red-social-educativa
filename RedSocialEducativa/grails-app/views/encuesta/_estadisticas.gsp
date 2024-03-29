<div class="box-header" data-original-title="">
	<h2><i class="icon-table"></i><span class="break"></span>Encuesta ${encuestaInstance}</h2>
</div>
<div class="box-content">
	<dl>
		<dt>Titulo: ${fieldValue(bean: encuestaInstance, field: "nombre")}</dt>
		<br>
		<dt>Cantidad de aprendices que la respondieron: ${com.encuesta.EncuestaAprendiz.findAllByEncuesta(encuestaInstance).size}
			<g:link style="float: right;" class="btn btn-success" action="aprendicesEncuesta" 
				params="['cursoId': params.cursoId, 'encuestaId': encuestaInstance.id]">Ver listado</g:link>	
		</dt>
		<br>
		<dt>Preguntas choice: <small style="float: right;">Cantidad de respuestas</small></dt>
			<g:set var="cant" value="${1}" />
			<g:each in="${encuestaInstance.preguntasChoice}" var="choice">	
				<dd><div class="message" style="width:100%">${cant} - ${choice}</div>
				</dd>
				<g:if test="${choice.opciones}">
					<ul>
						<g:each in="${choice.opciones}" var ="op">
							<p>-${op} <small style="float: right;">${op.cantRespuestas}</small></p>
						</g:each>
					</ul>
				</g:if>	
				<g:set var="cant" value="${cant + 1}" />
				<hr>
			</g:each>
		<dt>Preguntas a desarrollar: </dt>	
			<g:each in="${encuestaInstance.preguntasDesarrollo}" var="desarrollo">
				<dd><div class="message" style="width:100%">${cant} - ${desarrollo}
					<g:link style="float: right;" class="btn btn-success" action="respuestasDesarrollo" id="${desarrollo.id}" 
						params="['cursoId': params.cursoId, 'encuestaId': encuestaInstance.id]">
		            	Ver respuestas</g:link>
				</div></dd>	
				<g:set var="cant" value="${cant + 1}" />
				<hr>
			</g:each>
		<dt>Preguntas de puntajate: <small style="float: right;">Cantidad de respuestas</small></dt>	
			<g:each in="${encuestaInstance.preguntasPuntaje}" var="puntaje">
				<dd>${cant} - ${puntaje}</dd>
				<ul>
					<g:each in="[1,2,3,4,5,6,7,8,9,10]" var ="i">
						<g:set var="cantidadRespuestas" value="${varPreguntaPuntajeService.obtenerCantidadRespuestas(puntaje, i.toString().toShort())}"/>
						<p> ${i} <small style="float: right;">${cantidadRespuestas}</small></p>
					</g:each>	
				</ul>
				<g:set var="cant" value="${cant + 1}" />
				<hr>
			</g:each>
	</dl>
</div>
