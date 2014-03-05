<div class="row-fluid">
    <div class="box span12">
        <div class="box-header" data-original-title="">
            <h2><i class="icon-table"></i>
                <span class="break"></span>Evaluaciones del curso</h2>
            <div class="box-icon">
                <g:link action="create" params="['cursoId': params.cursoId]"><i class="icon-plus"></i></g:link>
            </div>
        </div>
        <div class="box-content">
            <table class="table table-striped table-bordered bootstrap-datatable datatable">
                <thead>
                    <tr>
                        <th>Nombre</th>
                        <th>Fecha</th>
                        <th>Horario</th>
                        <th>Aula</th>  
                        <th>Habilitada</th>
                        <th>Obligatoria</th>
						<th>Acciones</th>         
					</tr>
				</thead>
                <tbody>
                	<g:each in="${evaluaciones}" var="evaluacionInstance">
	                    <tr>
    	                    <td>${fieldValue(bean: evaluacionInstance, field: "nombre")}</td>
        	                <td class="center">${fieldValue(bean: evaluacionInstance, field: "fecha")}</td>
            	            <td class="center">${fieldValue(bean: evaluacionInstance, field: "horario")}</td>
            	            <td class="center">${fieldValue(bean: evaluacionInstance, field: "aula")}</td>
            	            <td class="center">${fieldValue(bean: evaluacionInstance, field: "habilitada")}</td>
            	            <td class="center">${fieldValue(bean: evaluacionInstance, field: "obligatoria")}</td>
	                        <td class="center">
	                        	<g:if test="${com.fiuba.EvaluacionAprendiz.findByAprendizAndEvaluacion(aprendiz, evaluacionInstance)}">
									<div>Usted ya esta inscripto en la evaluacion (REVISAR SI TIENE NOTA Y PONER NOTA/PENDIENTE)</div>
								</g:if>
								<g:else>
									<fieldset class="buttons">
										<g:link class="edit" action="inscribirme" 
											id="${evaluacionInstance.id}" 
											params="['cursoId': params.cursoId]">
											<g:message code="Inscribirme" /></g:link>
									</fieldset>
								</g:else>
	                        </td>
	                    </tr>
					</g:each>   
                </tbody>
            </table>
        </div>
    </div>
    <!--/span-->
</div>
<!--/row-->

		
	