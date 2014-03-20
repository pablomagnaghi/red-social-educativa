<div class="box-header" data-original-title="">
	<h2><i class="icon-table"></i><span class="break"></span>Encuesta ${encuestaInstance}</h2>
	<div class="box-icon">	
		<g:link action="encuestasCurso" params="['cursoId': params.cursoId]"><i class="icon-table"></i></g:link>
	</div>
</div>
<div class="box-content">
	<g:if test="${flash.message}">
		<div class="box-content alerts">
			<div class="alert alert-info"><button class="close" data-dismiss="alert" type="button"></button><strong>${flash.message}</strong></div>
		</div>
	</g:if>	
	<g:form class="form-horizontal" action="responder" id="${encuestaInstance.id}" params="['cursoId': params.cursoId]">
		<fieldset>
			<dl>
				<dt>Titulo: ${fieldValue(bean: encuestaInstance, field: "nombre")}</dt>
				<dt>Preguntas choice: </dt>
					<g:set var="cant" value="${1}" />
					<g:each in="${encuestaInstance.preguntasChoice}" var="choice">
						<dd><div class="message" style="width:100%">${cant} - ${choice}</div></dd>
						<g:if test="${choice.opciones}">
							<ul>
								<g:radioGroup name="${choice}" values="${choice.opciones}" value="1" labels="${choice.opciones}">
									<p><label class="radio">${it.radio} ${it.label}</label>	</p>
								</g:radioGroup>
							</ul>
						</g:if>	
						<g:set var="cant" value="${cant + 1}" />
						<hr>
					</g:each>
				<dt>Preguntas a desarrollar: </dt>	
					<g:each in="${encuestaInstance.preguntasDesarrollo}" var="desarrollo" status="i">
						<div class="message" style="width:100%">${cant} - ${desarrollo}</div>		
						<g:textArea name="${desarrollo}" style='width: 95%; height: 200px;'/>
						<g:set var="cant" value="${cant + 1}" />
						<hr>
					</g:each>
				<dt>Preguntas de puntajate: </dt>	
					<g:each in="${encuestaInstance.preguntasPuntaje}" var="puntaje" status="i">
						<div class="message" style="width:100%">${cant} - ${puntaje}</div>		
						<g:select name="${puntaje}" from="${[1,2,3,4,5,6,7,8,9,10]}" style='width: 10%;'/>
						<g:set var="cant" value="${cant + 1}" />
						<hr>
					</g:each>
			</dl>
		<div class="form-actions">
			<button type="submit" class="btn btn-primary">Responder</button>
		</div>		    
		</fieldset>
	</g:form>
</div>
