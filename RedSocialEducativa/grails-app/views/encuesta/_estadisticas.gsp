<div class="box-header" data-original-title="">
	<h2><i class="icon-table"></i><span class="break"></span>Encuesta ${encuestaInstance}</h2>
</div>
<div class="box-content">
	<dl>
		<dt>Titulo: ${fieldValue(bean: encuestaInstance, field: "nombre")}</dt>
		<dt>Cantidad de aprendices que la respondieron: ${com.encuesta.EncuestaAprendiz.findAllByEncuesta(encuestaInstance).size}</dt>
		<dt>Preguntas choice: </dt>
			<g:set var="cant" value="${1}" />
			<g:each in="${encuestaInstance.preguntasChoice}" var="choice">	
				<dd><div class="message" style="width:100%">${cant} - ${choice}</div>
				</dd>
				<g:if test="${choice.opciones}">
					<ul>
						<g:each in="${choice.opciones}" var ="op">
							<p>-${op} <small style="float: right;">[Cantidad de respuestas: ${op.cantRespuestas}]</small></p>
						</g:each>
					</ul>
				</g:if>	
				<g:set var="cant" value="${cant + 1}" />
				<br>
				<hr>
			</g:each>
		<dt>Preguntas a desarrollar: </dt>	
			<g:each in="${encuestaInstance.preguntasDesarrollo}" var="desarrollo">
				<dd><div class="message" style="width:100%">${cant} - ${desarrollo} [LINK PARA VER RESPUESTAS]</div></dd>
				<g:set var="cant" value="${cant + 1}" />
				<br>
				<hr>
			</g:each>
		<dt>Preguntas de puntajate: </dt>	
			<g:each in="${encuestaInstance.preguntasPuntaje}" var="puntaje">
				<dd>${cant} - ${puntaje}</dd>
				<ul>
					<g:each in="[1,2,3,4,5,6,7,8,9,10]" var ="i">
						<p> ${i} <small style="float: right;">[RESPUESTA: CANTIDAD POR PUNTAJE]</small></p>
					</g:each>	
				</ul>
				<g:set var="cant" value="${cant + 1}" />
				<br>
				<hr>
			</g:each>
	</dl>
</div>
