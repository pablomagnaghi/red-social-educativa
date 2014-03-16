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
	        	                <td class="center"><g:formatNumber number="${evaluacionInstance.fecha}"/></td>
	        	                <td class="center"><g:formatNumber number="${evaluacionInstance.horario}"/></td>
	            	            <td class="center">
	            	            	<g:if test="${fieldValue(bean: evaluacionInstance, field: "aula")}">${fieldValue(bean: evaluacionInstance, field: "aula")}</g:if>
	            	            	<g:else>No asignada</g:else>
	            	            </td>
	            	            <td class="center">
	            	            	<g:if test="${com.cursado.EvaluacionAprendiz.findByAprendizAndEvaluacion(aprendiz, evaluacionInstance)}">
	            	            		<span class="label label-success">Inscripto</span>
									</g:if>
									<g:else>
										<span class="label label-important">No inscripto</span>
									</g:else>
								</td>	
		                        <td class="center">
		                        	<g:if test="${evaluacionInstance.fecha > (com.fiuba.Utilidades.FECHA + 1)}">
			                        	<g:if test="${com.cursado.EvaluacionAprendiz.findByAprendizAndEvaluacion(aprendiz, evaluacionInstance)}">
			                        		<g:if test="${evaluacionInstance.obligatoria}">
			                        			<span class="label label-warning"><h5>Inscripción obligatoria</h5></span>
			                        		</g:if>
			                        		<g:else>
			                        		<g:link class="btn btn-danger" action="desinscribirme" id="${evaluacionInstance.id}" params="['cursoId': params.cursoId]">
												Desinscribirme</g:link>
											</g:else>	
										</g:if>
										<g:else>
											<fieldset class="buttons">
												<g:link class="btn btn-success" action="inscribirme" id="${evaluacionInstance.id}" params="['cursoId': params.cursoId]">
													Inscribirme</g:link>
											</fieldset>
										</g:else>
									</g:if>
									<g:else><span class="label label-warning"><h5>Período de inscripción vencido</h5></span></g:else>
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

		
	