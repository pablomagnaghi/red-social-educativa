<div class="row-fluid">
    <div class="box span12">
        <div class="box-header" data-original-title="">
            <h2><i class="icon-table"></i>
                <span class="break"></span>Evaluaciones del curso</h2>
        </div>
        <div class="box-content">
        	<g:if test="${flash.message}">
				<div class="box-content alerts">
		    		<div class="alert alert-info">
						<button class="close" data-dismiss="alert" type="button"></button>
						<strong></strong> 
						${flash.message}
				    </div>
				</div>    
			</g:if>
            <table class="table table-striped table-bordered bootstrap-datatable datatable">
                <thead>
                    <tr>
                        <th>Nombre</th>
                        <th>Fecha</th>
                        <th>Horario</th>
                        <th>Aula</th>  
						<th>Estado</th>     
						<th>Acciones</th>    
					</tr>
				</thead>
                <tbody>
                	<g:each in="${evaluaciones}" var="evaluacionInstance">
                		<g:if test="${evaluacionInstance.habilitada}">	
		                    <tr>
	    	                    <td>${fieldValue(bean: evaluacionInstance, field: "nombre")}</td>
	        	                <td class="center">${fieldValue(bean: evaluacionInstance, field: "fecha")}</td>
	            	            <td class="center">${fieldValue(bean: evaluacionInstance, field: "horario")}</td>
	            	            <td class="center">${fieldValue(bean: evaluacionInstance, field: "aula")}</td>
	            	            <td class="center">
	            	            	<g:if test="${com.fiuba.EvaluacionAprendiz.findByAprendizAndEvaluacion(aprendiz, evaluacionInstance)}">
		                        		Inscripto  
									</g:if>
									<g:else>
										No inscripto
									</g:else>
								</td>	
		                        <td class="center">
		                        	<g:if test="${com.fiuba.EvaluacionAprendiz.findByAprendizAndEvaluacion(aprendiz, evaluacionInstance)}">
		                        		Desincribirme
									</g:if>
									<g:else>
										<fieldset class="buttons">
											<g:link class="edit" action="inscribirme" id="${evaluacionInstance.id}" params="['cursoId': params.cursoId]">
												Inscribirme: "${com.fiuba.EvaluacionAprendiz.findByAprendizAndEvaluacion(aprendiz, evaluacionInstance)}"</g:link>
										</fieldset>
									</g:else>
		                        </td>
		                    </tr>
	                    </g:if>
					</g:each>   
                </tbody>
            </table>
        </div>
    </div>
    <!--/span-->
</div>
<!--/row-->

		
	