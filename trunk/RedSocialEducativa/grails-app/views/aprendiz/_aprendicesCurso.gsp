<div class="row-fluid">
    <div class="box span12">
        <div class="box-header" data-original-title="">
            <h2><i class="icon-user"></i><span class="break"></span>Aprendices</h2>
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
                    	<th>Padron</th>  
                        <th>Apellido</th>
                        <th>Nombres</th>
                        <th>Cuatrimestre de cursada</th>
                        <th>Email</th>       
                        <th>Estado</th>    
                        <th>Acciones</th>            
                    </tr>
                </thead>
                <tbody>
                	<g:each in="${aprendices}" var="aprendizInstance">
	                    <tr>
	                    	<td>${aprendizInstance.usuario.padron}</td>
    	                    <td class="center">${aprendizInstance.usuario.apellido}</td>
    	                    <td class="center">${aprendizInstance.usuario.nombres}</td>
    	            		<td class="center">${aprendizInstance.cuatrimestre}</td>
    	                    <td class="center">${aprendizInstance.usuario.email}</td>
							<td class="center">
								<g:if test="${aprendizInstance.participa}">
								 	<span class="label label-success">Activo</span>									
								</g:if>		
								<g:else>	
              						<g:if test="${aprendizInstance.cuatrimestre == cuatrimestre}">
										<span class="label label-warning">Esperando aceptacion</span>
									</g:if>
									<g:else>
										<span class="label label-important">Inactivo</span>
									</g:else>
								</g:else>	
							</td>						
							<td class="center">
								<g:if test="${!aprendizInstance.participa && aprendizInstance.cuatrimestre == cuatrimestre}">
									<g:link class="btn btn-success" action="cambiarEstado" id="${aprendizInstance.id}" 
										params="['cursoId': params.cursoId, 'activar': true]" value="${message(code: 'Activar')}" 
										onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');">
										<i class="icon-ok"></i></g:link>
								</g:if>
								<g:else>				
	                          		<g:if test="${aprendizInstance.participa}">
										<g:link class="btn btn-danger" action="cambiarEstado" id="${aprendizInstance.id}" 
											params="['cursoId': params.cursoId]"
											value="${message(code: 'default.button.delete.label', default: 'Delete')}" 
											onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');">
											<i class="icon-off"></i></g:link>
									</g:if>
									<g:else>
										<g:link class="btn btn-success" action="cambiarEstado" id="${aprendizInstance.id}" 
											params="['cursoId': params.cursoId, 'activar': true]" value="${message(code: 'Activar')}" 
											onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');">
										<i class="icon-ok"></i></g:link>
									</g:else>
									<g:link class="btn btn-success" controller="grupoActividadAprendiz" action="mostrarAprendiz"
	                            		params="['cursoId': params.cursoId, 'aprendizId': aprendizInstance.id]">A</g:link>	
	                          		<g:link class="btn btn-success" controller="evaluacionAprendiz" action="mostrarAprendiz"
	                            		params="['cursoId': params.cursoId, 'aprendizId': aprendizInstance.id]">E</g:link>	
	                            	<g:link class="btn btn-success" controller="encuesta" action="encuestasAprendiz"
	                            		params="['cursoId': params.cursoId, 'aprendizId': aprendizInstance.id]">Encuestas</g:link>	
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