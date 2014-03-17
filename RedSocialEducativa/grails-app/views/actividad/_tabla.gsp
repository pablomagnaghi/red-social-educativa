<div class="row-fluid">
    <div class="box span12">
        <div class="box-header" data-original-title="">
            <h2><i class="icon-table"></i>
                <span class="break"></span>Actividades del cuatrimestre ${com.cursado.Cuatrimestre.get(params.cuatrimestreId)}</h2>
            <g:if test="${mediador}">    
	            <div class="box-icon">
	                <g:link action="create" params="['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId]"><i class="icon-plus"></i></g:link>
	            </div>
            </g:if>
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
                        <th>Titulo</th>
                        <th>Categoria</th>
                        <th>Fecha finalizacion</th>
                        <th>Evaluable</th>  
                        <th>Material</th>
                        <th>Temas</th>
						<th>Acciones</th>            
					</tr>
				</thead>
                <tbody>
                	<g:each in="${actividadInstanceList}" var="actividadInstance">
                		<g:if test="${(aprendiz && actividadInstance.visibilidad) || (mediador)}">
		                    <tr>
	    	                    <td>${actividadInstance.titulo}</td>
	        	                <td class="center">${actividadInstance.categoria}</td>
	            	            <td class="center"><g:formatNumber number="${actividadInstance.fechaFinalizacion}" /></td>
	            	            <td class="center">
	            	            	<g:if test="${actividadInstance.evaluable}">Si</g:if>
	            	            	<g:else>No</g:else>
	            	            </td>	
	            	            <td class="center">
	            	           		<g:if test="${actividadInstance.materiales}">
		        	                	<g:each in="${actividadInstance.materiales}" var="m">
											<p>${m?.encodeAsHTML()}
												<g:if test="${mediador}">
													<g:link style="float: right;" class="btn btn-danger" controller="materialActividad" action="delete" method="DELETE" id="${m.id}" 
														params="['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 'actividadId': actividadInstance.id]"
														onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" >
														<i class="icon-trash "></i></g:link>
													<g:link style="float: right;" class="btn btn-info" controller="materialActividad" action="edit" id="${m.id}"
														params="['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 'actividadId': actividadInstance.id]">
														<i class="icon-edit "></i></g:link> 	
												</g:if>	
												<g:link style="float: right;" class="btn btn-success" controller="materialActividad" action="show" id="${m.id}" 
													params="['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 'actividadId': actividadInstance.id]">
													<i class="icon-search"></i></g:link>
												<g:link style="float: right;" class="btn btn-success" controller="materialActividad" action="descargar" id="${m?.archivo?.id}" 
													params="['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 'actividadId': actividadInstance.id]">
													<i class="icon-download-alt"></i></g:link></p>		
										</g:each>  
									</g:if>
									<g:else>
										<p>No posee</p>
									</g:else>	
	            	            </td>
	            	            <td class="center">
	            	            	<g:if test="${actividadInstance.temas}">
		        	           	     	<g:each in="${actividadInstance.temas}" var="t">
											<p>${t.tema}
												<g:if test="${mediador}">
													<g:link style="float: right;" class="btn btn-danger" controller="temaActividad" action="delete" method="DELETE" id="${t.id}" 
														params="['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 'actividadId': actividadInstance.id]"
														onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" >
														<i class="icon-trash "></i></g:link>	
												</g:if>
												<g:link style="float: right;" class="btn btn-success" controller="tema" action="index" id="${t.tema.id}" 
													params="['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 'actividadId': actividadInstance.id]">
													<i class="icon-search"></i></g:link></p>
										</g:each>  
									</g:if>
									<g:else>
										<p>No posee</p>
									</g:else>	
	            	            </td>
		                        <td class="center">
		                        	<g:if test="${mediador}">          
			                        	<g:link class="btn btn-success" controller="materialActividad" action="create"  
											params="['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 'actividadId': actividadInstance?.id]">M</g:link>
			          					<g:link class="btn btn-success" controller="temaActividad" action="create"  
											params="['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 'actividadId': actividadInstance?.id]">T</g:link>
										<g:link class="btn btn-success" controller="grupoActividad" action="gruposMediador" id="${actividadInstance.id}"
			                            	params="['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 'actividadId': actividadInstance.id]">
			                            	<i class="icon-group"></i></g:link>	
									</g:if>		
									<g:if test="${aprendiz}">
										<g:set var="varGrupoActividadAprendiz" value="${varGrupoActividadService.obtenerGrupoAprendiz(aprendiz, actividadInstance.id)}"/>
										<g:if test="${varGrupoActividadAprendiz}">
											<g:link class="btn btn-success" controller="grupoActividad" action="grupoAprendiz" id="${varGrupoActividadAprendiz.grupo.id}"
				                            	params="['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 'actividadId': actividadInstance.id]">
				                            	<i class="icon-group"></i></g:link>
			                            </g:if>
			                            <g:else>
			                            	<g:link class="btn btn-success" controller="grupoActividad" action="gruposAprendiz" 
				                            	params="['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 'actividadId': actividadInstance.id]">
				                            	<i class="icon-group"></i></g:link>
			                            </g:else>	
									</g:if>
			                        <g:link class="btn btn-success" action="show" id="${actividadInstance.id}"
			                            params="['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId]"><i class="icon-search "></i></g:link>
			                        <g:if test="${mediador}">   
				              			<g:link class="btn btn-info" action="edit" id="${actividadInstance.id}"
				          					params="['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId]"><i class="icon-edit "></i></g:link>
			                            <g:link class="btn btn-danger" action="delete" method="DELETE" id="${actividadInstance.id}" 
			                            	params="['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId]"
			                            	onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" >	                          
			                                <i class="icon-trash "></i></g:link>
			                    	</g:if>    
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
