<div class="box-header" data-original-title="">
	<h2><i class="icon-table"></i><span class="break"></span>Encuesta ${encuestaInstance}</h2>
	<div class="box-icon">	
		<g:link action="create" params="['cursoId': params.cursoId]"><i class="icon-plus"></i></g:link>
		<g:if test="${!encuestaInstance.habilitada}">
			<g:link action="edit" id="${encuestaInstance.id}" params="['cursoId': params.cursoId]"><i class="icon-edit"></i></g:link>
		</g:if>	
		<g:link action="delete" method="DELETE" id="${encuestaInstance.id}" params="['cursoId': params.cursoId]"
			onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" ><i class="icon-trash "></i></g:link>
		<g:link action="index" params="['cursoId': params.cursoId]"><i class="icon-table"></i></g:link>	
	</div>
</div>
<div class="box-content">
	<g:if test="${!encuestaInstance.habilitada}">
		<g:link class="btn btn-success" controller="preguntaChoice" action="create" params="['cursoId': params.cursoId, 'encuestaId': encuestaInstance.id]">
			<i class="icon-plus"></i> Pregunta choice</g:link>
		<g:link class="btn btn-success" controller="preguntaDesarrollo" action="create" params="['cursoId': params.cursoId, 'encuestaId': encuestaInstance.id]">
			<i class="icon-plus"></i> Pregunta a desarrollar</g:link>
		<g:link class="btn btn-success" controller="preguntaPuntaje" action="create" params="['cursoId': params.cursoId, 'encuestaId': encuestaInstance.id]">
			<i class="icon-plus"></i> Pregunta de puntaje</g:link>
	</g:if>	
	<g:if test="${flash.message}">
		<div class="box-content alerts">
			<div class="alert alert-info"><button class="close" data-dismiss="alert" type="button"></button><strong>${flash.message}</strong></div>
		</div>
	</g:if>	
	<dl>
		<dt>Titulo: ${fieldValue(bean: encuestaInstance, field: "nombre")}</dt>
		<dt>Preguntas choice: </dt>
			<g:set var="cant" value="${1}" />
			<g:each in="${encuestaInstance.preguntasChoice}" var="choice">
				
				<dd><div class="message" style="width:100%">${cant} - ${choice}</div></dd>
				<g:if test="${choice.opciones}">
					<ul>
						<g:each in="${choice.opciones}" var ="op">	
							<p>${op}
								<g:if test="${!encuestaInstance.habilitada}">
									<g:link style="float: right;" class="btn btn-danger" controller="opcionChoice" action="delete" method="DELETE" id="${op.id}"
										params="['cursoId': params.cursoId, 'encuestaId': encuestaInstance.id, 'preguntaId': choice.id]"
										onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');">
										<i class="icon-trash "></i></g:link>
									<g:link style="float: right;" class="btn btn-info" controller="opcionChoice" action="edit" id="${op.id}"
										params="['cursoId': params.cursoId, 'encuestaId': encuestaInstance.id, 'preguntaId': choice.id]">
										<i class="icon-edit "></i></g:link>
								</g:if>	
								</p>
						</g:each>
					</ul>
				</g:if>	
				<g:set var="cant" value="${cant + 1}" />
				<br>
				<g:if test="${!encuestaInstance.habilitada}">
					<dd>
					<g:link class="btn btn-success"  controller="opcionChoice" action="create" 
						params="['cursoId': params.cursoId, 'encuestaId': encuestaInstance.id, 'preguntaId': choice.id]">
						<i class="icon-plus"></i> Opcion</g:link>
					<g:link class="btn btn-info"  controller="preguntaChoice" action="edit" id="${choice.id}" 
						params="['cursoId': params.cursoId, 'encuestaId': encuestaInstance.id]"><i class="icon-edit"></i></g:link>
					<g:link class="btn btn-danger"  controller="preguntaChoice" action="delete" method="DELETE" id="${choice.id}" 
						params="['cursoId': params.cursoId, 'encuestaId': encuestaInstance.id]"
						onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" >
						<i class="icon-trash "></i></g:link>
					</dd>
				</g:if>
				<hr>
			</g:each>
		<dt>Preguntas a desarrollar: </dt>	
			<g:each in="${encuestaInstance.preguntasDesarrollo}" var="desarrollo">
				<dd><div class="message" style="width:100%">${cant} - ${desarrollo}</div></dd>
				<g:set var="cant" value="${cant + 1}" />
				<br>
				<g:if test="${!encuestaInstance.habilitada}">
					<dd><g:link class="btn btn-info"  controller="preguntaDesarrollo" action="edit" id="${desarrollo.id}" 
						params="['cursoId': params.cursoId, 'encuestaId': encuestaInstance.id]"><i class="icon-edit"></i></g:link>
					<g:link class="btn btn-danger"  controller="preguntaDesarrollo" action="delete" method="DELETE" id="${desarrollo.id}" 
						params="['cursoId': params.cursoId, 'encuestaId': encuestaInstance.id]"
						onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" >
						<i class="icon-trash "></i></g:link></dd>
				</g:if>	
				<hr>
			</g:each>
		<dt>Preguntas de puntajate: </dt>	
			<g:each in="${encuestaInstance.preguntasPuntaje}" var="puntaje">
				<dd>${cant} - ${puntaje}</dd>
				<dd>Puntaje: </dd>
				<g:set var="cant" value="${cant + 1}" />
				<br>
				<g:if test="${!encuestaInstance.habilitada}">
					<dd><g:link class="btn btn-info"  controller="preguntaPuntaje" action="edit" id="${puntaje.id}" 
						params="['cursoId': params.cursoId, 'encuestaId': encuestaInstance.id]"><i class="icon-edit"></i></g:link>
					<g:link class="btn btn-danger"  controller="preguntaPuntaje" action="delete" method="DELETE" id="${puntaje.id}" 
						params="['cursoId': params.cursoId, 'encuestaId': encuestaInstance.id]"
						onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" >
						<i class="icon-trash "></i></g:link></dd>
				</g:if>		
				<hr>
			</g:each>
	</dl>
</div>
